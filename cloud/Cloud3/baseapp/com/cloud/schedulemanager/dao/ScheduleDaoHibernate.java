package com.cloud.schedulemanager.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.schedulemanager.model.Schedule;

@Repository
public class ScheduleDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Schedule getScheduleById(String id) {
		return (Schedule)getDataObject(Schedule.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Schedule saveSchedule(Schedule schedule) {
		if(schedule.getId() == null || schedule.getId().equals("")) {
			saveData(schedule);
		} else {
			saveOrUpdate(schedule);
		}
		return schedule;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Schedule> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Schedule schedule = list.get(i);
				session.save(schedule);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			flag = false;
		} finally {
			return flag;
		}
	}

	/**
	 * 列表查询
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map searchSchedule(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Schedule schedule";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Schedule delSchedule(Schedule schedule) {
		return (Schedule)delData(schedule);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delScheduleBatch(List<String> list) {
		String delHql = "DELETE Schedule ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Schedule schedule where 1=1 " + where;
		return getDataList(hql);
	}

}