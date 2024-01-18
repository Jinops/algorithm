n, m = map(int, input().split())
word_list = []
count = 0
for _ in range(n):
    word_list.append(input())
for _ in range(m):
    search = input()
    if search in word_list:
        count += 1
print(count)
