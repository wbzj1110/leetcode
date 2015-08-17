package org.wangbin.leetcode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 * 没啥难度
 * @author wb
 * @version 2015-7-31 上午9:33:47
 */
public class _021MergeTwoSortedLists {
	public static void main(String[] args) {
		//1 4 3
		ListNode node3 = new ListNode(3);
		ListNode node2 = new ListNode(4,node3);
		ListNode node1 = new ListNode(1,node2);
		//2 5 6
		ListNode node6 = new ListNode(6);
		ListNode node5 = new ListNode(5,node6);
		ListNode node4 = new ListNode(2,node5);
		System.out.println(new _021MergeTwoSortedLists().mergeTwoLists(node1, node4));
		
	}
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode p1 = l1;
		ListNode p2 = l2;
		ListNode result = new ListNode(0);
		ListNode p = result;
		while(p1!=null&&p2!=null){
			if(p1.val<=p2.val){
				p.next = p1;
				p1 = p1.next;
			}else{
				p.next = p2;
				p2 = p2.next;
			}
			p = p.next;
		}
		if(p1!=null){
			p.next=p1;
		}
		if(p2!=null){
			p.next=p2;
		}
		return result.next;
		
	}
}
