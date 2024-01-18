n = int(input())
nums = list(map(int, input().split()))
dp = [1] * len(nums)

for i in range(0, len(nums)):
    max_idx = None
    for j in range(0, i):
        if nums[j] < nums[i] and \
        (max_idx is None or dp[j] > dp[max_idx]):
            max_idx = j

    if max_idx is not None:
        dp[i] += dp[max_idx]

print(max(dp))
