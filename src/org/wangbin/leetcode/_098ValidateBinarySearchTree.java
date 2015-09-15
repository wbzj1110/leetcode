package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key. The right
 * subtree of a node contains only nodes with keys greater than the node's key. Both the left and
 * right subtrees must also be binary search trees. confused what "{1,#,2,3}" means? > read more on
 * how binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-15 ����10:25:42
 */
public class _098ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(14);
        node.left.left = new TreeNode(1);
        System.out.println(new _098ValidateBinarySearchTree().isValidBST(node));
    }

    /**
     * ����ж��Ƿ��Ƕ��������������Ƚϱ��ķ��Ӿ��Ǳ������������ն������� ��΢�򵥵ķ������ǣ�ֱ���������������ǵ����ľ��ǡ�
     * 
     * ������ǵ����Ļ�˵������
     * 
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> allNode = new ArrayList<Integer>();
        helper(root, allNode);
        // �����Ŀ�и��ر���ĵ����ӣ�����[-2147483648,-2147483648],���Գ�ʼ����ʱ��Ҫע��
        int pre = Integer.MIN_VALUE;
        for (int i = 0, len = allNode.size(); i < len; i++) {
            Integer inVal = allNode.get(i);
            if (i == 0) {
                pre = inVal;
            } else {
                if (pre < inVal) {
                    pre = inVal;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private void helper(TreeNode root, List<Integer> allNode) {
        // �������
        if (root != null) {
            helper(root.left, allNode);
            allNode.add(root.val);
            helper(root.right, allNode);
        }
    }
    /**
     * �ڷ���֮ǰ�����ж�
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {  
        ArrayList<Integer> pre = new ArrayList<Integer>();  
        pre.add(null);  
        return helper2(root, pre);  
    }  
    private boolean helper2(TreeNode root, ArrayList<Integer> pre)  
    {  
        if(root == null)  
            return true; 
        
        boolean left = helper2(root.left,pre); 
        
        if(pre.get(pre.size()-1)!=null && root.val<=pre.get(pre.size()-1))  
            return false;  
        pre.add(root.val);  
        
        boolean right = helper2(root.right,pre);
        return left && right;  
    }
    /**
     * ���߰��ն��������
     */
    public boolean isValidBST2(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBST(TreeNode node, int low, int high) {
        if (node == null) {
            return true;
        }
        if (low < node.val && node.val < high) {
            return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);
        } else {
            return false;
        }
    }
}
