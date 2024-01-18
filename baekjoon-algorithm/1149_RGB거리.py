n = int(input())
rgbs = [(0,0,0)]
results = [[0,0,0]]

for _ in range(n):
    rgbs.append(tuple(map(int,input().split())))
    results.append([0,0,0])

for i in range(1,len(rgbs)):
    results[i][0] = rgbs[i][0] + min(results[i-1][1], results[i-1][2])
    results[i][1] = rgbs[i][1] + min(results[i-1][2], results[i-1][0])
    results[i][2] = rgbs[i][2] + min(results[i-1][0], results[i-1][1])

print(min(results[len(rgbs)-1]))
