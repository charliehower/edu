package org.platform.snail.autocode;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.platform.snail.utils.SnailUtils;

public class DBHelpInfo {

	/**
	 * 这里是Oracle连接方法 private static final String driver =
	 * "oracle.jdbc.driver.OracleDriver"; private static final String url =
	 * "jdbc:oracle:thin:@localhost:1521:orcl"; private static final String uid
	 * = "system"; private static final String pwd = "sys"; 这里是SQL Server连接方法
	 * private static final String url =
	 * "jdbc:sqlserver://localhost:1433;DateBaseName=数据库名"; private static final
	 * String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" private
	 * static final String uid = "sa"; private static final String pwd = "sa";
	 *
	 *
	 * 这里是MySQL连接方法
	 */
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String pwd = "123456";
	private static final String user = "root";
	private static final String url = "jdbc:mysql://localhost/portal"
			+ "?user=" + user + "&password=" + pwd
			+ "&useUnicode=true&characterEncoding=UTF-8";
	private static Connection getConnection = null;

	public static void main(String[] args) {
		List<ColumsInfo> list = DBHelpInfo.getTableInfo("site");
		StringBuffer _colNames = new StringBuffer();
		StringBuffer _colModel = new StringBuffer();
		_colNames.append("[");
		for (ColumsInfo o : list) {
			_colNames.append("'");
			_colNames.append(o.getRemarks());
			_colNames.append("'");
			_colNames.append(",");
			_colModel.append("{\r");
			_colModel.append("name : '"
					+ SnailUtils.getJavaName(o.getColumName()) + "',\r");
			_colModel.append("width : 100,\r");
			_colModel.append("editable : true,\r");
			_colModel.append("editoptions : {\r");
			_colModel.append("size : \"20\",\r");
			_colModel.append("maxlength : \"50\"\r");
			_colModel.append("}");
			if (o.getIsNullAble().equals("NO")) {
				_colModel.append(",\rformoptions : {\r");
				_colModel.append("elmprefix : \"\",\r");
				_colModel
						.append("elmsuffix : \"<span style='color:red;font-size:16px;font-weight:800\'>*</span>\"\r");
				_colModel.append("},");
				_colModel.append("editrules : {\r");
				_colModel.append("required : true\r");
				_colModel.append("}\r");
			}
			_colModel.append("},\r");

		}
		_colNames.append("]");
		System.out.println(_colNames.toString());
		System.out.println(_colModel.toString());
	}

	public static List<ColumsInfo> getTableInfo(String tableName) {
		Logger logger = Logger.getLogger(DBHelpInfo.class);
		logger.info("table name ：" + tableName);
		List<ColumsInfo> list = new ArrayList<ColumsInfo>();
		getConnection = getConnections();
		try {
			DatabaseMetaData dbmd = getConnection.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, "%", "%",
					new String[] { "TABLE" });
			while (resultSet.next()) {
				if (resultSet.getString("TABLE_NAME").equals(tableName)) {
					ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");

					while (rs.next()) {
						ColumsInfo o = new ColumsInfo();
						o.setColumName(rs.getString("COLUMN_NAME"));
						o.setRemarks(rs.getString("REMARKS"));
						o.setTypeName(rs.getString("TYPE_NAME"));
						o.setIsNullAble(rs.getString("IS_NULLABLE"));
						list.add(o);
						logger.info(o.toString());
					}
					logger.info("generate success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Connection getConnections() {
		try {
			Class.forName(driver);
			getConnection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getConnection;
	}

	public static String getSchema() throws Exception {
		String schema;
		schema = getConnection.getMetaData().getUserName();
		if ((schema == null) || (schema.length() == 0)) {
			throw new Exception("ORACLE数据库模式不允许为空");
		}
		return schema.toUpperCase().toString();
	}

}