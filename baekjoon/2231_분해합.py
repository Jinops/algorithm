num = int(input())
result = 0

for m in range(num):
    n = m + sum(map(int,list(str(m))))
    if n == num:
        result = m
        break
    
print(result)