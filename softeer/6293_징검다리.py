import sys

N = int(input())
arr = list(map(int, input().split()))
dp = [0] * N # i번째까지 갔을 때 밟은 돌 개수

for i in range(0, N):
    cnt = 0; # 밟을 수 있는 이전 돌까지의 밟은 개수 최대
    for prev in range(i):
        if arr[prev] < arr[i]:
            cnt = max(cnt, dp[prev])
    dp[i] = cnt + 1 #  이전 거치고+지금(1) 총 cnt+1개의 돌 밟음
    
print(max(dp))
