max_w = 0
max_h = 0
distance_list = []

multi = int(input())
for i in range(6):
    way, distance = map(int, input().split())
    if way==1 or way==2:
        max_w = max(max_w, distance)
    else:
        max_h = max(max_h, distance)
    distance_list.append(distance)

max_w_idx = distance_list.index(max_w)
h1_idx = max_w_idx-1
h2_idx = max_w_idx+1
max_h_idx = distance_list.index(max_h)
w1_idx = max_h_idx-1
w2_idx = max_h_idx+1

if max_w_idx == 0:
    h1_idx = 5
elif max_w_idx == 5:
    h2_idx = 0

if max_h_idx == 0:
    w1_idx = 5
elif max_h_idx == 5:
    w2_idx = 0

min_w = abs(distance_list[w1_idx]-distance_list[w2_idx])
min_h = abs(distance_list[h1_idx]-distance_list[h2_idx])

print(multi * (max_w * max_h - min_w * min_h))
