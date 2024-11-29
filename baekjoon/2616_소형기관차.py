size = int(input())
cnts = list(map(int, input().split()))
capacity = int(input())

dp = [[0 for _ in range(size+1)] for _ in range(4)]
S = [0]
sum = 0

for cnt in cnts:
    sum += cnt
    S.append(sum)

# dp[i][j] : i개의 열차를 운용할 때, j번째 객차를 마지막으로 할 때 최댓값

for j in range(capacity, size+1):
  # 객차를 1~capacity 이전까지에 배치하는 것은 고려하지 않아도 된다. (최댓값이 아니기 때문)
  # capacity 이상부터에 대하여,
  dp[1][j] = max(dp[1][j-1], S[j]-S[j-capacity])
  # 이전까지의 최댓값이나,
  # j번 객차로 끝났을 때의 최댓값을 넣는다

for i in range(2, 4):
   # 객차를 2개 이상 배치하는 경우에 대해서
   for j in range(i*capacity, size+1):
      # i번째 이전 객차를 모두 배치했을 때, 최소 시작위치는 i*capacity
      dp[i][j] = max(dp[i][j-1], dp[i-1][j-capacity] + S[j]-S[j-capacity])
      # 이전까지의 최댓값이나,
      # 현재 내 객차를 수용(j번으로 끝) + 이전 객차까지의 최댓값으로 계산

print(dp[3][size])
