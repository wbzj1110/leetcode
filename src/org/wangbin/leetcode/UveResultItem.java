package org.wangbin.leetcode;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
 * 关键字逻辑结果保存。
 * @author wb
 * @date 2015-8-12 
 */
public class UveResultItem {
    //存储微博字段对应的关键词（高亮词）
    private Map<Long, List<String>> highlightMap;
    //存储关键词（高亮词）对应的具体的uve的结果
    private Map<String, String> highlightResultMap;
    public Map<String, String> getHighlightResultMap() {
        return highlightResultMap;
    }
    public void setHighlightResultMap(Map<String, String> highlightResultMap) {
        this.highlightResultMap = highlightResultMap;
    }
    public Map<Long, List<String>> getHighlightMap() {
        return highlightMap;
    }
    public void setHighlightMap(Map<Long, List<String>> highlightMap) {
        this.highlightMap = highlightMap;
    }
    @Override
    public String toString() {
        return "UveResultItem [highlightMap=" + toShowHighlightMap() + ", highlightResultMap=" + toShowHighlightResultMap() + "]";
    }
    private String toShowHighlightResultMap() {
        // TODO Auto-generated method stub
        if(MapUtils.isEmpty(highlightResultMap)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, String> entry :highlightResultMap.entrySet()){
            sb.append(entry.getKey()).append("   ").append(entry.getValue().toString()).append("  ");
        }
        return sb.toString();
    }
    private String toShowHighlightMap() {
        // TODO Auto-generated method stub
        if(MapUtils.isEmpty(highlightMap)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Long, List<String>> entry :highlightMap.entrySet()){
            sb.append(entry.getKey()).append("   ").append(entry.getValue().toString() + "\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) {
    	Map<String, String> highlightResultMap = null;
    	UveResultItem item = new UveResultItem();
    	item.setHighlightResultMap(highlightResultMap);
    	System.out.println(item.toString());
    	
	}
    
}
