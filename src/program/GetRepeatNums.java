package program;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangfujie
 * @date 2018-10-26 16:45
 * @description 从数组中找出重复元素及其所在位置
 */
public class GetRepeatNums {

    public static void main(String[] args) throws Exception {
        int[] nums = {12, 18, 19, 15, 26, 29, 49, 15, 12, 19, 29, 12, 18};
        // map 的键 为 nums 中的整数，值 为 nums 中整数的位置
        // LinkedHashMap 可以维护键值对 加入 map 的顺序
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> positions = map.get(nums[i]);
            // 如果 map 的键 中不存在这个整数
            if (positions == null) {
                positions = new ArrayList<>();
                // 将这个整数和与其关联的位置 positions 放入 map
                map.put(nums[i], positions);
            }
            positions.add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> positions = entry.getValue();
            // 如果一个整数对应的位置数量大于 1，说明这个整数重复
            if (positions.size() > 1) {
                int num = entry.getKey();
                printResult(num, positions);
            }
        }
    }

    private static void printResult(int num, List<Integer> positions) {
        StringBuilder result = new StringBuilder();
        result.append(num).append(' ').append('{');
        for (Integer position : positions) {
            result.append(position).append(',');
        }
        // 把最后一个 , 替换为 }
        result.setCharAt(result.length() - 1, '}');
        System.out.println(result);
    }
}
