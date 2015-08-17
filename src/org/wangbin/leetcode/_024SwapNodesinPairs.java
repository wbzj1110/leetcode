package org.wangbin.leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * 
 * @author wb
 * @version 2015-8-1 下午5:12:12
 */
public class _024SwapNodesinPairs {
	public static void main(String[] args) {
		ListNode node5 = new ListNode(5);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);
		System.out.println(new _024SwapNodesinPairs().swapPairs4(node1));
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null) {
			return null;
		}
		// 当只有一个元素的情况
		if (head.next == null) {
			return head;
		}
		ListNode i = head; // i指向第1个
		ListNode j = i.next; // j指向第2个
		ListNode k = j.next; // k指向第3个

		head = head.next;
		while (j != null) {
			j.next = i;
			if (k != null && k.next != null) { // 当有偶数个节点
				i.next = k.next;
			} else { // 当有奇数个节点
				i.next = k;
			}

			// 更新i，j，k的值，前进两格
			i = k;
			if (k != null) {
				j = k.next;
			} else {
				j = null;
			}
			if (k != null && k.next != null) {
				k = k.next.next;
			} else {
				k = null;
			}
		}
		return head;
	}

	/**
	 * 需要运用fakehead来指向原指针头，防止丢链，用两个指针，ptr1始终指向需要交换的pair的前面一个node，
	 * ptr2始终指向需要交换的pair的第一个node。
	 * 
	 * 然后就是进行链表交换。
	 * 
	 * 需要用一个临时指针nextstart， 指向下一个需要交换的pair的第一个node，保证下一次交换的正确进行。
	 * 
	 * 然后就进行正常的链表交换，和指针挪动就好。
	 * 
	 * 当链表长度为奇数时，ptr2.next可能为null；
	 * 
	 * 当链表长度为偶数时，ptr2可能为null。
	 * 
	 * 所以把这两个情况作为终止条件，在while判断就好，最后返回fakehead.next。 看为一直是第一个与第二个元素交换，第一个元素自增。
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs2(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode fakehead = new ListNode(-1);
		fakehead.next = head;

		ListNode ptr1 = fakehead;// 交换的元素的前一个
		ListNode ptr2 = head;// 要交换的元素的第一个

		while (ptr2 != null && ptr2.next != null) {
			ListNode nextstart = ptr2.next.next;// 需要用一个临时指针nextstart，
												// 指向下一个需要交换的pair的第一个node
			ptr2.next.next = ptr2;// 变为环路
			ptr1.next = ptr2.next;// 之前的指向要交换元素的第二个
			ptr2.next = nextstart;
			ptr1 = ptr2;
			ptr2 = ptr2.next;
		}
		return fakehead.next;

	}

	public ListNode swapPairs3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode point = new ListNode(0);
		point.next = head;//point是指向交换位置之前的位置
		head = point;
		while (point.next != null && point.next.next != null) {
			ListNode tmp = point.next.next.next;// 记录下一个要交换的位置
			point.next.next.next = point.next;// 形成环，比如1,2,3,4 链接，变成了1,2,1,2
			point.next = point.next.next;// 利用环路，point指向的是交换前一个位置，那么就成为了2,1,2,1
			point.next.next.next = tmp;// 然后就成了2,1,3,4了
			point = point.next.next;// 指向1号位置。
		}
		return head.next;
	}

	public ListNode swapPairs4(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode point = new ListNode(0);
		point.next = head;
		head = point;
		ListNode temp = null;
		while (point.next != null && point.next.next != null) {
			temp = point.next.next.next;
			point.next.next.next = point.next;
			point.next = point.next.next;
			point.next.next.next = temp;
			point = point.next.next;

		}
		return head.next;
	}
}
