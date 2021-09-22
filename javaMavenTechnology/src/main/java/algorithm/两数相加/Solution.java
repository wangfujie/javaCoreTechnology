package algorithm.两数相加;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author wangfj
 */
public class Solution {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2,new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5,new ListNode(6, new ListNode(4)));
        ListNode result = addTwoNumbers(l1, l2);
        System.out.print(result.val);
        while (result.next != null){
            result = result.next;
            System.out.print(result.val);
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //初始化结果节点，后后面得遍历节点
        ListNode result = null, tail = null;
        //初始化进位为0
        int carry = 0;
        while (l1 != null || l2 != null){
            //从链表中取出值，如果为空，则赋值0
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            //计算两数和，加上进位
            int sum = n1 + n2 + carry;
            //判断，如果为第一次进来，则给result赋值，之后只需一次赋值tail即可
            if (result == null){
                result = tail = new ListNode(sum % 10);
            }else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            //对和取商，计算进位
            carry = sum / 10;
            //如果链表不为null，则取下一个链表赋值
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //最后一位如果存在进位，则设置最后1位
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return result;
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
