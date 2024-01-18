from collections import deque

n,m,k,x = map(int, input().split())
m_line = {}
answers = []

def bfs(start, end):
    q = deque([start])
    visited = [False] * (n+1)
    visited[start] = True
    distance = 0
    while q :
        distance += 1
        start_node = q[0]
        if start_node in m_line:
            for node in m_line[start_node]:
                if not visited[node] :    
                    q.append(node)
                    visited[node] = True
                    if node == end :
                        return distance
        q.popleft()
    return distance

for i in range(0, m):
    start, end = map(int, input().split())
    if not start in m_line:
        m_line[start] = []
    m_line[start].append(end)

for city in range(1, n+1):
    if k == bfs(x, city) :
        answers.append(city)

if len(answers) > 0 :
    answers.sort()
    print(*answers, sep='\n')
else :
    print(-1)
