def solution(n):
    arr = [None, 1,1,1,2,2,3]
    arr.extend([None]*(n-6))

    for i in range(6, n+1):
       arr[i] = arr[i-5]+arr[i-1]

    return arr[n]

count = int(input())
for _ in range(count):
    n = int(input())
    print(solution(n))
