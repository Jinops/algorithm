tc = int(input())

def cal(sign, tmp):
  return tmp if sign=='+' else -tmp

def check(cur, exp, N):
  cur += 1
  if cur > N:
    exp_origin = exp
    exp = exp.replace(" ", "")
    sign = '+'
    result = 0
    tmp = 0

    for c in exp:
      if c.isdecimal():
        tmp = tmp*10+int(c)
      else:
        result += cal(sign, tmp)
        tmp = 0
        sign = c

    result += cal(sign, tmp)

    if result==0:
      print(exp_origin)

    return
  
  check(cur, exp+" "+str(cur), N)
  check(cur, exp+"+"+str(cur), N)
  check(cur, exp+"-"+str(cur), N)

for _ in range(tc):
  N = int(input())
  check(1, "1", N)
  print()
