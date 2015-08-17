package org.wangbin.leetcode;

public class ListNode {
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	int val;
	ListNode next;
	public ListNode(int val){
		this.val = val;
		this.next = null;
	}
	@Override
	public String toString() {
		return "ListNode [val=" + val + ", node=" + next + "]";
	}
	public ListNode(int val,ListNode next){
		this.val = val;
		this.next = next;
	}
	
}
