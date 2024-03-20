import sys
import queue

def BFS(start, end, immovables):
    depths = [[-1 for _ in range(m)] for _ in range(n)]
    Q = queue.Queue()
    Q.put(start)
    depths[start[1]][start[0]] = 0
    while not Q.empty():
        node = Q.get()
        depth = depths[node[1]][node[0]] 

        if matrix[node[1]][node[0]] == end:
            return depth
        
        for delta in deltas:
            nx = node[0] + delta[0]
            ny = node[1] + delta[1]
            if inRange(nx,ny) and (matrix[ny][nx] not in immovables) and depths[ny][nx] == -1:
                depths[ny][nx] = depth+1
                Q.put([nx, ny])
                
    return sys.maxsize
            
def inRange(x, y):
    return 0<=x<m and 0<=y<n

deltas = [[1,0],[-1,0],[0,1],[0,-1]]

n, m = map(int, input().split())

N = None
D = None
has_G = False

matrix = [input() for _ in range(n)]
for i in range(n):
    for j in range(m):
        if matrix[i][j] == 'N':
            N = [j, i]
        elif matrix[i][j] == 'D':
            D = [j, i]
        elif matrix[i][j] == 'G':
            has_G = True

G_depth = BFS(D, 'G', []) if has_G else sys.maxsize
N_depth = BFS(N, 'D', ['#'])

print("Yes" if N_depth<G_depth else "No")
