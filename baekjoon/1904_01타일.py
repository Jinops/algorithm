def get_count(n):
    if n==1:
        return 1
    num1 = 1
    num2 = 2
    for _ in range(3, n+1):
        num1, num2 = num2, (num1+num2)%15746
    return num2

n = int(input())
print(get_count(n))
