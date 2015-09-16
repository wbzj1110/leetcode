package org.wangbin.leetcode;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note: A solution using O(n) space is pretty straight forward. Could you devise a constant space
 * solution? confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 * @author wb
 * @date 2015-9-16 ����9:56:11
 */
public class _099RecoverBinarySearchTree {
    public static void main(String[] args) {

    }

    /**
     * ����ֻ��Ҫһ��ȫ�ֱ�����¼�����2���ڵ㼴�ɣ� ֻ�����и��޸��߼���������Ҫÿ���滻�������еĵڶ����� Ϊɶ�ǵڶ�����������1��4��3,2,5,6
     * ��Ҫһ��ͼ�������ˡ���Ȼ�ϸ����ѧ֤����򵥵ľ��Ƿ�֤����
     * 
     * @param root
     */
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;

    public void recoverTree(TreeNode root) {
        // ������rootΪnull�����
        inOrder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inOrder(TreeNode root) {
        // TODO Auto-generated method stub
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (pre == null) {
            pre = root;// pre�ĳ�ʼ��
        } else {
            if (pre.val > root.val) {// ��������
                if (first == null) {
                    first = pre;// �ҵ���һ���������
                    second = root;
                } else {
                    second = root;//�ڶ����ҵ�����㣬����second
                }
            }
            pre = root;
        }
        inOrder(root.right);
    }
}
