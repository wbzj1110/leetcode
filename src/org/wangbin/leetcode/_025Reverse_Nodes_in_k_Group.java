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
 * @version 2015-8-1 ����6:11:35
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
	 * 2��λ�� һ����Ҫ��������λ�ã�һ���ǽ�����λ�� �洢���˼���
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}
		//���ܳ���head�������Ȼ�û��k�ĳ��ȳ��������
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
	 * һ�����ı仯
	 * pre����Ҫ�仯��ǰһ��λ�ã�next��¼�����仯2��Ԫ�صĺ�һ��Ԫ�ص���һ��Ԫ��
	 * ����
	 * 0 1 2 3 4 5 6 7��0�������Լ���ı�ǣ�1 ��3 Ҫ���н���
	 * ��ô0��pre  next��4
	 * last��1  cur��2
	 * ���ʱ����ѭ����Ŀ�ľ���pre��nextһ����cur��ѭ������ֹ������curָ��next
	 * ��һ��ѭ����0 2 1 3 4 5 6 7
	 * last ��2 cur��3
	 * �ڶ���ѭ�� 0 3 2 1 4 5 6 7
	 * @param pre �ƶ�2��Ԫ�ص�һԪ��֮ǰ��λ��
	 * @param next �ƶ���2��Ԫ�صڶ���Ԫ��֮���λ��
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
		//���ܳ���head�������Ȼ�û��k�ĳ��ȳ��������
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
	 * ֱ���滻��
	 * @param pre �ƶ�2��Ԫ�ص�һԪ��֮ǰ��λ��
	 * @param pre2 �ƶ���2��Ԫ�صڶ���Ԫ��֮ǰ��λ��
	 * @return
	 */
	private ListNode reverse2(ListNode pre, ListNode pre2) {
		// TODO Auto-generated method stub
		ListNode temp =pre2.next.next;//��¼�ƶ��ڶ���Ԫ��֮���λ��
		ListNode secondToMove = pre2.next;//�ڶ���Ҫ�ƶ���Ԫ��
		ListNode firstToMove = pre.next;//��һ��Ҫ�ƶ���Ԫ��
		secondToMove.next = firstToMove.next;
		pre2.next = firstToMove;
		pre.next = secondToMove;
		firstToMove.next = temp;
		return firstToMove;
	}
}
