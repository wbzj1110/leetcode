package org.wangbin.leetcode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 * 
 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3,
 * return 2->3.
 * 
 * @author wb
 * @date 2015-8-16 上午10:21:42
 */
public class _082RemoveDuplicatesfromSortedListII {
	public static void main(String[] args) {
		// ListNode n5 = new ListNode(3);
		// ListNode n4 = new ListNode(3, n5);
		// ListNode n3 = new ListNode(2, n4);
		// ListNode n2 = new ListNode(1, n3);
		// ListNode n1 = new ListNode(1, n2);
		
//		ListNode n3 = new ListNode(2);
//		ListNode n2 = new ListNode(2,n3);
//		ListNode n1 = new ListNode(1, n2);
		
//		ListNode n2 = new ListNode(1);
//		ListNode n1 = new ListNode(1, n2);
		
		ListNode n4 = new ListNode(2);
		ListNode n3 = new ListNode(2,n4);
		ListNode n2 = new ListNode(1,n3);
		ListNode n1 = new ListNode(1, n2);
		System.out.println(new _082RemoveDuplicatesfromSortedListII()
				.deleteDuplicates(n1));
	}

	/**
	 * 快慢指针，不一样的时候，slow直接跑到fast的next
	 * 
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		//因为一开始的都可能删除
		ListNode result = new ListNode(-1);
		result.next = head;
		ListNode slow = result;
		ListNode fast = result.next;
		while(fast!=null&&fast.next!=null){
			if(fast.val == fast.next.val){
				while(fast!=null && fast.val==slow.next.val){
					fast =fast.next;
				}
				slow.next = fast;
			}else{
				slow = slow.next;
				fast = fast.next;
			}
		}
		return result.next;
	}
	/**
	 * 不用2个指针
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode result = new ListNode(0);
		result.next = head;
		ListNode node = result;
		while (node.next != null && node.next.next != null) {
			if (node.next.val == node.next.next.val) {
				int val = node.next.val;
				while (node.next != null && node.next.val == val) {
					node.next = node.next.next;
				}
			} else {
				node = node.next;
			}
		}

		return result.next;
	}
}
