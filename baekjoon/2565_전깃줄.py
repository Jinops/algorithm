n = int(input())
a_list = [None] * n
b_list = [None] * n
b_dict = {}

dp = [1] * n

for i in range(n):
    a, b = map(int, input().split())
    a_list[i] = a
    b_dict[a] = b

a_list.sort()

for i in range(n):
    b_list[i] = b_dict[a_list[i]]

for i in range(n):
    for j in range(i):
        if b_list[i] > b_list[j]:
            dp[i] = max(dp[i], dp[j]+1)

print(n-max(dp))
