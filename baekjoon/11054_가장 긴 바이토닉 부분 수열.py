n = int(input())
nums = list(map(int, input().split()))
dp_left = [1] * len(nums)
dp_right = [1] * len(nums) 

for i in range(len(nums)):
    idx = None
    for j in range(0, i):
        if nums[j] < nums[i] and \
        (idx is None or dp_left[j] > dp_left[idx]):
            idx = j
        
    if idx is not None:
        dp_left[i] += dp_left[idx]

for i in range(len(nums)-1, -1, -1):
    idx = None
    for j in range(len(nums)-1, i, -1):
        if nums[j] < nums[i] and \
        (idx is None or dp_right[j] > dp_right[idx]):
            idx = j
        
    if idx is not None:
        dp_right[i] += dp_right[idx]

result = 0
for i in range(0, len(nums)):
    result = max(result, dp_left[i]+dp_right[i]-1)
print(result)
