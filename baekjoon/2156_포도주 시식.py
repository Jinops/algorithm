n = int(input())
nums = []
dp = []
for _ in range(n):
    nums.append(int(input()))

dp.append(nums[0])
if len(nums)>1:
    dp.append(nums[1] + nums[0])
if len(nums)>2:
    dp.append(max(nums[2]+nums[1], nums[2]+dp[0], dp[1]))

for i in range(3,n):
    dp.append(max(nums[i]+nums[i-1]+dp[i-3], nums[i]+dp[i-2], dp[i-1]))

print(dp[-1])
