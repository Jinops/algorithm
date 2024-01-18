def b_search(start, end):
    if start > end:
        return end
    mid = (start+end)//2
    count = get_count(mid)
    global m

    if count >= m:
        return b_search(mid+1, end)
    else:
        return b_search(start, mid-1)
        
def get_count(length):
    global datas
    count = 0
    for data in datas:
        count += data // length
    return count

k, m = map(int, input().split())
datas = []
for _ in range(k):
    datas.append(int(input()))

start = max(max(datas) // m, 1)
end = sum(datas) // m

print(b_search(start, end))
