# TODO
n = int(input())
data_list = []
rank_list = [n] * n

for rank in range(n):
    kg, cm = map(int,input().split())
    data_list.append((kg, cm))

for rank in range(n):
    for j in range(rank+1, n):
        if data_list[rank][0] > data_list[j][0] and data_list[rank][1] > data_list[j][1]:
            rank_list[rank] -= 1

diff = min(rank_list)-1
diff_sub = 0
diff_dict = {}
for rank in sorted(rank_list):
    if rank not in diff_dict:
        diff_dict[rank] = diff + diff_sub
        diff_sub = 0
    else:
        diff_sub -= 1

rank_list = list(map(lambda rank: str(rank - diff_dict[rank]), rank_list))
print(' '.join(rank_list))
