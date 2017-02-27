package com.chao.helper.db.dao;

import com.chao.helper.db.query.Criteria;
import com.chao.helper.db.query.MySQLQuery;
import com.chao.helper.db.query.Page;
import com.chao.helper.db.query.Query;
import com.chao.helper.utils.DbUtilsTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author barry
 */
@SuppressWarnings({"unchecked","rawtypes", "hiding"})
public class BaseDao<T> {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private RowProcessor defaultBeanProcessor;

    public BaseDao() {
        BeanInfo beanInfo ;
        try {
            beanInfo = Introspector.getBeanInfo(getClassType());
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }

        Map<String, String> columnToPropertyOverrides = new HashMap<>();
        for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
            columnToPropertyOverrides.put("_"+ pd.getName(), pd.getName());
        }
        defaultBeanProcessor = new BasicRowProcessor(new BeanProcessor(columnToPropertyOverrides));
    }


    @Autowired
    private DataSource dataSource;
    @Autowired
    protected DbUtilsTemplate dbUtilsTemplate;

    public DataSource getDataSource(){
        return dataSource;
    }

    protected Class getClassType() {
        Type superClass = this.getClass().getGenericSuperclass();
        return (Class)((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    /**
     * load entity
     * @param sql The SQL to execute.
     * @param params The SQL to Parameter.
     * @return Entity
     */
    protected T load(String sql, Object... params) {
        T result = null;
        try {
            QueryRunner run = new QueryRunner(getDataSource());
            LOG.debug("SQL:{}, Arguments:{}", sql, params);
            if (params == null) {
                result = run.query(sql, new BeanHandler<T>(getClassType(), defaultBeanProcessor));
            } else {
                result = run.query(sql, new BeanHandler<T>(getClassType(), defaultBeanProcessor), params);
            }
        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and
     * Convert the first row of the ResultSet into a bean with the Class given
     * in the parameter.
     *
     * Usage Demo:
     *
     * <pre>
     * String sql = &quot;SELECT * FROM test&quot;;
     * Test test = (Test) searchToBean(Test.class, sql);
     * if (test != null) {
     *
     * }
     * </pre>
     *
     * @param sql
     *            The SQL to execute.
     * @param params
     *            The SQL to Parameter.
     * @return An initialized JavaBean or null if there were no rows in the
     *         ResultSet.
     */
    protected <T> T findOneBy(String sql, Object... params) {
        T result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, Arguments:{}", sql, params);
            if (params == null) {
                result = run.query(sql, new BeanHandler<T>(getClassType(), defaultBeanProcessor));
            } else {
                result = run.query(sql, new BeanHandler<T>(getClassType(), defaultBeanProcessor), params);
            }
        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     *
     * @param sql
     *            The SQL to execute.
     * @param params
     *            The SQL to Parameter.
     * @return
     */
    protected List<T> findBy(String sql, Object... params) {
        List<T> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, Arguments:{}", sql, params);
            if (params == null) {
                result = run.query(sql, new BeanListHandler<T>(getClassType(), defaultBeanProcessor));
            } else {
                result = run.query(sql, new BeanListHandler<T>(getClassType(), defaultBeanProcessor), params);
            }
        } catch (Exception _e) {
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * 执行更新数据语句
     * @param sql
     * @param params
     * @return
     */
    protected void update(String sql, Object... params) {
        try {
            QueryRunner run = new QueryRunner(getDataSource());
            LOG.debug("SQL:{}, Arguments:{}", sql, params);
            run.update(sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入数据库，返回自动增长的主键
     *
     * @param sql -
     *            执行的sql语句
     * @param params
     *            执行参数
     * @return 主键
     */
    public Number insertForKeys(String sql, Object... params) {
        Number key = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ParameterMetaData pmd = stmt.getParameterMetaData();
            int stmtCount = pmd.getParameterCount();
            int paramsCount = params == null ? 0 : params.length;

            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }

            LOG.debug("SQL:{}, Arguments:{}", sql, params);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    stmt.setObject(i + 1, params[i]);
                }
            }
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                key = (Number) rs.getObject(1);
            }
        } catch (Exception _e) {
            LOG.error("execute sql exception! reason:{}", sql, _e);
            throw new RuntimeException(_e);
        } finally {
            if (rs != null) { // 关闭记录集
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // 关闭声明
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return key;
    }
    
    /**==============================  分页查询方法   ============================*/
    
    /**
     * 分页查询数据
     * @param clazz
     * @param sql 执行的sql
     * @param page 分页参数实体
     * @return Page
     * @throws Exception
     */
    public Page searchPageToBean(Class<?> clazz, String sql, Page page) {
		Object[] params = null;
		// 动态拼接参数
		Map<String, Object> map = dynamicWhere(page.getParamsList());
		// 追加查询条件
		if(MapUtils.isNotEmpty(map)){
			params = (Object[]) map.get("params");
			sql += " WHERE " + map.get("condition");
		}
		// 追加分组
		sql += getSQLToGroup(page.getGroup());
		// 查询记录数sql
		String totalSql = getSQLToTotal(sql);
		// 回填数据，留作他用
		page.setExecuteSql(sql);
		page.setParams(params);
		// 追加排序
		sql += getSQLToOrder(page.getOrderBy(), page.getOrder());
		
		Query query = createSQLQuery(clazz, sql);
		query.setFirstResult(page.getFirst());
		query.setMaxResults(page.getPageSize());
		if(null != params){
			page.setRows(query.searchToBeanList(params));
			page.setTotal(dbUtilsTemplate.searchTotal(totalSql, params));
		}else{
			page.setRows(query.searchToBeanList());
			page.setTotal(dbUtilsTemplate.searchTotal(totalSql));
		}
		return page;
	}
    
    /**
     * 新创建方法分页查询数据
     * 说明：page中的sql是完整的，本方法中不会在加工，params是sql中对应的?值
     * sql和params都需要在controller里拼接处理完成
     * @param sql 完整的sql，包含where条件
     * @param clazz
     * @param page 分页参数实体
     * @return Page
     * @throws Exception
     */
    public Page searchPageToBeanBySql(Class<?> clazz, String sql, Page page) {
		// 追加分组
		sql += getSQLToGroup(page.getGroup());
		// 查询记录数sql
		String totalSql = getSQLToTotal(sql);
		// 回填数据，留作他用
		page.setExecuteSql(sql);
		Object[] params = page.getParams();
		// 追加排序
		sql += getSQLToOrder(page.getOrderBy(), page.getOrder());
		
		Query query = createSQLQuery(clazz, sql);
		query.setFirstResult(page.getFirst());
		query.setMaxResults(page.getPageSize());
		if(null != params){
			page.setRows(query.searchToBeanList(params));
			page.setTotal(dbUtilsTemplate.searchTotal(totalSql, params));
		}else{
			page.setRows(query.searchToBeanList());
			page.setTotal(dbUtilsTemplate.searchTotal(totalSql));
		}
		return page;
	}
    
    
    /**
     * 统计
     * @param sql
     * @param params
     * @return
     */
    public Number sumData(String sql, Object[] params) {
    	if(null != params){
    		return  dbUtilsTemplate.sumData(sql, params);
    	}
		return  dbUtilsTemplate.sumData(sql);
    }
    
    /**
     * sql追加排序
     * @param orderBy 排序字段
     * @param order 排序方向
     * @return String
     */
    protected String getSQLToOrder(String orderBy, String order){
    	String sql = "";
    	if(StringUtils.isNotEmpty(orderBy) && orderBy.trim().length() > 0){
			sql += " ORDER BY " + orderBy.trim();
			// 排序方式
			if(StringUtils.isNotEmpty(order) && order.trim().length() > 0){
				sql += " " + order.trim();
			}
		}
    	return sql;
    }
    
    /**
     * sql追加分组
     * @param group 分组字段
     * @return String
     */
    protected String getSQLToGroup(String group) {
    	String sql = "";
    	if(StringUtils.isNotEmpty(group) && group.trim().length() > 0){
			sql += " GROUP BY " + group.trim();
		}
    	return sql;
    }
    
    
    /**
     * 将sql转为查询记录数sql
     * @param sql 
     * @return String
     */
    protected String getSQLToTotal(String sql){
    	return "SELECT COUNT(1) FROM (" + sql + ") AS T";
    }
    
    
    
    /**
	 * 创建mysql分页查询器
	 * @Description: 创建mysql分页查询器 
	 * @author zhangYuanHui
	 * @param clazz 实体类的class
	 * @param sql 执行的sql
	 * @return SQLMySQLQuery
	 */
	public MySQLQuery createSQLQuery(Class<?> clazz, String sql){
		return new MySQLQuery(dbUtilsTemplate, sql, clazz);
	}
    
    /**
     * 动态拼接查询条件
     * @param params where条件数组
     * @return Map<String, Object> key:condition(String)  key:params(数组)
     */
	public Map<String, Object> dynamicWhere(List<Criteria> params){
		if(CollectionUtils.isNotEmpty(params)){
			// 返回值
			Map<String, Object> map = new HashMap<String, Object>(2);
			// 拼接条件的buff
			StringBuffer condition = new StringBuffer();
			// 参数值
			List<Object> paramList = new ArrayList<>();
			Criteria criteria;
			for(int i = 0, size = params.size(); i < size; i++){
				criteria = params.get(i);
				// in条件拼接
				if("IN".equalsIgnoreCase(criteria.getOperator()) || "NOT IN".equalsIgnoreCase(criteria.getOperator())){
					condition.append(" AND ").append(criteria.getName()).append(" " + criteria.getOperator());
					StringBuffer buf = new StringBuffer();
					if(criteria.getValue().getClass().isArray()){ // 数组
						Object[] obj = (Object[]) criteria.getValue();
						for(int j = 0, len = obj.length; j < len; j++){
							buf.append("?,");
							paramList.add(obj[j]);
						}
					}else{ // array
						List<Object> list = (List<Object>) criteria.getValue();
						for(int j = 0, len = list.size(); j < len; j++){
							buf.append("?,");
							paramList.add(list.get(j));
						}
					}
					condition.append(" (").append(buf.substring(0, buf.length() - 1)).append(")");
				}else if("OR".equalsIgnoreCase(criteria.getOperator())){ // or条件拼接
					// 递归调取
					Map<String, Object> temp = dynamicWhere(criteria.getParamCriteriaList());
					if(MapUtils.isNotEmpty(temp)){
						if(criteria.getParamCriteriaList().size() > 1){
							condition.append(" OR (").append(temp.get("condition")).append(")");
						}else{
							condition.append(" OR ").append(temp.get("condition"));
						}
						paramList.addAll((List<Object>)temp.get("paramList"));
					}
				}else if("AND".equalsIgnoreCase(criteria.getOperator())){ // and or条件拼接
					// 递归调取
					Map<String, Object> temp = dynamicWhere(criteria.getParamCriteriaList());
					if(MapUtils.isNotEmpty(temp)){
						if(criteria.getParamCriteriaList().size() > 1){
							condition.append(" AND (").append(temp.get("condition")).append(")");
						}else{
							condition.append(" AND ").append(temp.get("condition"));
						}
						paramList.addAll((List<Object>)temp.get("paramList"));
					}
				}else if("IS NULL".equalsIgnoreCase(criteria.getOperator()) || "IS NOT NULL".equalsIgnoreCase(criteria.getOperator())){
					condition.append(" AND ").append(criteria.getName()).append(" " + criteria.getOperator());
				}else{
					condition.append(" AND ").append(criteria.getName()).append(" " + criteria.getOperator()).append(" ?");
					paramList.add(criteria.getValue());
				}
			}
			// 替换掉第一个 AND 关键字
			map.put("paramList", paramList);
			map.put("condition", condition.toString().replaceFirst(" AND ", ""));
			map.put("params", paramList.toArray());
			return map;
		}
		return null;
	}

	
	/**
	 * Executes the given SELECT SQL query and Convert the ResultSet rows into a
	 * List of beans with the Class given in the parameter.
	 * 
	 * @param type
	 *            The Class that objects returned from handle() are created
	 *            from.
	 * @param sql
	 *            The SQL to execute.
	 * @param params
	 *            Initialize the PreparedStatement's IN parameters with this
	 *            array.
	 * @return A List of beans (one for each row), never null.
	 */	
	public <T> List<T> searchToBeanList(Class type, String sql, Object... params) {
		List<T> result = null;
		try {
			QueryRunner run = new QueryRunner(dataSource);
			LOG.debug("SQL:{}, 参数：{}", sql, params);
			result = run.query(sql, new BeanListHandler<T>(type), params);
		} catch (Exception _e) {
			throw new RuntimeException(_e);

		} 
		return result;
	}
	
	
}
