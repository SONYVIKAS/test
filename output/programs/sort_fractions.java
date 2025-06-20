from random import uniform

def sort_fractional(nums):
    nums = map(str, nums)
    for i, num in enumerate(nums):
        index = num.find('.')
        nums[i] = num[index+1:]
    return sorted(nums)

nums = [round(uniform(0, 1000), 3) for _ in range(100)]