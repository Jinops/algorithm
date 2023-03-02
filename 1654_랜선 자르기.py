def b_search(start, end):
    if start > end:
        return 0
    mid = (start+end)//2
    count = get_count(mid)
    global m

    if count == m:
        return s_search(mid, end)
    elif count > m:
        return b_search(mid+1, end)
    else:
        return b_search(start, mid-1)
    
def s_search(start, end):
    global m
    while start <= end \
        and get_count(start) == m:
        start += 1
    return start -1
        
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

start = max(datas) // m
end = sum(datas) // m

print(b_search(start, end))
