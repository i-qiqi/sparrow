package leetcode.sort;


import java.util.Random;

public class T912Solution {
    /**
     * 1 编号从1开始， i 的父亲 i/2, i的左孩子 2 * i, 右孩子 2 * i + 1
     * 2 swap 函数先写上
     * 3 offer 上浮算法 : pi = i/2, 循环条件 pi > 0; 如果双亲小，就交换，如果双亲大，就提前结束循环，next loop
     * 4 poll 下沉算法 :  循环条件 i <= heapSize,  如果存在孩子，找较大的孩子交换，没有孩子或者没有更大的孩子，提前结束循环， next loop
     * 5 创建堆: heapSize = 1 默认， 从2 -> n, 循环offer
     * 6 排序: 交换堆顶元素到堆尾，不断poll元素，直到heapSize == 1
     */
    class Solution {
        public int[] sortArray(int[] nums) {
            BigHeap h = new BigHeap(nums);
            h.heapSort();
            return nums;
        }

        class BigHeap {
            int heapSize = 1;
            int nums[];

            public BigHeap(int[] arr) {
                this.nums = arr;
            }

            public void heapSort() {
                createHeap(nums, nums.length);
                while (heapSize > 1) {
                    poll(nums);
                }
            }

            public void createHeap(int[] nums, int size) {
                for (int i = 2; i <= size; i++) {
                    offer(nums, i);
                }
            }

            public int poll(int[] nums) {
                swap(nums, 0, heapSize - 1);
                heapSize--;
                //调整堆
                int i = 1;
                while (i <= heapSize) {
                    int leftChild = 2 * i;
                    int rightChild = 2 * i + 1;

                    int maxChild = i; //TODO: 默认是自己

                    if (leftChild <= heapSize) {
                        maxChild = leftChild;
                    }
                    if (rightChild <= heapSize && nums[rightChild - 1] > nums[leftChild - 1]) {
                        maxChild = rightChild;
                    }
                    //TODO: 注意== 不然DS
                    if (nums[i - 1] >= nums[maxChild - 1]) {
                        break; //不用调整了
                    }
                    swap(nums, i - 1, maxChild - 1);

                    i = maxChild;
                }

                return nums[heapSize];
            }

            //i 是编号， size 是堆的大小
            public void offer(int[] nums, int i) {
                if (heapSize > nums.length) return;
                swap(nums, heapSize, i - 1);
                //上浮
                int pi = i / 2;
                while (pi > 0) {
                    if (nums[pi - 1] < nums[i - 1]) {
                        swap(nums, pi - 1, i - 1);
                    } else {
                        break;
                    }
                    i = pi;
                    pi = i / 2;
                }
                heapSize++;
            }

            public void swap(int[] nums, int i, int j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }


    }

    //TODO: 记住
    class Solution1 {
        int[] tempArr;

        public int[] sortArray(int[] nums) {
            tempArr = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        public void mergeSort(int[] nums, int low, int high) {
            if (low >= high) return;
            int mid = low + (high - low) / 2;

            mergeSort(nums, low, mid);
            mergeSort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }

        public void merge(int[] nums, int low, int mid, int high) {
            int i = low;
            int j = mid + 1;
            int k = 0;
            while (i <= mid && j <= high) {
                if (nums[i] <= nums[j]) {
                    tempArr[k++] = nums[i++];
                } else {
                    tempArr[k++] = nums[j++];
                }
            }

            while (i <= mid) {
                tempArr[k++] = nums[i++];
            }
            while (j <= mid) {
                tempArr[k++] = nums[j++];
            }

            //复制
            System.arraycopy(tempArr, 0, nums, low, k);
        }

    }

    /**
     * 1 快排递归: 分区p归位, 递归[low, p - 1]和[p+1, high]
     * 2 分区算法:
     * 随机数来个p,
     * p先放最右边,
     * 来个k维护边界,
     * 遍历[low, high],
     * 遇到比pivot小的，
     * 和nums[k]交换下,
     * k占下个位置，
     * 最后pivot归位
     */
    class Solution2 {

        public int[] sortArray(int[] nums) {
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        //随机快排
        public void quickSort(int[] nums, int low, int high) {
            if (low >= high) return;
            int p = partition(nums, low, high);
            quickSort(nums, low, p - 1);
            quickSort(nums, p + 1, high);
        }

        public int partition(int[] nums, int low, int high) {
            Random rand = new Random();
            int pivotIdx = rand.nextInt(high - low + 1) + low;
            // int pivotIdx = low + (high - low) / 2;
            //先放一边
            swap(nums, pivotIdx, high);
            int pivot = nums[high];
            //TODO: k 赋初值， 千万不要上来就是0
            int k = low;
            for (int i = low; i < high; i++) {
                if (nums[i] < pivot) {
                    swap(nums, k++, i);
                }
            }
            //归位
            swap(nums, k, high);
            return k;
        }

        public void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    class Solution3 {
        public int[] sortArray(int[] nums) {
            bubbleSort(nums);
            return nums;
        }

        //优化后的也timeout
        public void bubbleSort(int[] nums){
            int low = 0, high = nums.length - 1;
            int startPos = low, endPos = high;
            while(low < high){
                for(int j = low; j + 1 <= high; j++){
                    if(nums[j] > nums[j+1]){
                        swap(nums, j, j+1);
                        endPos = j;
                    }
                }
                //1 已经排序好提前结束
                if(endPos == high){
                    return;
                }
                //2 决定局部有序位置
                high = endPos;

                //3 双向排序
                for(int j =  high ; j - 1 >= low; j--){
                    if(nums[j] < nums[j - 1]){
                        swap(nums, j, j-1);
                        startPos = j;
                    }
                }
                if(startPos == low){
                    return ;
                }
                low = startPos;
            }
        }



        public void swap(int[] nums, int i, int j){
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
    }


}
