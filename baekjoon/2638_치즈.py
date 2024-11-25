from collections import deque

ds = ((1,0), (-1,0), (0,1), (0,-1))

n, m = map(int, input().split())
mtx = [list(map(int, input().split())) for _ in range(n)]

def in_range(y, x):
  return 0<=y<n and 0<=x<m


while True:
  queue = deque()
  queue.append((0, 0))
  visited = [[False] * m for _ in range(n)]
  touched = [[False] * m for _ in range(n)]

  while queue:
    y, x = queue.popleft()
    
    for d in ds:
      ny, nx = y+d[0], x+d[1]
      if not in_range(ny, nx):
        continue
      
      if not visited[ny][nx] and mtx[ny][nx] == 0:
        visited[ny][nx] = True
        queue.append((ny, nx))
