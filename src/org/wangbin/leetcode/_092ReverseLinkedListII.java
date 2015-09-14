package org.wangbin.leetcode;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 
 * return 1->4->3->2->5->NULL.
 * 
 * Note: Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
 * 
 * @author wb
 * @date 2015-9-14 下午10:36:04
 */
public class _092ReverseLinkedListII {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(node1);
        node1 = new _092ReverseLinkedListII().reverseBetween(node1, 2, 4);
        System.out.println(node1);
    }
    /**
     * 经典链表逆转，其实就是找到开始位置，然后一个个逆转即可。
     * 逆转的方法就是找到一开的位置，依靠这个位置不停的逆转
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null ||m >=n) {
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode start = temp;
        ListNode node1 = null;
        ListNode node2 = null;
        int length = 1;
        for (int i = 0; i < n; i++) {
            if (i < m - 1) {
                start = start.next;// 找到开始反转滴位置
            } else if (i == m - 1) {// 这是第一个记录点，找到之后start的位置不变了
                node1 = start.next;
                node2 = node1.next;
            } else {
                //开始重新构建start的下一个。
                node1.next = node2.next;
                node2.next = start.next;
                start.next = node2;
                //node2继续下一个位置
                node2 = node1.next;
            }
        }

        return temp.next;
    }

}
