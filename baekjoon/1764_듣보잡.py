n, m = map(int, input().split())
dict = {}
result_list = []

for _ in range(n):
    dict[input()] = True
for _ in range(m):
    name = input()
    if name in dict:
        result_list.append(name)

result_list.sort()
print(len(result_list))
print('\n'.join(result_list))
