package com.cityinspector.topic.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllTopicData() {
		String sql = "select * from SERVICE_TOPIC";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
