package algorithm.无重复字符的最长子串;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangfj
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bhwssrrswixvlwzjprwgesccwmwyjsumbsftlyohglerhecyxygeqj"));
    }

    public static int lengthOfLongestSubstring(String s) {
        String[] arrays = s.split("");
        String noRep = "";
        int num = 0;
        for (String letter : arrays){
            if (noRep.contains(letter)){
                if (num < noRep.length()){
                    num = noRep.length();
                }
                noRep = "";
                if (s.indexOf(letter) + 1 < s.length()){
                    String newS = s.substring(s.indexOf(letter) + 1);
                    int newNum = lengthOfLongestSubstring(newS);
                    if (newNum >= num){
                        num = newNum;
                    }
                }
            }else {
                noRep = noRep + letter;
            }
        }
        if (num < noRep.length()){
            num = noRep.length();
        }
        return num;
    }
}
