package com.sds.acube.luxor.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.sds.acube.luxor.framework.config.LuxorConfig;

public class UtilDB {
	private static Hashtable dataSource = new Hashtable();

	
	/**
	 * Datasource에서 Connection 객체를 얻는다.
	 * 
	 * @param jndiName Datasource의 JNDI명
	 * @return 성공하면 Connection 객체, 실패하면 null
	 */
	public static Connection getConnectionFromDataSource(String jndiName) {
		return getConnectionFromDataSource(jndiName, false);
	}
	
	
	/**
	 * Datasource에서 Connection 객체를 얻는다.
	 * 
	 * @param jndiName Datasource의 JNDI명
	 * @param autoCommit 오토커밋 여부
	 * @return 성공하면 Connection 객체, 실패하면 null
	 */
	public static Connection getConnectionFromDataSource(String jndiName, boolean autoCommit) {
		Connection con = null;

		try {
			DataSource ds = (DataSource)dataSource.get(jndiName);
			
			if(ds==null) {
				InitialContext ctx = new InitialContext();
				ds = (DataSource) ctx.lookup(jndiName);
				dataSource.put(jndiName, ds);
			}
			
			con = ds.getConnection();
			
			// JBoss는 setAutoCommit을 허용하지 않음
			if(!LuxorConfig.getString("BASIC.WAS").toLowerCase().startsWith("jboss")) {
				con.setAutoCommit(autoCommit);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void closeConnection(Connection con) {
		if(con != null) {
			try {
				if("JEUS".equalsIgnoreCase(LuxorConfig.getString("BASIC.WAS"))) {
					con.commit();
				}

				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void closeConnection(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void closeConnection(PreparedStatement pstmt) {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void closeConnection(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


	public static void closeConnection(Statement stmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
	}


	public static void closeConnection(PreparedStatement pstmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(pstmt);
	}


	public static void closeConnection(Connection con, Statement stmt) {
		closeConnection(stmt);
		closeConnection(con);
	}


	public static void closeConnection(Connection con, PreparedStatement pstmt) {
		closeConnection(pstmt);
		closeConnection(con);
	}


	public static void closeConnection(Connection con, Statement stmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
		closeConnection(con);
	}


	public static void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(pstmt);
		closeConnection(con);
	}


	public static void closeConnection(Connection con, Statement stmt, PreparedStatement pstmt) {
		closeConnection(stmt);
		closeConnection(pstmt);
		closeConnection(con);
	}


	public static void closeConnection(PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
		closeConnection(pstmt);
	}


	public static void closeConnection(Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
		closeConnection(pstmt);
	}


	public static void closeConnection(Connection con, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
		closeConnection(pstmt);
		closeConnection(con);
	}


	public static void closeConnection(Connection con, PreparedStatement pstmt, Statement stmt, ResultSet rs) {
		closeConnection(rs);
		closeConnection(stmt);
		closeConnection(pstmt);
		closeConnection(con);
	}

}
