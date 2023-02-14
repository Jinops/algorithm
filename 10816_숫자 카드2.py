n = int(input())
num_list = input().split()
dict = {}
for num in num_list:
    if num in dict:
        dict[num] += 1
    else:
        dict[num] = 1

m = int(input())
find_num_list = input().split()
for find_num in find_num_list:
    if find_num in dict:
        print(dict[find_num], end=" ")
    else:
        print("0", end=" ")
