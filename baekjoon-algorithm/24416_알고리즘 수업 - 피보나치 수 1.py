def fib(n) :
    if n == 1 or n == 2:
        return 1;  # 코드1
    else:
        return (fib(n - 1) + fib(n - 2))

def fibonacci(n) :
    count = 0
    f = {1: 1, 2: 1}
    for i in range(3, n+1):
        count += 1
        f[i] = f[i-1] + f[i-2]
    
    return count

n = int(input())
print(fib(n), fibonacci(n))
