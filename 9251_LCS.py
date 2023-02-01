# TODO
# a = input()
# b = input()
a = "acaca"  
b = "accca"

cache = [[0]*(len(b)+1)]*(len(a)+1)
for aIdx in range(len(a)):
    for bIdx in range(len(b)):
        if a[aIdx] == b[bIdx] :
            cache[aIdx+1][bIdx+1] = cache[aIdx][bIdx] + 1
        else :
            cache[aIdx+1][bIdx+1] = max(cache[aIdx+1][bIdx], cache[aIdx][bIdx+1])

print(cache[len(a)][len(b)])

