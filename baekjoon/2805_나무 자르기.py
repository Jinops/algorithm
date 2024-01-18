def search(start, end):
    if start > end:
        return end
    
    mid = (start+end) // 2
    heights = get_heights(mid)

    if heights >= m:
        return search(mid+1, end)
    else:
        return search(start, mid-1)

def get_height(height, cut):
    return max(height-cut, 0)
    
def get_heights(cut):
    heights = 0
    for h in hs:
        if h < cut:
            break
        heights += (h-cut)

    return heights

n, m = map(int, input().split())
hs = list(map(int, input().split()))
hs.sort(reverse=True)
start = get_height(hs[0], m)
end = hs[0]-1

print(search(start, end))
