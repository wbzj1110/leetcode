package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [1,3,2].
 * 
 * Note: Recursive solution is trivial, could you do it iteratively?
 * 
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * 
 * OJ's Binary Tree Serialization: The serialization of a binary tree follows a level order
 * traversal, where '#' signifies a path terminator where no node exists below.
 * 
 * Here's an example: 1 / \ 2 3 / 4 \ 5 The above binary tree is serialized as
 * "{1,2,3,#,#,4,#,#,5}".
 * 
 * @author wb
 * @date 2015-9-15 上午8:15:34
 */
public class _094BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        
    }
    /**
     * 递归中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            return Collections.EMPTY_LIST;
        }
        List<Integer> result = new ArrayList<Integer>();
        helper(root,result);
        
        return result;
    }
    private void helper(TreeNode root, List<Integer> result) {
        // TODO Auto-generated method stub
        if(root==null){
            return;
        }
        helper(root.left, result);
        result.add(root.val);
        helper(root.right, result);
    }
    
    
    /**
     * 非递归中序遍历，利用栈
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        if(root==null){
            return null;
        }
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root!=null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }
}
