from collections import deque
import copy
import sys

def rotate(mtx, cmd):
  r, c, s = cmd

  for i in range(s):
    rotate_outline(mtx, r-s+i, c-s+i, r+s-i, c+s-i)

def rotate_outline(mtx, ay, ax, by, bx):
  y, x = ay, ax
  before = mtx[y][x]

  while x < bx:
    x += 1
    cur = mtx[y][x]
    mtx[y][x] = before
    before = cur

  while y < by:
    y += 1
    cur = mtx[y][x]
    mtx[y][x] = before
    before = cur

  while x > ax:
    x -= 1
    cur = mtx[y][x]
    mtx[y][x] = before
    before = cur

  while y > ay:
    y -= 1
    cur = mtx[y][x]
    mtx[y][x] = before
    before = cur

def get_min(mtx):
  result = sys.maxsize
  for line in mtx:
    result = min(result, sum(line))

  return result

def operate():
  if len(queue) == K:
    mtx = copy.deepcopy(A)
    for cmd in queue:
      rotate(mtx, cmd)
    global result
    result = min(result, get_min(mtx))
    return

  for i in range(K):
    if not used[i]:
      queue.append(cmds[i])
      used[i] = True
      operate()
      queue.pop()
      used[i] = False

N, M, K = map(int, input().split())
A = [list(map(int, input().split())) for _ in range(N)]
result = sys.maxsize

cmds = []
queue = deque()
used = [False] * K

for _ in range(K):
  r, c, s = map(int, input().split())
  cmds.append((r-1, c-1, s))

operate()
print(result)
