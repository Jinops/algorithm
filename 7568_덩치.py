n = int(input())
data_list = []
rank_list = [1] * n

for _ in range(n):
    kg, cm = map(int,input().split())
    data_list.append((kg, cm))

for i in range(n):
    for j in range(n):
        if data_list[i][0] < data_list[j][0] \
        and data_list[i][1] < data_list[j][1]:
            rank_list[i] += 1

print(' '.join(map(str, rank_list)))
