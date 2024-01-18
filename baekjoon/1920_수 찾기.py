def search(target, start, end):
    if start > end:
        return 0
    
    mid = (start + end) // 2
    compare = compares[mid]

    if target == compare:
        return 1
    if target > compare:
        start = mid + 1
    elif target < compare:
        end = mid - 1

    return search(target, start, end)

n = int(input())
compares = list(map(int, input().split()))
m = int(input())
targets = list(map(int, input().split()))

compares.sort()

for target in targets:
    print(search(target, 0, n-1))
