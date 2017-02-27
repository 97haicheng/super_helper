package com.chao.helper.db.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: Page
 * @Description: 分页工具类
 * @author zhangYuanHui
 */
@SuppressWarnings("rawtypes")
public class Page {
	// 公共变量
	public static final String ASC = "ASC";

	public static final String DESC = "DESC";

	// 每页最小多少行记录
	public static final int MIN_PAGESIZE = 1;

	// 每页最大多少行记录不做限制
	public static final int MAX_PAGESIZE = 1000;

	// 分页参数
	private int pageNo = 1;

	// 每页多少条数据
	private int pageSize = MIN_PAGESIZE;

	// 排序字段
	private String orderBy = null;

	// 排序方式，升序或降序（asc|desc）
	private String order = null;
	
	// 分组字段
	private String group = null;

	// 总记录数据
	private long total = 0;

	// 查询结果集
	private List rows;

	// 参数集合
	private List<Criteria> paramList = new ArrayList<>();
	
	/**
	 * 合计
	 */
	private Number sumMation;
	
	/**
	 * 是否合计
	 */
	private boolean whetherSumMation;
	
	/**
	 * 执行查询后的sql
	 */
	private String executeSql;
	
	/**
	 * 执行sql的参数
	 */
	private Object[] params;
	
	/**
	 * 请求状态码，1代表成功
	 */
	private int code;

	/**
	 * 设置分页参数
	 * 
	 * @param pageNo 当前页码
	 * @param pageSize 每页记录数
	 */
	public Page(int pageNo, int pageSize) {
		setPageSize(pageSize);
		setPageNo(pageNo);
	}
	
	/**
	 * 设置分页参数
	 * 
	 * @param pageNo 当前页码
	 * @param pageSize 每页记录数
	 * @param whetherSumMation 是否合计数据，用于报表统计金额
	 */
	public Page(int pageNo, int pageSize, boolean whetherSumMation) {
		setPageSize(pageSize);
		setPageNo(pageNo);
		this.whetherSumMation = whetherSumMation;
	}

	/**
	 * 设置分页参数
	 * 
	 * @param rowIndex 数据库中的记录索引位置
	 * @param pageSize 每页记录数
	 * @param whetherSumMation 是否合计数据，用于报表统计金额
	 */
	public Page(String rowIndex, int pageSize, boolean whetherSumMation) {
		setPageSize(pageSize);
		setPageNo((Integer.parseInt(rowIndex) / pageSize) + 1);
		this.whetherSumMation = whetherSumMation;
	}
	
	/**
	 * 设置分页参数
	 * 
	 * @param rowIndex 数据库中的记录索引位置
	 * @param pageSize 每页记录数
	 */
	public Page(String rowIndex, int pageSize) {
		setPageSize(pageSize);
		setPageNo((Integer.parseInt(rowIndex) / pageSize) + 1);
	}

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	@JsonIgnore // 转为json时忽略
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;
		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量,默认为10.
	 */
	@JsonIgnore // 转为json时忽略
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,超出MIN_PAGESIZE与MAX_PAGESIZE范围时会自动调整.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
		if (pageSize < MIN_PAGESIZE) {
			this.pageSize = MIN_PAGESIZE;
		}
		if (pageSize > MAX_PAGESIZE) {
			this.pageSize = MAX_PAGESIZE;
		}
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从0开始.
	 */
	@JsonIgnore // 转为json时忽略
	public int getFirst() {
		return ((pageNo - 1) * pageSize);
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔Criterion查询时有效.
	 */
	@JsonIgnore // 转为json时忽略
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段.多个排序字段时用','分隔.仅在Criterion查询时有效.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 获得排序方向,默认为asc
	 * 
	 * @param   可选值为desc或asc,多个排序字段时用','分隔.
	 */
	@JsonIgnore // 转为json时忽略
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) throws Exception {
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.upperCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)){
				throw new IllegalArgumentException("排序方向" + orderStr + "不时合法值");
			}
		}
		this.order = StringUtils.upperCase(order);
	}
	
	/**
	 * 多个分组字段时用','分隔
	 * @return
	 */
	@JsonIgnore // 转为json时忽略
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * 取得总记录数,默认值为0.
	 */
	public long getTotal() {
		return total;
	}

	public void setTotal(final long total) {
		this.total = total;
	}

	/**
	 * 根据pageSize与total计算总页数,默认值为-1.
	 */
	@JsonIgnore // 转为json时忽略
	public int getTotalPages() {
		if (total <= 0) {
			return 1;
		}
		int count = (int) (total / pageSize);
		if (total % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 时否还有下一页.
	 */
	@JsonIgnore // 转为json时忽略
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号,序号从1开始.
	 */
	@JsonIgnore // 转为json时忽略
	public int getNextPage() {
		return isHasNext() ? pageNo + 1 : pageNo;
	}

	/**
	 * 时否还有上一页.
	 */
	@JsonIgnore // 转为json时忽略
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号,序号从1开始.
	 */
	@JsonIgnore // 转为json时忽略
	public int getPrePage() {
		return isHasPre() ? pageNo - 1 : pageNo;
	}

	public List getRows() {
		return CollectionUtils.isNotEmpty(rows) ? rows : new ArrayList<>();
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	@JsonIgnore // 转为json时忽略
	public List<Criteria> getParamsList() {
		return paramList;
	}

	/**
	 * 添加查询条件,支持 =、>、>=、<、<=、or、in、like、not in
	 * 
	 * @Description: 添加查询条件,支持 =、>、>=、<、<=、or、in、like、not in
	 * @author zhangYuanHui
	 * @param criteria 查询条件构造器 通过Restrictions工具类获得
	 */
	public void setCriteriaParams(Criteria criteria) {
		if (null != criteria) {
			paramList.add(criteria);
		}
	}
	
	/**
	 * @param rowIndex 数据库表记录下表索引
	 */
	public void setRowIndex(int rowIndex) {
		setPageNo((rowIndex / pageSize) + 1);
	}

	public Number getSumMation() {
		return null != sumMation ? sumMation : 0;
	}

	public void setSumMation(Number sumMation) {
		this.sumMation = sumMation;
	}
	
	@JsonIgnore // 转为json时忽略
	public boolean isWhetherSumMation() {
		return whetherSumMation;
	}

	/**
	 *  @param whetherSumMation 是否合计数据，用于报表统计金额
	 */
	public void setWhetherSumMation(boolean whetherSumMation) {
		this.whetherSumMation = whetherSumMation;
	}

	@JsonIgnore // 转为json时忽略
	public String getExecuteSql() {
		return executeSql;
	}

	public void setExecuteSql(String executeSql) {
		this.executeSql = executeSql;
	}

	@JsonIgnore // 转为json时忽略
	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
