package org.platform.snail.model;

public class Dict implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 字典表名
	 */
	private String tableName;
	/**
	 * 字典名称
	 */
	private String dictName;
	/**
	 * 自动加载
	 */
	private Boolean autoLoad;
	/**
	 * 加载语句
	 */
	private String loadSql;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public Boolean getAutoLoad() {
		return autoLoad;
	}
	public void setAutoLoad(Boolean autoLoad) {
		this.autoLoad = autoLoad;
	}
	public String getLoadSql() {
		return loadSql;
	}
	public void setLoadSql(String loadSql) {
		this.loadSql = loadSql;
	}

}
