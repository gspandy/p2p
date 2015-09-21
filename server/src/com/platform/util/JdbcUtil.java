package com.platform.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class JdbcUtil {

	private static Logger log = Logger.getLogger(JdbcUtil.class);

	public static final byte DATABASE_ORACLE = 1;
	public static final byte DATABASE_MYSQL = 2;

	public static final String ORACLE_DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String ORACLE_DB_URL = ResourceUtil.getSystem("oracle.db.url");
	public static final String ORACLE_DB_USERNAME = ResourceUtil.getSystem("oracle.db.username");
	public static final String ORACLE_DB_PASSWORD = ResourceUtil.getSystem("oracle.db.password");

	public static final String MYSQL_DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_DB_URL = ResourceUtil.getSystem("mysql.db.url");
	public static final String MYSQL_DB_USERNAME = ResourceUtil.getSystem("mysql.db.username");
	public static final String MYSQL_DB_PASSWORD = ResourceUtil.getSystem("mysql.db.password");

	public static Map<String, Object> getSqlFromOracle(Map<String, String> sqlMap, Map<String, Object> pageMap, byte databaseType) {
		String sql = getSql(sqlMap, pageMap);
		return get(sql, databaseType);
	}

	public static List<Map<String, Object>> listFromOracle(Map<String, String> sqlMap, Map<String, Object> pageMap, byte databaseType) {
		String sql = getSql(sqlMap, pageMap);
		if (pageMap.get("first") != null && pageMap.get("size") != null) {
			sql = "select * from (select hsg.*,rownum rn from (" + sql + ") hsg where rownum<=" + ((Integer) pageMap.get("first") + (Integer) pageMap.get("size")) + ") where rn>" + pageMap.get("first");
		}
		return list(sql, databaseType);
	}

	public static long getCount(Map<String, String> sqlMap, Map<String, Object> pageMap, byte databaseType) {
		String sql = getSql(sqlMap, pageMap);
		sql = "select count(*) hsg from (" + sql + ")";
		return Long.parseLong(get(sql, databaseType).get("hsg").toString());
	}

	public static String getSql(Map<String, String> sqlMap, Map<String, Object> pageMap) {
		String sql = sqlMap.get("sql");
		String vars = sqlMap.get("queryFields");
		Pattern p = Pattern.compile("#.*?#");
		String temp = "";
		if (StringUtil.isNotBlank(vars)) {
			String[] varArr = vars.split("，");
			for (String var : varArr) {
				Matcher m = p.matcher(var);
				boolean isReplace = true;
				while (m.find()) {
					String key = m.group(0);
					String value = (String) pageMap.get(m.group(0).replace("#", ""));
					if (StringUtil.isNotBlank(value)) {
						var = var.replace(key, value);
					} else {
						isReplace = false;
					}
				}
				if (isReplace) {
					temp += " " + var;
				}
			}
		}
		return sql + temp;
	}

	public static Map<String, Object> get(String sql, byte databaseType) {
		return (Map<String, Object>) execute(sql, 1, databaseType);
	}

	public static List<Map<String, Object>> list(String sql, byte databaseType) {
		return (List<Map<String, Object>>) execute(sql, 2, databaseType);
	}

	public static void execute(String sql, byte databaseType) {
		execute(sql, 3, databaseType);
	}

	// 返回值全为String
	public static Object execute(String sql, int function, byte databaseType) {
		if (StringUtil.isBlank(sql)) {
			return null;
		}

		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			connection = getConnection(databaseType);
			st = connection.createStatement();
			rs = st.executeQuery(sql);

			switch (function) {
			case 1:// get
				ResultSetMetaData md = rs.getMetaData();
				Map<String, Object> map = new HashMap<String, Object>();
				while (rs.next()) {
					for (int i = 1; i <= md.getColumnCount(); i++) {
						map.put(md.getColumnName(i).toLowerCase(), rs.getObject(md.getColumnName(i)));
					}
					return map;
				}
				return null;
			case 2:// select
				ResultSetMetaData md2 = rs.getMetaData();
				List<Map> list = new ArrayList<Map>();
				while (rs.next()) {
					Map<String, Object> map2 = new HashMap<String, Object>();
					for (int i = 1; i <= md2.getColumnCount(); i++) {
						map2.put(md2.getColumnName(i).toLowerCase(), rs.getObject(md2.getColumnName(i)));
					}
					list.add(map2);
				}
				return list;
			case 3:// execute

			default:
				break;
			}
		} catch (Exception e) {
			return -1;
		} finally {
			try {
				releaseResource(connection, st, rs);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return null;
	}

	private static void releaseResource(Connection connection, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}

	private static Connection getConnection(byte databaseType) throws Exception {
		if (databaseType == DATABASE_ORACLE) {
			Class.forName(ORACLE_DB_DRIVER);
			Connection connection = DriverManager.getConnection(ORACLE_DB_URL, ORACLE_DB_USERNAME, ORACLE_DB_PASSWORD);
			return connection;
		}

		if (databaseType == DATABASE_MYSQL) {
			Class.forName(MYSQL_DB_DRIVER);
			
			String pwd = "";
			try {
				pwd = new String(new EncryptDBUtil().decode(MYSQL_DB_PASSWORD));
			} catch (Exception e) {
				log.error(e);
			}
			
			Connection connection = DriverManager.getConnection(MYSQL_DB_URL, MYSQL_DB_USERNAME, pwd);
			return connection;
		}

		return null;
	}

	public static boolean procedure(String name, byte databaseType) {
		Connection connection = null;
		CallableStatement stmt = null;
		try {
			connection = getConnection(databaseType);
			stmt = connection.prepareCall("{call " + name + "()}");
			stmt.execute();
			return true;
		} catch (Exception e) {
			log.error(e);
		} finally {
			releaseResource(connection, stmt, null);
		}
		return false;
	}

}
