package com.callor.todolist.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.callor.todolist.config.DBConnection;
import com.callor.todolist.config.DBInfo;
import com.callor.todolist.config.MySQLConnection;
import com.callor.todolist.model.TodoVO;
import com.callor.todolist.service.TodoListService;

public class TodoListServiceImplV1 implements TodoListService{

	protected Connection dbConn;
	
	public TodoListServiceImplV1() {
		dbConn = DBConnection.getDBConnection();
	}
	
	protected List<TodoVO> select(PreparedStatement pStr) 
			throws SQLException {
		
		List<TodoVO> tdList = new ArrayList<TodoVO>();
		
		ResultSet rSet = pStr.executeQuery();
		
		while(rSet.next()) {
			
			TodoVO tdVO = new TodoVO();
			tdVO.setTd_seq(rSet.getLong(DBInfo.td_seq));
			tdVO.setTd_work(rSet.getString(DBInfo.td_work));
			tdVO.setTd_date(rSet.getString(DBInfo.td_date));
			tdVO.setTd_time(rSet.getString(DBInfo.td_time));
			tdVO.setTd_place(rSet.getString(DBInfo.td_place));
			
			tdList.add(tdVO);
		}
		System.out.println(tdList.toString());
		return tdList;
				
		
	}
	@Override
	public List<TodoVO> selectAll() {
		// TODO 데이터 전체 조회
		
		String sql = " SELECT * FROM tbl_todolist ";
		sql += " ORDER BY td_date DESC ";
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			List<TodoVO> tdList = this.select(pStr);
			pStr.close();
			
			System.out.println(tdList.toString());
			return tdList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public TodoVO findById(Long seq) {
		// TODO seq로 조회하기
		String sql = " SELECT * FROM tbl_todolist ";
		sql += " WHERE td_seq = ? ";
		
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, seq);
			
			List<TodoVO> tdList = this.select(pStr);
			pStr.close();
			
			if(tdList != null && tdList.size() > 0) {
				System.out.println(tdList.toString());
				return tdList.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TodoVO> findBywork(String td_work) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TodoVO> findByDate(String td_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer insert(TodoVO tdVO) {
		// TODO 데이터 추가하기
		String sql = " INSERT INTO tbl_todolist ";
		sql += " ( ";
		sql += " td_work, ";
		sql += " td_date, ";
		sql += " td_time, ";
		sql += " td_place, ";
		sql += " VALUES( ?,?,? ) ";
		
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, tdVO.getTd_work());
			pStr.setString(2, tdVO.getTd_date());
			pStr.setString(3, tdVO.getTd_time());
			pStr.setString(4, tdVO.getTd_place());
			
			return pStr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer update(TodoVO tdVO) {
		// TODO 데이터 업데이트
		String sql = " UPDATE tbl_todolist SET ";
		sql += " td_work = ?, ";
		sql += " td_date = ?, ";
		sql += " td_time = ?, ";
		sql += " td_place = ? ";
		
		sql += " WHERE gb_seq = ? ";
		
		PreparedStatement pStr = null;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, tdVO.getTd_work());
			pStr.setString(2, tdVO.getTd_date());
			pStr.setString(3, tdVO.getTd_time());
			pStr.setString(4, tdVO.getTd_place());
			
			pStr.setLong(5, tdVO.getTd_seq());
			return pStr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer delete(Long seq) {
		// TODO Auto-generated method stub
		return null;
	}

}
