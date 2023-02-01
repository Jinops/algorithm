aS = input("")
bS = input("")

max_len = 0

for i in range(len(aS)):
    LCS = ""
    bIdx = 0
    for aIdx in range(i, len(aS)):
        bIdx = bS.find(aS[aIdx], bIdx)
        if bIdx == -1:
            continue
        LCS += bS[bIdx]
    
    if len(LCS) > max_len:
        max_len = len(LCS)

print(max_len)
