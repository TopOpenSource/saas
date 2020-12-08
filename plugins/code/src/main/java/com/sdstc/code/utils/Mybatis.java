package com.sdstc.code.utils;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import com.sdstc.code.dao.TableMapper;
import com.sdstc.code.model.Column;
import com.zaxxer.hikari.HikariDataSource;

public class Mybatis {
	private String jdbcUrl;
	private String scheme;
	private String userName;
	private String pwd;
	private String tableName;

	public Mybatis(String jdbcUrl,String scheme,String userName, String pwd, String tableName) {
		this.jdbcUrl = jdbcUrl;
		this.userName = userName;
		this.pwd = pwd;
		this.tableName = tableName;
		this.scheme=scheme;
	}

	public DataSource getDataSource(String jdbcUrl,String scheme, String userName, String pwd) {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:mysql://" + jdbcUrl+"/"+scheme+ "?characterEncoding=utf8&allowMultiQueries=true&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai");
		dataSource.setUsername(userName);
		dataSource.setPassword(pwd);
		return dataSource;
	}

	public SqlSessionFactory getSqlSessionFactory(String jdbcUrl,String scheme, String userName, String pwd) {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("development", transactionFactory, this.getDataSource(jdbcUrl,scheme,userName, pwd));

		Configuration configuration = new Configuration(environment);
		configuration.addMapper(TableMapper.class);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

		return sqlSessionFactory;
	}

	public List<Column> getColumns(SqlSessionFactory sqlSessionFactory,String scheme, String tableName) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TableMapper mapper = sqlSession.getMapper(TableMapper.class);
		return mapper.getColumns(tableName,scheme);
	}

	public List<Column> getColumns() {
		SqlSessionFactory sqlSessionFactory = this.getSqlSessionFactory(this.jdbcUrl,this.scheme,this.userName, this.pwd);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		TableMapper mapper = sqlSession.getMapper(TableMapper.class);
		return mapper.getColumns(this.tableName,this.scheme);
	}

	public static void main(String[] args) {
		Mybatis mybatis = new Mybatis("172.16.10.145:30017","system", "root", "qwe123-=", "sys_user");
		List<Column> columns = mybatis.getColumns();
		for(Column column:columns) {
			System.out.println(column.getColumnName());
		}
	}

}
