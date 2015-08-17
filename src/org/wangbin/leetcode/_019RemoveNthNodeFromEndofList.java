package org.wangbin.leetcode;

/**
 * Given a linked list, remove the nth node from the end of list and return its
 * head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes
 * 1->2->3->5. Note: Given n will always be valid. Try to do this in one pass.
 * 
 * @author wb
 * @version 2015-7-30 上午9:33:31
 */
public class _019RemoveNthNodeFromEndofList {
	public static void main(String[] args) {
//		ListNode node5 = new ListNode(5);
//		ListNode node4 = new ListNode(4, node5);
//		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(new _019RemoveNthNodeFromEndofList()
				.removeNthFromEnd(node1, 2));

	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(head==null||head.next==null){
			return null;
		}
		 ListNode fast = head;
		 ListNode slow = head;
		 //快的先走n
		 for(int i= 0;i <n ;i++){
			 fast = fast.next;
		 }
		 //截取倒着数最后一位
		 if(fast==null){
			 head = head.next;
			 return head;
		 }
		 while(fast.next!=null){
			 fast =fast.next;
			 slow = slow.next;
		 }
		 slow.next = slow.next.next;
		 return head;
	 }
	
}
