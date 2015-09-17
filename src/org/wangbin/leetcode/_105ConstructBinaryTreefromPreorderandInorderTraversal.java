package org.wangbin.leetcode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note: You may assume that duplicates do not exist in the tree.
 * 
 * @author wb
 * @date 2015-9-17 上午8:47:46
 */
public class _105ConstructBinaryTreefromPreorderandInorderTraversal {
    public static void main(String[] args) {
        int []preOrder = new int[]{1,2,4,5,3,6,7};
        int []inOrder = new int[]{4,2,5,1,6,3,7};
        System.out.println(new _105ConstructBinaryTreefromPreorderandInorderTraversal().buildTree(preOrder, inOrder));
    }
    /**
     * 依据先根中跟遍历构建二叉树。
     * 推理过程蛮简单的，过程参考，http://www.cnblogs.com/springfor/p/3884034.html
     * 其实基本上只要自己画画即可
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder,0,preorder.length-1 ,inorder,0,inorder.length-1);
    }
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // TODO Auto-generated method stub
        if(preStart > preEnd || inStart > inEnd){
            return null;
        }
        int rootVal = preorder[preStart];
        int index = 0;
        //在中跟遍历中找到rootVal的位置，然后inStart到这个位置中间的数就是左子树
        for(int i = inStart;i <= inEnd;i++){
            //一定能找到
            if(rootVal == inorder[i]){
                index= i;
                break;
            }
        }
        int len = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, preStart+1, preStart+len, inorder, inStart, index-1);
        root.right=buildTree(preorder, preStart+len+1, preEnd, inorder, index+1, inEnd);
        return root;
    }
}
