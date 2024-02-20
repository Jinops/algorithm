from queue import Queue

def DFS(node, visited=[]):
    visited.append(node)
    if node not in E:
        return visited
    for adj_node in E[node]:
        if adj_node not in visited:
            DFS(adj_node, visited)
    return visited

def BFS(start):
    queue = Queue()
    queue.put(start)
    visited = [start]
    while queue.qsize() :
        node = queue.get()
        if node not in E:
            return visited
        for adj_node in E[node]:
            if adj_node not in visited:
                queue.put(adj_node)
                visited.append(adj_node)
    return visited

N, M, V = map(int, input().split())
E = {}
for _ in range(M):
    frm, to = map(int, input().split())
    if frm not in E:
        E[frm] = []
    if to not in E:
        E[to] = []
    E[frm].append(to)
    E[to].append(frm)

for key in E.keys():
    E[key].sort()

print(*DFS(V), sep=" ")
print(*BFS(V), sep=" ")
