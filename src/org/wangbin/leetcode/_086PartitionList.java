package org.wangbin.leetcode;

/**
 * Given a linked list and a value x, partition it such that all nodes less than
 * x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the
 * two partitions.
 * 
 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
 * 
 * @author wb
 * @date 2015-8-19 上午8:46:20
 */
public class _086PartitionList {
	public static void main(String[] args) {
		ListNode node6 = new ListNode(2);
		ListNode node5 = new ListNode(5, node6);
		ListNode node4 = new ListNode(2, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(4, node3);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(new _086PartitionList().partition(node1, 3));
	}

	/**
	 * 这题目不难，但是麻烦在理解题意。。想了半天都不懂为啥会出现4 3 5 这个奇怪的排序。。囧 多读题目，那啥。。partition it such
	 * that all nodes less than x come before nodes greater than or equal to x.
	 * 题目的意思是大于等于的不动，小于的动。。好吧 我就这么理解了。2个链表一个记录小的，一个记录后边滴~然后组合
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode small = new ListNode(-1);
		ListNode smListNode = small;//小的头
		ListNode big = new ListNode(-1);
		ListNode bgListNode = big;
		while(head!=null){
			if(head.val <x){
				small.next = head;
				small = small.next;
			}else{
				big.next = head;
				big = big.next;
			}
			head = head.next;
		}
		big.next = null;
		small.next = bgListNode.next;
		return smListNode.next;
	}
}
