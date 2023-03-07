n = int(input())
nums = []
dp = []
for _ in range(n):
    nums.append(int(input()))
    dp.append(0)

dp[0] = nums[0]
if len(nums) > 1:
    dp[1] = nums[1] + dp[0]
if len(nums) > 2:
    dp[2] = max(nums[2]+nums[0], nums[2]+nums[1])

for i in range(3, len(nums)):
    dp[i] = max(nums[i] + dp[i-2], nums[i] + nums[i-1] + dp[i-3])

print(dp[len(dp)-1])
