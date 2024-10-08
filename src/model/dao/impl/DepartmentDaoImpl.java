package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoImpl implements DepartmentDao{
	
	private Connection connection;
	

	public DepartmentDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement st = null;
		try {
			
			
			String q = "insert into department(name) values (?)";
			st = connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, department.getName());
			
			int rowAffected = st.executeUpdate();
			if(rowAffected>0) {
				ResultSet result = st.getGeneratedKeys();
				
				while(result.next()) {
					department.setId(result.getInt(1));
				}
				DB.closeResultSet(result);
			}else {
				throw new DbException("No rows affected!");
			}
			
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Department department) {
		PreparedStatement st = null;
		try {
			System.out.println("Updating department :"+ department);
			String q = "update department set name = ? where id = ?";
			st = connection.prepareStatement(q);
			
			st.setString(1, department.getName());
			st.setInt(2, department.getId());
			st.executeUpdate();
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			String q = "delete from department where id = ?";
			st = connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
		
			st.setInt(1, id);
			st.executeUpdate();
			System.out.println("Departemnt deleted with ID"+ id);
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet result = null;
		try {
			String q = "select * from department where department.id = ?";
			st = connection.prepareStatement(q);
		
			st.setInt(1, id);
			result = st.executeQuery();
			if (result.next()) {
				//Department department = instantiateDepartment(result);
				Department department = new Department();
				department.setId(result.getInt("id"));
				department.setName(result.getString("name"));
				return department;
				
			}
			return null;
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet result = null;
		try {
			String q = "select * from department order by name";
			st = connection.prepareStatement(q);
			result = st.executeQuery();
			List<Department> departments = new ArrayList<Department>();
			while (result.next()) {
				Department department = instantiateDepartment(result);
//				Department department = new Department();
//				department.setId(result.getInt("id"));
//				department.setName(result.getString("name"));
				
				departments.add(department);
			}
			return departments;
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	private Department instantiateDepartment(ResultSet result) throws SQLException {
		Department department = new Department();
		department.setId(result.getInt("id"));
		department.setName(result.getString("name"));
		return department;
		
	}

}
