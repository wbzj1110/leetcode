package org.wangbin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example, Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 * 1 3 3 2 1 \ / / / \ \ 3 2 1 1 3 2 / / \ \ 2 1 2 3 confused what "{1,#,2,3}" means? > read more on
 * how binary tree is serialized on OJ.
 * 
 * 
 * OJ's Binary Tree Serialization: The serialization of a binary tree follows a level order
 * traversal, where '#' signifies a path terminator where no node exists below.
 * 
 * Here's an example: 1 / \ 2 3 / 4 \ 5 The above binary tree is serialized as
 * "{1,2,3,#,#,4,#,#,5}".
 * 
 * @author wb
 * @date 2015-9-15 ����8:51:39
 */
public class _095UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        List<TreeNode> result = new _095UniqueBinarySearchTreesII().generateTrees(3);
        for(TreeNode tree:result){
            System.out.println(tree);
        }
    }

    /**
     * �����Ŀ��������bst�Ķ��壺 ������Ϊ 1��2��3��4��.. i��.. nʱ����������ԭ���BST��������Ψһ�ԣ� ��iΪ���ڵ����������������[1, i-1]���ɣ�
     * ����������[i+1, n]���ɡ�
     * �ݹ�1������n
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generator(1,n);//��1��Ϊroot��ʼ����n��Ϊroot����
    }

    private List<TreeNode> generator(int left, int right) {
        // TODO Auto-generated method stub
        List<TreeNode> res = new ArrayList<TreeNode>();
        if (left > right){
            res.add(null);//û�н����
            return res;
        }
        for(int i = left;i <= right ;i++){
            //��i��Ϊ���ڵ㣬��������[1,i-1]����
            List<TreeNode> lefts = generator(left, i-1);
            //��������[i+1, n]����
            List<TreeNode> rights = generator(i+1,right);
            //Ȼ��ϳ�һ����
            for(int m = 0;m < lefts.size();m++){
                for(int n = 0; n < rights.size();n++){
                    TreeNode root = new TreeNode(i);
                    root.left = lefts.get(m);
                    root.right = rights.get(n);
                    res.add(root);
                }
            }
        }
        return res;
    }
}
