package org.wangbin.leetcode;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height
 * balanced BST.
 * 
 * @author wb
 * @date 2015-9-19 上午9:19:31
 */
public class _109ConvertSortedListtoBinarySearchTree {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        System.out.println(new _109ConvertSortedListtoBinarySearchTree().sortedListToBST(node1));
    }
    
    private static ListNode h;
    /**
     * 思路就是从最下边的叶子节点一步步往上构建，当然前提是链表是有序递增
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        h = head;
        //获取链表的长度
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return sortedListToBST(0, len - 1);
    }
    /**
     * 递归到左子树的最左节点然后根然后右子树一遍遍递归
     * 这样就满足了BST的要求同时链表的特性也用到了
     * @param start
     * @param end
     * @return
     */
    private  TreeNode sortedListToBST(int start, int end) {
        if(start > end){
            return null;
        }
        int mid = (start+end)/2;
        TreeNode left = sortedListToBST(start, mid-1);
        TreeNode root = new TreeNode(h.val);
        h=h.next;
        root.left=left;
        TreeNode right = sortedListToBST(mid+1, end);
        root.right = right;
        return root;
    }
}
