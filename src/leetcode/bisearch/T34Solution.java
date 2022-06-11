package leetcode.bisearch;

public class T34Solution {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) return new int[]{-1, -1};
            int lb = left_bound(nums, target);
            int rb = right_bound(nums, target);
            return new int[]{lb, rb};
        }

        public int left_bound(int[] nums, int target) {
            int low = 0, high = nums.length;

            while (low < high) {
                //[low,high] 的len是偶数时，偏左
                int mid = low + (high - low) / 2;

                if (nums[mid] >= target) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }

            return low == nums.length || nums[low] != target ? -1 : low;
        }

        public int right_bound(int[] nums, int target) {
            int low = -1, high = nums.length - 1;

            while (low < high) {
                /**
                 * [low,high] 的len是偶数时，偏右
                 * [1,1,1] , target = 1 , 避免出现死循环
                 * [1] , target = 0, 避免越界
                 */
                int mid = low + (high - low + 1) / 2;

                if (nums[mid] <= target) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            /**
             * [1, 1], target = 0, 避免high = -1 = low 这种情况
             */
            return low == -1 || nums[low] == target ? low : -1;
        }
    }

    class Solution1 {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) return new int[]{-1, -1};
            int lb = left_bound(nums, target);
            int rb = right_bound(nums, target);
            return new int[]{lb, rb};
        }

        int left_bound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    // 别返回，锁定左侧边界
                    right = mid - 1;
                }
            }
            // 最后要检查 left 越界的情况
            if (left >= nums.length || nums[left] != target) {
                return -1;
            }
            return left;
        }

        int right_bound(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] == target) {
                    // 别返回，锁定右侧边界
                    left = mid + 1;
                }
            }
            // 最后要检查 right 越界的情况
            if (right < 0 || nums[right] != target) {
                return -1;
            }
            return right;
        }

        int find_bound(int[] nums, int target, boolean isLeftBound) {
            //空值处理 || 越界处理: case 1,2
            if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) return -1;
            int low = 0, high = nums.length - 1;
            while (low <= high) {
                //@attention : why need '+1', 避免死循环
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    if (isLeftBound == true) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                }
            }

            if (isLeftBound == true) {
                return nums[low] == target ? low : -1;
            }
            return nums[high] == target ? high : -1;
        }
    }


    class Solution2 {
        /**
         * 1. target > maxVal in nums[]
         * 2. target < minVal in nums[]
         * 3. nums[k] < target < nums[k+1], 0 <= i <= k < nums.length - 1
         * 4. target == nums[i:i+k]
         *
         * @param nums
         * @param target
         * @return
         */
        int left_bound(int[] nums, int target) {

            //空值处理
            if (nums.length == 0) return -1;

            //越界处理: case 1,2
            if (target < nums[0] || target > nums[nums.length - 1]) return -1;

            int low = 0, high = nums.length - 1;
            //[1, 1, 1], t = 1 √
            //[1, 3, 3], t = 3 √
            //[1, 3, 3], t = 2 √
            //[1, 1, 5], t = 4 √
            //[1, 2, 3, 3, 3], t = 2 √
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    high = mid;
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                }
            }

            return nums[low] == target ? low : -1;
        }

    }

    class SolutionMy {
        public int[] searchRange(int[] nums, int target) {
            int lb = find_bound(nums, target, true);
            int rb = find_bound(nums, target, false);
            return new int[]{lb, rb};
        }

        int find_bound(int[] nums, int target, boolean isLeftBound) {
            //空值处理 || 越界处理: case 1,2
            if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) return -1;
            int low = 0, high = nums.length - 1;
            while (low < high) {
                //@attention : why need '+1', 避免死循环, 求右边界的时候保证low可以往右逼近
                int mid = isLeftBound == true ? low + (high - low) / 2 : low + (high - low + 1) / 2;
                if (nums[mid] == target) {
                    if (isLeftBound == true) {
                        high = mid;
                    } else {
                        low = mid;
                    }
                } else if (nums[mid] < target) {
                    low = mid + 1;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                }
            }

            return nums[low] == target ? low : -1;
        }

        public int search(int[] nums, int target) {
            if (nums.length == 0) return -1;
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return -1;
        }

        public int search1(int[] nums, int target) {
            if (nums.length == 0) return -1;
            int low = 0, high = nums.length - 1;

            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return nums[low] == target ? low : -1;
        }
    }
}
