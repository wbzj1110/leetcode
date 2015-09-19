package org.wangbin.leetcode;

import java.util.Arrays;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * @author wb
 * @date 2015-9-19 上午8:52:31
 */
public class _108ConvertSortedArraytoBinarySearchTree {
    public static void main(String[] args) {
        int []nums = new int[]{1,2,3,4,5};
        System.out.println(new _108ConvertSortedArraytoBinarySearchTree().sortedArrayToBST(nums));
    }

    /**
     * 构建平衡二叉树，选取中间位置作为根节点，然后构建左右子树。
     * 
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length==0){
            return null;
        }
        Arrays.sort(nums);
        TreeNode root = buildTree(nums,0,nums.length-1);
        return root;
    }

    private TreeNode buildTree(int[] nums, int left, int right) {
        // TODO Auto-generated method stub
        if(left > right){
            return null;
        }
        int mid = (left + right) /2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(nums, left, mid-1);
        node.right = buildTree(nums, mid+1, right);
        return node;
    }
}
