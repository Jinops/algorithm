n = int(input())
datas = [n]
count = 0
while True:
    if 1 in datas:
        print(count)
        break

    new_datas = []

    for data in datas:
        if data%3==0:
            new_datas.append(data/3)
        if data%2==0:
            new_datas.append(data/2)
        if data>1:
            new_datas.append(data-1)

    datas = new_datas
    count += 1
