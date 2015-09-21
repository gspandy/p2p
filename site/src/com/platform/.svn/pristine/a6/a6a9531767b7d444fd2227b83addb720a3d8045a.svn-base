package com.platform.util;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

	private static int currentPageSize = 20; // 每页记录数
	private int currentPage = 1; // 当前页码
	private long totalRow = 0; // 记录总数 （总共多少行）
	private int first = 1; // 数据库查询的时候的初始行
	private int totalPage = 1; // 总页数

	/*
	 * 只有在分页查询的时候才能调用这个方法
	 */
	public static Map<String, Object> getMap(Map<String, Object> map, String currentPage, long totalRow, String pageSize) {
		PageUtil page = new PageUtil();
		int current = 1;
		if (StringUtil.isNumeric(currentPage)) {
			current = Integer.parseInt(currentPage);
		}
		if (StringUtil.isNumeric(pageSize)) {
			currentPageSize = Integer.parseInt(pageSize);
		}
		if (current < 1) {
			current = 1;
		}
		page.setCurrentPage(current);
		page.setTotalRow(totalRow);
		page.setPageSize(currentPageSize);
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		map.put("currentPage", current);
		map.put("first", page.getFirst());
		map.put("size", page.getPageSize());
		map.put("page", page);
		return map;
	}

	public static Map<String, Object> getMap(Map<String, Object> map, String currentPage, long totalRow) {
		return getMap(map, currentPage, totalRow, "20");
	}

	public int getPageSize() {
		return currentPageSize;
	}

	public void setPageSize(int pageSize) {
		this.currentPageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
	}

	public int getFirst() {
		this.first = (currentPage - 1) * currentPageSize;
		return this.first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getTotalPages() {
		this.totalPage = (int) Math.ceil((double) totalRow / (double) currentPageSize);
		if (this.totalPage == 0)
			return 1;
		return this.totalPage;
	}

	public void setTotalPages(int totalPages) {
		this.totalPage = totalPages;
	}

}