package org.platform.snail.dao;

import org.apache.ibatis.session.RowBounds;

public interface IBatisDAOInterface {

	public abstract boolean isExit(java.lang.String statementName,
			java.lang.Object parameterObject);

	public abstract boolean delete(java.lang.String statementName,
			java.lang.Object parameterObject);

	public abstract boolean insert(java.lang.String statementName,
			java.lang.Object parameterObject);

	public abstract java.util.List queryForList(java.lang.String statementName,
			java.lang.Object parameterObject);
	
	public abstract java.util.List queryForList(java.lang.String statementName,
			java.lang.Object parameterObject,RowBounds rowBounds);

	public abstract java.lang.Object queryForObject(
			java.lang.String statementName, java.lang.Object parameterObject);


	public abstract boolean update(java.lang.String statementName,
			java.lang.Object parameterObject);


}
