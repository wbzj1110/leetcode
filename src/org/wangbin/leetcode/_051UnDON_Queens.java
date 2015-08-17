package org.wangbin.leetcode;

import java.util.ArrayList;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * 
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * For example, There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [ [".Q..", // Solution 1 "...Q", "Q...", "..Q."],
 * 
 * ["..Q.", // Solution 2 "Q...", "...Q", ".Q.."] ]
 * 
 * @author wb
 * @date 2015-8-8 下午2:44:28
 */
public class _051UnDON_Queens {
	/**
	 * 参考的这个http://www.cnblogs.com/springfor/p/3870944.html
	 * 
	 */
	public ArrayList<String[]> solveNQueens(int n) {
        ArrayList<String[]> res = new ArrayList<String[]>();
        if(n<=0)
            return res;
            
        int [] columnVal = new int[n];
        
        DFS_helper(n,res,0,columnVal);
        return res;
    }
    
    public void DFS_helper(int nQueens, ArrayList<String[]> res, int row, int[] columnVal){
        if(row == nQueens){
            String[] unit = new String[nQueens];
            for(int i = 0; i < nQueens; i++){
                StringBuilder s = new StringBuilder();
                for(int j = 0; j < nQueens; j++){
                    if(j == columnVal[i])
                        s.append("Q");
                    else
                        s.append(".");
                }
                
                unit[i] = s.toString();
            }
            
            res.add(unit);
        }else{
            for(int i = 0; i < nQueens; i++){
                columnVal[row] = i;//(row,columnVal[row)==>(row,i)
                
                if(isValid(row,columnVal))
                    DFS_helper(nQueens, res, row+1, columnVal);
            }
        }
    }
    
    public boolean isValid(int row, int [] columnVal){
        for(int i = 0; i < row; i++){
            if(columnVal[row] == columnVal[i]
               ||Math.abs(columnVal[row]-columnVal[i]) == row-i)
               return false;
        }
        return true;
    }
}
