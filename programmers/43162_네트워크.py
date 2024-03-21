from collections import deque

def solution(n, computers):
    result = 0
    visited = [False for _ in range(n)]
    
    for start in range(0, n):
        if visited[start]:
            continue
            
        result += 1
        
        Q = deque()
        Q.append(start)
        visited[start] = True
        
        while len(Q) > 0:
            node = Q.pop()
            for i in range(0, n):
                if computers[node][i] == 1 and not visited[i]:
                    Q.append(i)
                    visited[i] = True
        
    return result
