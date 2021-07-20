package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/new_servlet";
			String userName = "root";
			String password = "06012000";
			return DriverManager.getConnection(url, userName, password);

		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}

	}

	@SuppressWarnings("unused")
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results=new ArrayList<T>();
		Connection connection =null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
			try {
				connection = getConnection();
				statement = connection.prepareStatement(sql);
				// set parameter
				setParameter(statement,parameters);
				resultSet = statement.executeQuery();
                while(resultSet.next()) {
                	results.add(rowMapper.mapRow(resultSet));
                	
                }
                return results;
			} catch (SQLException e) {
             return null;
			}	
			finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (resultSet != null) {
						resultSet.close();
					}
				} catch (SQLException e) {
					return null;
				}
			}
		
	
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for(int i=0; i< parameters.length;i++) {
				Object paraObject=parameters[i];
				int index=i+1;
				if(paraObject instanceof Long) {
					statement.setLong(index,(long) paraObject);
				}
				else if(paraObject instanceof String) {
					statement.setString(index, (String) paraObject);
					
				}
				else if(paraObject instanceof Integer) {
					statement.setInt(index, (Integer) paraObject);
				}
				else if(paraObject instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) paraObject);
				}
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void update(String sql, Object... parameters) {
	
		Connection connection=null;
		PreparedStatement statement= null;
		try {
			 connection=getConnection();
			 connection.setAutoCommit(false);
			 statement=connection.prepareStatement(sql);
			 
			 setParameter(statement, parameters);
			 statement.executeUpdate();
	         connection.commit();		 
		}catch(SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		
	         }
			}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		} 
	
		

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection=null;
		PreparedStatement statement= null;
		ResultSet resultSet=null;
		try {
			 Long id=null;
			 connection=getConnection();
			 connection.setAutoCommit(false);
			 statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
			 
			 setParameter(statement, parameters);
			 statement.executeUpdate();
			 resultSet=statement.getGeneratedKeys();
				if(resultSet.next()) {
					id=resultSet.getLong(1);
				}
	         connection.commit();	
	         return id;
		}catch(SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		
	         }
			}
		finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		
			Connection connection =null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;
			int count =0;
			
				try {
					connection = getConnection();
					statement = connection.prepareStatement(sql);
					// set parameter
					setParameter(statement,parameters);
					resultSet = statement.executeQuery();
	                while(resultSet.next()) {
	                	count=resultSet.getInt("count(*)");
	                	
	                }
	                return count;
				} catch (SQLException e) {
	             return 0;
				}	
				finally {
					try {
						if (connection != null) {
							connection.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (resultSet != null) {
							resultSet.close();
						}
					} catch (SQLException e) {
						return 0;
					}
				}



}
}
