package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * 给定一个整数数组nums和一个目标值target，请在数组中找出和为目标值的两个整数，并返回它们的数组下标
 * 假设每种输入只会对应一个答案，但是不可重复利用数组的同样的元素
 *
 * @author wangfj
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 7, 11, 15};
        int[] ans = twoSum3(nums, 9);
        System.out.println("两数下标为：" + ans[0] + ":" + ans[1]);
    }

    /**
     * 方法一，普通玩家，耗时52ms
     */
    static int[] twoSum(int[] nums, int target){
        for (int i = 0 ; i < nums.length ; i++){
            for (int j = i + 1 ; j < nums.length ; j++){
                if (nums[i] + nums[j] == target){
                    return new int[]{i , j};
                }
            }
        }
        return null;
    }

    /**
     * 方法二，高端玩家，使用map，存入补数和下标，循环找到补数即可，耗时8ms
     */
    static int[] twoSum2(int[] nums, int target){
       int[] result = new int[2];
       Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //找到补数，即为答案
            if (map.containsKey(nums[i])){
                return new int[]{i, map.get(nums[i])};
            }
            //存入补数及下标
            map.put(target - nums[i], i);
        }
        return null;
    }

    /**
     * 方法三，王者玩家，耗时1ms
     */
    static int[] twoSum3(int[] nums, int target){
        int max = 2047;
        int[] tempNums = new int[max + 1];
        for (int i = 0; i < nums.length; i++) {
            //计算补数的下标位置
            int diffPos = (target - nums[i]) & max;
            if (tempNums[diffPos] != 0){//补数的值不为0的，它的值则是需要的下标
                return new int[]{tempNums[diffPos] - 1, i};
            }
            //由于初始tempNums数组默认值都为0，这里存入下标位置加1来做区分
            tempNums[nums[i] & max] = i + 1;
        }
        throw  new IllegalStateException("No two sum solution!");
    }
}
