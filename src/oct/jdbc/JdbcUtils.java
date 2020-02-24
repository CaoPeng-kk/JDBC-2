package oct.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class JdbcUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

//	事务专用连接
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/*
	 * 使用 c3p0连接池
	 * 
	 */
	public static Connection getConnection() throws SQLException {
		Connection con = tl.get();
		if (con != null) {
			return con;
		}
		return dataSource.getConnection();
	}
	/*
	 * 返回一个连接池对象
	 */
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
		
	}

	public static void beginTransaction() throws SQLException {
//		1.给con赋值
		Connection con = tl.get();
		if (con != null) {
			throw new SQLException("已经开启事物，不要重复开启");
		}
		con = getConnection();
		tl.set(con);
		con.setAutoCommit(false);
	}

	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new SQLException("没有开启事务，不要提交");
		}
		con.commit();
		con.close();
		tl.remove();
	}

	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new SQLException("没有事务 不要提交");
		}
		con.rollback();
		con.close();
		tl.remove();
	}
	public static void releaseConnection(Connection connection) throws SQLException {
		/*判断是否为事务专用连接  如果是 就不关  不是就关
		 * 
		 */
		Connection con = tl.get();
		if(con ==null) {
			connection.close();
		}else if(con!=connection) {
			connection.close();
		}
	}
	
}



