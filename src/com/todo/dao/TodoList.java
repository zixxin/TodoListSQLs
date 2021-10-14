package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;
import com.todo.service.DbConnect;

public class TodoList {
	private List<TodoItem> list;
	Connection conn;

	public TodoList() {
		this.conn = DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date)"
				+ "values (?, ?, ?, ?, ?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			count = pstmt.executeUpdate();
			pstmt.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,index);
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=?, current_date=?, due_date=?"
				+ " where id = ?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setInt(6, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				TodoItem t = new TodoItem(title, category, description, due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				
				if (rs.getInt("is_completed") == 1) {
					t.setIsCompleted(1);
				}
				else {
					t.setIsCompleted(0);
				}
				
				if (rs.getInt("is_doing") == 1) {
					t.setIsDoing(1);
				}
				else {
					t.setIsDoing(0);
				}
				 
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ? or category like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			pstmt.setString(3, keyword);
			ResultSet rs = pstmt.executeQuery();
			
			changeListToType(list, rs);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY " + orderby;
			if (ordering == 0) sql += " desc";
			ResultSet rs = stmt.executeQuery(sql);
			changeListToType(list, rs);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<String> getCategories() {
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			String category = rs.getString("category");
			list.add(category);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			changeListToType(list, rs);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	private static void changeListToType(ArrayList<TodoItem> list, ResultSet rs) throws SQLException {
		while(rs.next()) {
			TodoItem item = new TodoItem(
					rs.getString("title"),
					rs.getString("category"), 
					rs.getString("memo"), 
					rs.getString("current_date"),
					rs.getString("due_date")
			);
			
			int id = rs.getInt("id");
			item.setId(id);
			
			if (rs.getInt("is_completed") == 1) {
				item.setIsCompleted(1);
			}
			else {
				item.setIsCompleted(0);
			}
			if (rs.getInt("is_doing") == 1) {
				item.setIsDoing(1);
			}
			else {
				item.setIsDoing(0);
			}
			
			list.add(item);
		}
	}
	
	public Boolean isDuplicate(String title) {
		String sql = "select count(id) from list where title = \"" + title +"\"";
		Statement stmt;
		
		int count = 0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
			if(count > 0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean doingItem(int id) {
		String sql = "update list set is_doing=1 where id = " + id;
		Statement stmt;
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count > 0) return true;
		return false;
	}

	public boolean completeItem(int id) {
		String sql = "update list set is_completed=1 where id = " + id;
		Statement stmt;
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count > 0) return true;
		return false;
	}

	public boolean completeItem(int id1, int id2, int id3) {
		String sql1 = "update list set is_completed=1 where id = " + id1;
		String sql2 = "update list set is_completed=1 where id = " + id2;
		String sql3 = "update list set is_completed=1 where id = " + id3;
		Statement stmt;
		int count = 0;
		
		try {
			stmt = conn.createStatement();
			count = stmt.executeUpdate(sql1);
			count = stmt.executeUpdate(sql2);
			count = stmt.executeUpdate(sql3);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(count > 0) return true;
		return false;
	}
	
	public ArrayList<TodoItem> doingList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list WHERE is_doing=1";
			ResultSet rs = stmt.executeQuery(sql);
			changeListToType(list, rs);			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> completeList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list WHERE is_completed=1";
			ResultSet rs = stmt.executeQuery(sql);
			changeListToType(list, rs);			
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
}