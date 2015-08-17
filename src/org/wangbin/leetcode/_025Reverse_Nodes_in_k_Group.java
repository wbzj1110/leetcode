package org.wangbin.leetcode;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and
 * return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end
 * should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example, Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * @author wb
 * @version 2015-8-1 下午6:11:35
 */
public class _025Reverse_Nodes_in_k_Group {
	public static void main(String[] args) {
		ListNode node7 = new ListNode(7);
		ListNode node6 = new ListNode(6,node7);
		ListNode node5 = new ListNode(5,node6);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);
//		System.out.println(new _025Reverse_Nodes_in_k_Group().reverseKGroup2(
//				node1, 3));
		
		System.out.println(new _025Reverse_Nodes_in_k_Group().reverseKGroup(
				node1, 3));

	}

	/**
	 * 2个位置 一个是要被交换的位置，一个是交换的位置 存储好了即可
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}
		//可能出现head的链表长度还没有k的长度长的情况。
		int len = getLength(head);
		if(len <k){
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = head;
		int count = 0;
		ListNode next = null;
		while (cur != null) {
			count++;
			next = cur.next;
			if (count % k == 0) {
				pre = reverse(pre, next);
			}
			cur = next;
		}
		return dummy.next;
	}
	private int getLength(ListNode head) {
		// TODO Auto-generated method stub
		int count = 0;
		ListNode p  =head;
		while(p!=null){
			p = p.next;
			count++;
		}
		return count;
	}

	/**
	 * 一步步的变化
	 * pre保持要变化的前一个位置，next记录交换变化2个元素的后一个元素的下一个元素
	 * 例如
	 * 0 1 2 3 4 5 6 7（0是我们自己打的标记）1 与3 要进行交换
	 * 那么0是pre  next是4
	 * last是1  cur是2
	 * 这个时候走循环，目的就是pre的next一定是cur，循环的终止条件是cur指向next
	 * 第一次循环，0 2 1 3 4 5 6 7
	 * last 是2 cur是3
	 * 第二次循环 0 3 2 1 4 5 6 7
	 * @param pre 移动2个元素第一元素之前的位置
	 * @param next 移动的2个元素第二个元素之后的位置
	 * @return
	 */
	private ListNode reverse(ListNode pre, ListNode next) {
		// TODO Auto-generated method stub
		ListNode last = pre.next;// where first will be doomed "last"
		ListNode cur = last.next;
		while (cur != next) {
			last.next = cur.next;
			cur.next = pre.next;
			pre.next = cur;
			cur = last.next;
//			System.out.println(pre);
		}
		return last;

	}
	
	public ListNode reverseKGroup2(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}
		//可能出现head的链表长度还没有k的长度长的情况。
		int len = getLength(head);
		if(len <k){
			return head;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode cur = dummy;
		int count = 0;
		ListNode next = null;
		while (cur != null) {
			count++;
			if (count % k == 0) {
				pre = reverse2(pre, next);
			}
			next = cur.next;
			cur = next;
		}
		return dummy.next;
	}
	/**
	 * 直接替换了
	 * @param pre 移动2个元素第一元素之前的位置
	 * @param pre2 移动的2个元素第二个元素之前的位置
	 * @return
	 */
	private ListNode reverse2(ListNode pre, ListNode pre2) {
		// TODO Auto-generated method stub
		ListNode temp =pre2.next.next;//记录移动第二个元素之后的位置
		ListNode secondToMove = pre2.next;//第二个要移动的元素
		ListNode firstToMove = pre.next;//第一个要移动的元素
		secondToMove.next = firstToMove.next;
		pre2.next = firstToMove;
		pre.next = secondToMove;
		firstToMove.next = temp;
		return firstToMove;
	}
}
