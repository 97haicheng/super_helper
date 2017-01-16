package com.chao.helper.utils;

/**
 * Created by think on 2017/1/16.
 */

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * <code>
 * private DbUtilsTemplate dbUtilsTemplate;
 * public void setDbUtilsTemplate(DbUtilsTemplate dbUtilsTemplate) {
 *     this.dbUtilsTemplate = dbUtilsTemplate;
 * }
 * </code>
 *
 * @author Sunshine
 * @version 1.0 2009-07-29
 */
public class DbUtilsTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(DbUtilsTemplate.class);

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and place the column values
     * from the first row in an Object[].
     *
     * Usage Demo:
     *
     * <pre>
     * Object[] result = searchToArray(sql);
     * if (result != null) {
     * 	for (int i = 0; i &lt; result.length; i++) {
     *
     * 	}
     * }
     * </pre>
     *
     * @param sql The SQL to execute.
     * @return An Object[] or null if there are no rows in the ResultSet.
     */
    public Object[] searchToArray(String sql) {
        Object[] result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result = (Object[]) run.query(sql, new ArrayHandler());
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }



    /**
     * Executes the given SELECT SQL query and place the column values from the first row in an
     * Object[].
     *
     * @param sql The SQL statement to execute.
     * @param params Initialize the PreparedStatement's IN parameters with this array.
     * @return An Object[] or null if there are no rows in the ResultSet.
     */
    public Object[] searchToArray(String sql, Object... params) {
        Object[] result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            result = (Object[]) run.query(sql, new ArrayHandler(), params);
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and place the ResultSet into a
     * List of Object[]s
     *
     * Usage Demo:
     *
     * <pre>
     *      ArrayList result = searchToArrayList(sql);
     *      Iterator iterator = result.iterator();
     *      while (iterator.hasNext()) {
     *          Object[] temp = (Object[])iterator.next();
     *          for (int i = 0; i &lt; temp.length; i++) {
     *
     *      }
     * </pre>
     *
     * @param sql The SQL statement to execute.
     * @return A List of Object[]s, never null.
     */
    public List<Object[]> searchToList(String sql) {
        List<Object[]> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result = run.query(sql, new ArrayListHandler());
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Executes the given SELECT SQL query and place the ResultSet into a List of Object[]s
     *
     * @param sql The SQL statement to execute.
     * @param params Initialize the PreparedStatement's IN parameters with this array.
     * @return A List of Object[]s, never null.
     */
    public List<Object[]> searchToList(String sql, Object... params)  {
        List<Object[]> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            result = run.query(sql, new ArrayListHandler(), params);
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and converts the first
     * ResultSet into a Map object.
     *
     * Usage Demo:
     *
     * <pre>
     * Map result = searchToMap(sql);
     *
     * </pre>
     *
     * @param sql The SQL to execute.
     * @return A Map with the values from the first row or null if there are no rows in the
     *         ResultSet.
     */
    public Map<String, Object> searchToMap(String sql) {
        Map<String, Object> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result = run.query(sql, new MapHandler());
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Executes the given SELECT SQL query and converts the first ResultSet into a Map object.
     *
     * @param sql The SQL to execute.
     * @param params Initialize the PreparedStatement's IN parameters with this array.
     * @return A Map with the values from the first row or null if there are no rows in the
     *         ResultSet.
     */
    public Map<String, Object> searchToMap(String sql, Object... params) {
        Map<String, Object> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            result = run.query(sql, new MapHandler(), params);
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and converts the ResultSet
     * into a List of Map objects.
     *
     * Usage Demo:
     *
     * <pre>
     * ArrayList result = searchToMapList(sql);
     * Iterator iterator = result.iterator();
     * while (iterator.hasNext()) {
     * 	Map map = (Map) iterator.next();
     *
     * }
     * </pre>
     *
     * @param sql The SQL to execute.
     * @return A List of Maps, never null.
     */
    public List<Map<String, Object>> searchToMapList(String sql) {

        List<Map<String, Object>> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result = run.query(sql, new MapListHandler());
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Executes the given SELECT SQL query and converts the ResultSet into a List of Map objects.
     *
     * @param sql The SQL to execute.
     * @param params Initialize the PreparedStatement's IN parameters with this array.
     * @return A List of Maps, never null.
     */
    public List<Map<String, Object>> searchToMapList(String sql, Object... params) {
        List<Map<String, Object>> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            result = run.query(sql, new MapListHandler(), params);
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
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
     * @param type
     *            The Class of beans.
     * @param sql
     *            The SQL to execute.
     * @return An initialized JavaBean or null if there were no rows in the
     *         ResultSet.
     */
    public <T> T searchToBean(Class type, String sql) {
        T result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result = run.query(sql, new BeanHandler<T>(type));
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Executes the given SELECT SQL query and Convert the first row of the
     * ResultSet into a bean with the Class given in the parameter.
     *
     * @param type
     *            The Class of beans.
     * @param sql
     *            The SQL to execute.
     * @param params
     *            Initialize the PreparedStatement's IN parameters with this
     *            array.
     * @return An initialized JavaBean or null if there were no rows in the
     *         ResultSet.
     */
    public <T> T searchToBean(Class type, String sql, Object... params) {
        T result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            result = run.query(sql, new BeanHandler<T>(type), params);
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
    }

    /**
     * Execute an SQL SELECT query without any replacement parameters and
     * Convert the ResultSet rows into a List of beans with the Class given in
     * the parameter.
     *
     * Usage Demo:
     *
     * <pre>
     * ArrayList result = searchToBeanList(Test.class, sql);
     * Iterator iterator = result.iterator();
     * while (iterator.hasNext()) {
     * 	Test test = (Test) iterator.next();
     *
     * }
     * </pre>
     *
     * @param type
     *            The Class that objects returned from handle() are created
     *            from.
     * @param sql
     *            The SQL to execute.
     * @return A List of beans (one for each row), never null.
     */
    public <T> List<T> searchToBeanList(Class type, String sql) {
        List<T> result = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            result =  run.query(sql, new BeanListHandler<T>(type));
        } catch (Exception _e) {
            LOG.error("查询出现异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return result;
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


    /**
     * Execute a batch of SQL INSERT, UPDATE, or DELETE queries.
     *
     * @param sql The SQL to execute.
     * @param params An array of query replacement parameters. Each row in this array is one set of
     *        batch replacement values.
     * @return The number of rows updated per statement.
     */
    public int[] batch(String sql, Object[][] params) {
        int[] rows = null;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            rows = run.batch(sql, params);
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return rows;
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

            LOG.debug("SQL:{}, 参数：{}", sql, params);
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
    /**
     * Executes the given INSERT, UPDATE, or DELETE SQL statement without any
     * replacement parameters.
     *
     * @param sql
     *            The SQL statement to execute.
     * @return The number of rows updated.
     */
    public int update(String sql) {
        int rows = 0;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            rows = run.update(sql);
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return rows;
    }

    /**
     * Executes the given INSERT, UPDATE, or DELETE SQL statement.
     *
     * @param sql
     *            The SQL statement to execute.
     * @param params
     *            Initializes the PreparedStatement's IN (i.e. '?') parameters.
     * @return The number of rows updated.
     */
    public int update(String sql, Object... params) {
        int rows = 0;
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}, 参数：{}", sql, params);
            rows = run.update(sql, params);
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return rows;
    }

    /**
     * 获取记录数
     * @Description: 获取记录数
     * @author zhangYuanHui
     * @param sql 执行的sql
     * @return Long
     * @throws Exception
     */
    public long searchTotal(String sql) {
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            Object[] result = (Object[]) run.query(sql, new ArrayHandler());
            if(null != result && result.length > 0){
                return Long.parseLong(result[0].toString());
            }
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return 0L;
    }

    /**
     * 根据指定的单个参数获取记录总数
     * @Description: 根据指定参数获取记录总数
     * @author zhangYuanHui
     * @param sql 执行的sql
     * @param params 参数值数组
     * @return Long
     * @throws Exception
     */
    public long searchTotal(String sql, Object... params) {
        Number num = 0;
        try {
            QueryRunner queryRunner = new QueryRunner(dataSource);
            ScalarHandler scalarHandler = new ScalarHandler<>();
            if (params == null) {
                num = (Number) queryRunner.query(sql, scalarHandler);
            } else {
                num = (Number) queryRunner.query(sql, scalarHandler, params);
            }
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return (num != null) ? num.longValue() : 0;
    }

    public Number sumData(String sql) {
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            Object[] result = (Object[]) run.query(sql, new ArrayHandler());
            if(null != result && result.length > 0){
                return (Number)result[0];
            }
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return 0;
    }

    public Number sumData(String sql, Object... params) {
        try {
            QueryRunner run = new QueryRunner(dataSource);
            LOG.debug("SQL:{}", sql);
            Object[] result = (Object[]) run.query(sql, new ArrayHandler(), params);
            if(null != result && result.length > 0){
                return (Number)result[0];
            }
        } catch (Exception _e) {
            LOG.error("执行生sql异常!原因:" + _e);
            throw new RuntimeException(_e);
        }
        return 0;
    }

}
