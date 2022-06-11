package leetcode.bisearch;

/**
 * @搜索旋转排序数组
 */
public class T33Solution {
    /**
     * 分类讨论版
     */
    class Solution {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    if (target >= nums[0]) { //target在前半部分
                        // mid 也在前半部分
                        high = mid - 1;
                    } else { //后半部分
                        if (nums[mid] >= nums[0]) { // mid在前半部分
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    }
                } else {
                    if (target >= nums[0]) { // 前半部分
                        if (nums[mid] >= nums[0]) { //前半部分
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    } else {
                        low = mid + 1;
                    }
                }
            }
            return nums[low] == target ? low : -1;
        }
    }


    /**
     *  简化理解版
     *  mid 和 target 不在同一段的时候，反过来处理
     *  在同一段的时候，正常二分处理
     */
    class Solution1 {
        public int search(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            while(low < high) {
                int mid = low + (high - low) / 2;
                if(nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    // mid 和 target 不在同一段
                    if(target < nums[0] && nums[mid] >= nums[0]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    // mid 和 target 不在同一段
                    if (target >= nums[0] && nums[mid] < nums[0]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
            return nums[low] == target ? low : -1;
        }

        public int search1(int[] nums, int target) {
            int low = 0, high = nums.length - 1;
            /**
             * 注意这里是等于号
             */
            while(low <= high) {
                int mid = low + (high - low) / 2;
                if(nums[mid] == target) {
                    return mid;
                } else if (nums[mid] > target) {
                    if(target < nums[0] && nums[mid] >= nums[0]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    if (target >= nums[0] && nums[mid] < nums[0]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
            return -1;
        }
    }
}
