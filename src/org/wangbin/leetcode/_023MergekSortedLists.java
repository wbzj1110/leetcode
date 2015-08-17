package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.google.common.collect.Lists;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 * 
 * @author wb
 * @version 2015-8-1 下午4:14:14
 */
public class _023MergekSortedLists {
	public static void main(String[] args) {
		// 1 4 3
		ListNode node3 = new ListNode(3);
		ListNode node2 = new ListNode(4, node3);
		ListNode node1 = new ListNode(1, node2);
		
//		// 2 5 6
		ListNode node6 = new ListNode(6);
		ListNode node5 = new ListNode(5, node6);
		ListNode node4 = new ListNode(2, node5);
		//7
		ListNode node7 = new ListNode(7);
		ListNode []ls = new ListNode []{node1,node4,node7};
//		ListNode node1 = new ListNode(1);
//		ListNode node0 = new ListNode(0);
//		ListNode []ls = new ListNode []{node1,node0};
		ListNode result = new _023MergekSortedLists().mergeKLists2(ls);
		System.out.println(result);
	}

	/**
	 * 先把所有的元素拿出来排序然后再变为链接 排序用priorityQueue构建最小堆
	 * 
	 * @param lists
	 * @return
	 */
	public  ListNode mergeKLists(ListNode[] lists) {
		if(lists==null|| lists.length==0){
			return null;
		}
		if(lists.length==1){
			return lists[0];
		}
		PriorityQueue<ListNode> p = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {

			@Override
			public int compare(ListNode o1, ListNode o2) {
				// TODO Auto-generated method stub
				if(o1.val>o2.val){
					return 1;
				}else if(o1.val == o2.val){
					return 0;
				}else{
					return -1;
				}
				
			}});
		for(ListNode node:lists){
			if(node!=null){
				p.add(node);
			}
		}
		ListNode head =new ListNode(0);
		ListNode result = head;
		ListNode temp = null;
		while(p.size()>0){
			temp = p.poll();
			result.next = temp;
			//temp后续如果还有链接 需要继续放
			if(temp.next!=null){
				p.add(temp.next);
			}
			result = result.next;
		}
		
		return head.next;
	}
	
	/**
	 * 递归拆分 每次2个合并为1个。
	 * @param lists
	 * @return
	 */
	public  ListNode mergeKLists2(ListNode[] lists) {
		if(lists==null||lists.length==0){
			return null;
		}
		if(lists.length==1){
			return lists[0];
		}
		return mergeAnction(lists,0,lists.length-1);
	}
	/**
	 * 递归合并
	 * @param lists
	 * @param i
	 * @param j
	 * @return
	 */
	private ListNode mergeAnction(ListNode[] lists, int start, int end) {
		// TODO Auto-generated method stub
		if(start == end){
			return lists[start];
		}
		int mid = (start + end)/2;//中间就是start + (end -start)/2
		ListNode left= mergeAnction(lists, start, mid);
		ListNode right = mergeAnction(lists, mid+1, end);
		return mergeTwoLists(left, right);
	}

	private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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
