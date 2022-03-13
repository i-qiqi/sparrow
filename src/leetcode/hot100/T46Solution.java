package leetcode.hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.sound.midi.Soundbank;

public class T46Solution {

    List<List<Integer>> ans = null;
    public List<List<Integer>> permute(int[] nums) {
        ans = new LinkedList<>();
        helper(nums, 0, new LinkedList<>());  
        return ans;  
    }

    private void helper(int[] nums, int start, List<Integer> path){

        if (start == nums.length) {
            ans.add(new ArrayList<>(path));
            return ;
        }
        
        for ( int i = start; i < nums.length;  i++){
            swap(nums, i, start);
            path.addLast(nums[start]);
            helper(nums, start+1, path);
            path.remove(o)
            swap(nums, i, start);
        }
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(128);
        list.add(3);
        list.add(2);
        list.remove(new Integer(3));
        list.remove(new Integer(3));
        list.remove(new Integer(128));
        System.out.println(list.toString());
    }
    
}
