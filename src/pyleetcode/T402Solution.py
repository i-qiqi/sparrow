#!/usr/bin/python3
from typing import List

class Solution:
    def maxNumber(self, nums1: List[int], nums2: List[int], k: int) -> List[int]:

        def pick_max(nums: List[int], k1: int):
            stack = []
            drop = len(nums) - k1
            for num in nums:
                while drop and stack and stack[-1] < num:
                    stack.pop()
                    drop -= 1
                stack.append(num)
            return stack[:k1]

        def merge(a: List[int], b: List[int]):
            ans = ""
            while a or b:
                bigger = a if a > b else b
                ans += bigger.pop(0)

            return ans

        ret = 0

        for i in range(k + 1):
            if i <= len(nums1) and k - i <= len(nums2):
                left_ans = pick_max(nums1, i)
                right_ans = pick_max(nums2, k - i)
                ret = max(ret, merge(left_ans, right_ans))

        return


if __name__ == "__main__":
    print("test")
    list1 = [2,3,1]
    list2 = [2, 1, 3]

    print(max(list1, list2))
    print(min(list1, list2))

