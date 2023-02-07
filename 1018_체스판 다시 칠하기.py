def get_diff_count(i_start, j_start):
    global data
    global board_b
    global board_w
    count_b = 0
    count_w = 0

    for i in range(8):
        for j in range(8):
            if data[i_start+i][j_start+j] != board_b[i][j]:
                count_b += 1
            if data[i_start+i][j_start+j] != board_w[i][j]:
                count_w += 1

    return min(count_b, count_w)

def generate_board(is_start_b):
    board = []
    for i in range(8):
        line = []
        for j in range(8):
            if(is_start_b^( \
                (i%2==0 and j%2==1) or \
                (i%2==1 and j%2==0))):
                line.append('B')
            else:
                line.append('W')
        board.append(line)
    return board

board_b = generate_board(True)
board_w = generate_board(False)

n, m = map(int, input().split())
data = []

for _ in range(n):
    data.append(list(input()))

min_diff_count = get_diff_count(0, 0)

for i in range(n-7):
    for j in range(m-7):
        min_diff_count = min(min_diff_count, get_diff_count(i, j))

print(min_diff_count)
