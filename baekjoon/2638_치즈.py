from collections import deque

ds = ((1,0), (-1,0), (0,1), (0,-1))

N, M = map(int, input().split())
mtx = [list(map(int, input().split())) for _ in range(N)]

def in_range(y, x):
  return 0<=y<N and 0<=x<M

def is_cheese_left():
  for y in range(N):
    for x in range(M):
      if mtx[y][x] == 1:
        return True
  return False

time = 0

while is_cheese_left():
  queue = deque()
  queue.append((0, 0))
  visited = [[False] * M for _ in range(N)]
  touch_cnt = [[0] * M for _ in range(N)]

  while queue:
    y, x = queue.popleft()
    
    for d in ds:
      ny, nx = y+d[0], x+d[1]
      if not in_range(ny, nx):
        continue
      
      if mtx[ny][nx] == 1:
        touch_cnt[ny][nx] += 1

      elif not visited[ny][nx] and mtx[ny][nx] == 0:
        visited[ny][nx] = True
        queue.append((ny, nx))
  
  for y in range(N):
    for x in range(M):
      if touch_cnt[y][x] >= 2:
        mtx[y][x] = 0
  
  time += 1

print(time)
