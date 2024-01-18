n, m = input().split()
n = int(n)
m = int(m)
num_list = list(map(int, input().split()))
result = 0

for i in range(len(num_list)):
    if result == m:
        break
    sum = num_list[i]
    if sum > m-2:
        continue

    for j in range(i+1, len(num_list)):
        sum = num_list[i] + num_list[j]
        if sum > m-1:
            continue

        for k in range(j+1, len(num_list)):
            sum = num_list[i] + num_list[j] + num_list[k]
            if sum > m:
                continue
            result = max(result, sum)

print(result)
