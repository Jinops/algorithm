n = int(input())
tris = []
dp = []
for i in range(n):
    tris.append(tuple(map(int, input().split())))
    dp.append([0] * len(tris[i]))

dp[0] = tris[0]

for i in range(1, n):
    for j in range(len(tris[i])):
        dp[i][j] = tris[i][j]
        if j==0:
            dp[i][j] += dp[i-1][0]
        elif j==len(tris[i])-1:
            dp[i][j] += dp[i-1][j-1]
        else:
            dp[i][j] += max(dp[i-1][j-1], dp[i-1][j])

print(max(dp[n-1]))
