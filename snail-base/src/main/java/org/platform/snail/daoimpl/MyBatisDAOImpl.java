package org.platform.snail.daoimpl;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.platform.snail.dao.IBatisDAOInterface;

public class MyBatisDAOImpl implements IBatisDAOInterface {
	protected Log log = LogFactory.getLog(this.getClass());

	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public boolean isExit(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			Integer a = (Integer) sqlSessionTemplate.selectOne(statementName,
					parameterObject);
			if (a > 0) {
				return true;
			}
		} catch (Exception e) {
			log.debug(e);
			return false;
		} finally {
			log.info("isExit use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
		return false;
	}

	public boolean insert(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			sqlSessionTemplate.insert(statementName, parameterObject);
		} catch (Exception e) {
			log.debug(e);
			return false;
		} finally {
			log.info("insert use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
		return true;
	}

	public boolean update(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			sqlSessionTemplate.update(statementName, parameterObject);
		} catch (Exception e) {
			log.debug(e);
			return false;
		} finally {
			log.info("update use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
		return true;
	}

	public boolean delete(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			sqlSessionTemplate.delete(statementName, parameterObject);
		} catch (Exception e) {
			log.debug(e);
			return false;
		} finally {
			log.info("delete use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
		return true;
	}



	public java.util.List queryForList(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			return sqlSessionTemplate
					.selectList(statementName, parameterObject);
		} catch (Exception e) {
			log.debug(e);
			return null;
		} finally {
			log.info("queryForList use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
	}
	public java.util.List queryForList(java.lang.String statementName,
			java.lang.Object parameterObject ,RowBounds rowBounds) {
		long startTime = System.currentTimeMillis();
		try {
			return sqlSessionTemplate.selectList(statementName, parameterObject, rowBounds);
		} catch (Exception e) {
			log.debug(e);
			return null;
		} finally {
			log.info("queryForList use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
	}
	public java.lang.Object queryForObject(java.lang.String statementName,
			java.lang.Object parameterObject) {
		long startTime = System.currentTimeMillis();
		try {
			return sqlSessionTemplate.selectOne(statementName, parameterObject);
		} catch (Exception e) {
			log.debug(e);
			return null;
		} finally {
			log.info("queryForObject use time "
					+ (System.currentTimeMillis() - startTime) + " ms");
		}
	}


}
