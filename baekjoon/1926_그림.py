from collections import deque

ds = ((1, 0), (-1, 0), (0, 1), (0, -1))

N, M = map(int, input().split())
mtx = [list(map(int, input().split())) for _ in range(N)]

def in_range(n, m):
  return 0<=n<N and 0<=m<M

def get_size(n, m):
  queue = deque()
  queue.append((n, m))
  mtx[n][m] = 0
  size = 0

  while queue:
    y, x = queue.popleft()
    size += 1

    for d in ds:
      ny, nx = y+d[0], x+d[1]
      if in_range(ny, nx) and mtx[ny][nx] == 1:
        mtx[ny][nx] = 0
        queue.append((ny, nx))

  return size

cnt = 0
max_size = 0

for n in range(N):
  for m in range(M):
    if mtx[n][m] == 1:
      cnt += 1
      max_size = max(max_size, get_size(n, m))

print(cnt)
print(max_size)
