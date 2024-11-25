n = int(input())
a, b = map(int, input().split())
m = int(input())

nodes = list(set() for _ in range(n+1))

for _ in range(m):
  x, y = map(int, input().split())
  nodes[x].add(y)
  nodes[y].add(x)

visited = set([a])

result = -1

def dfs(node, cnt):
  global result
  global b
  visited.add(node)

  if node == b:
    result = cnt
    return

  for next in nodes[node]:
    if next not in visited:
      dfs(next, cnt+1)
      if result != -1:
        return

dfs(a, 0)

print(result)
