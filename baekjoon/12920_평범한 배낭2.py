# TODO: 6% 메모리초과
from sys import stdin

N, M = map(int, stdin.readline().split())

V = []
C = []

for _ in range(N):
  v, c, k = map(int, stdin.readline().split())
  for _ in range(k):
    V.append(v)
    C.append(c)

N = len(V)
dp = [[0]*(M+1) for _ in range(N)]
minW = min(V)

for w in range(V[0], M+1):
  dp[0][w] = C[0]

for n in range(1, N):
  for w in range(minW, V[n]):
      dp[n][w] = dp[n-1][w]

  for w in range(V[n], M+1):
      dp[n][w] = max(dp[n-1][w], C[n] + dp[n-1][w-V[n]])

print(dp[N-1][M])
