n, m = map(int, input().split())
num_dict = {}
name_dict = {}
result = []

for num in range(n):
    name = input()
    num_dict[str(num+1)] = name
    name_dict[name] = str(num+1)
for _ in range(m):
    keyword = input()
    if keyword.isalpha():
        result.append(name_dict[keyword])
    else:
        result.append(num_dict[keyword])

print('\n'.join(result))
