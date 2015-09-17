package org.wangbin.leetcode;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author wb
 * @date 2015-9-17 上午9:11:22
 */
public class _106ConstructBinaryTreefromInorderandTraversal {
    public static void main(String[] args) {
        int[] inorder = new int[] {4, 2, 5, 1, 6, 3, 7};
        int[] postorder = new int[] {4, 5, 2, 6, 7, 3, 1};
        System.out.println(new _106ConstructBinaryTreefromInorderandTraversal().buildTree(inorder, postorder));
    }

    /**
     * 一样的道理，与Construct Binary Tree from Preorder and Inorder Traversal 不过是把先根遍历换为了后跟遍历
     * 
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }


    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // TODO Auto-generated method stub
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                index = i;
                break;
            }
        }
        int len = index - inStart;// 左子树
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorder, inStart, index - 1, postorder, postStart, postStart + len - 1);
        root.right = buildTree(inorder, index + 1, inEnd, postorder, postStart + len, postEnd - 1);

        return root;
    }
}
