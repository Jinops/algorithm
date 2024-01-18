n = int(input())
numbers = list(map(int, input().split()))

max_list = [numbers[0]]

for i in range(1, len(numbers)):
    number = numbers[i]
    max_list.append(max(max_list[i-1]+number, number))

print(max(max_list))
