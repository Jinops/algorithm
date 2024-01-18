n_size = input()
n_list = list(map(int, input().split()))
m_size = input()
m_list = list(map(int, input().split()))

n_list.sort()

def b_search(target, min=0, max=len(n_list)-1):
    global n_list
    if max >= min:
        mid = (min+max)//2
        if n_list[mid] == target:
            return 1
        elif n_list[mid] > target :
            return b_search(target, min, mid-1)
        else:
            return b_search(target, mid+1, max)
    else:
        return 0

for m in m_list:
    print(b_search(m), end=" ")
