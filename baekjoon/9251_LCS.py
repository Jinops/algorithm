a = input()
b = input()

dp = [[0]*(len(b)+1) for _ in range(len(a)+1)]

for aIdx in range(len(a)):
    for bIdx in range(len(b)):
        if a[aIdx] == b[bIdx] :
            dp[aIdx+1][bIdx+1] = dp[aIdx][bIdx] + 1
        else :
            dp[aIdx+1][bIdx+1] = max(dp[aIdx+1][bIdx], dp[aIdx][bIdx+1])

print(dp[len(a)][len(b)])
