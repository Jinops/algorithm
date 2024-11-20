from collections import deque

node_cnt = int(input())
edge_cnt = int(input())

nodes = {}

for node in range(1, node_cnt+1):
 nodes[node] = set()

for _ in range(edge_cnt):
 start, end = map(int, input().split())
 nodes[start].add(end)
 nodes[end].add(start)

cnt = 0
queue = deque()
visited = set()

queue.append(1)
visited.add(1)

while queue:
 node = queue.popleft()

 for next in nodes[node]:
  if next not in visited:
   queue.append(next)
   visited.add(next)

print(len(visited)-1)
