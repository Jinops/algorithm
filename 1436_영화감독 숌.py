n = int(input())
number = 665
while n > 0:
    number += 1
    if str(number).find('666') != -1:
        n -= 1
print(number)
