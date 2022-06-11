package leetcode.swordoffer;

public class SO51Solution {
    class Solution {
        int ans = 0;
        int[] temp;
        public int reversePairs(int[] nums) {
            temp = new int[nums.length];
            mergeSort(nums, 0, nums.length -1);
            temp = null;
            return ans;
        }

        public void mergeSort(int[] nums, int low, int high){
            if(low >= high) return;
            int mid = low + (high - low)/2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid+1, high);
            merge(nums, low, mid, high);
        }

        void merge(int[] nums, int low, int mid,  int high){
            //[low, mid], [mid+1, high]
            int i = low, j = mid+1;
            int len = high-low+1;
            int k = 0;
            while(i <= mid && j <= high){
                if(nums[i] <= nums[j]){
                    temp[k++] = nums[i];
                    i++;
                } else {
                    temp[k++] = nums[j];
                    ans = ans +  mid - i + 1;
                    j++;
                }
            }

            while(i <= mid){
                temp[k++] = nums[i++];
            }
            while(j <= high){
                temp[k++] = nums[j++];
            }
            System.arraycopy(temp, 0, nums, low, len);
        }
    }
}
