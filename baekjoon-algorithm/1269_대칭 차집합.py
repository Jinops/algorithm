n, m = map(int, input().split())
a = set(input().split())
b = set(input().split())

share = len(a & b)

print(len(a)+len(b)-share*2)
