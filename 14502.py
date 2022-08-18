import copy

moveXY = [(1,0), (-1,0), (0,1), (0,-1)]
n,m = map(int, input().split())
lab = []
safeCount = 0

for i in range(0,n):
    lab.append(list(map(int, input().split())))

def spreadVirus(lab): # bfs
    q = []
    for x in range(n):
        for y in range(m):
            if lab[x][y] == 2:
                q.append((x,y))
    
    while q:
        currentX, currentY = q[0]
        for move in moveXY:
            x, y = currentX + move[0], currentY + move[1]

            if not (x >= 0 and x < n and y >=0 and y < m):
                continue
            if lab[x][y] == 0:
                lab[x][y] = 2
                q.append((x,y))
        q.pop(0)

def getVirusedLab() :
    virusedLab = copy.deepcopy(lab)
    spreadVirus(virusedLab)
    return virusedLab

def getSafeCount(lab) :
    count = 0
    for x in range(n):
        for y in range(m):
            if lab[x][y] == 0:
                count += 1
    return count

def generateLabAndRun(moreWallCount):
    global lab
    global safeCount
    if moreWallCount < 3 :
        for x in range(n):
            for y in range(m):
                if lab[x][y] == 0:
                    lab[x][y] = 1
                    generateLabAndRun(moreWallCount+1)
                    lab[x][y] = 0
    else :
        virusedLab = getVirusedLab()
        safeCount = max(safeCount, getSafeCount(virusedLab))

generateLabAndRun(0)
print(safeCount)
