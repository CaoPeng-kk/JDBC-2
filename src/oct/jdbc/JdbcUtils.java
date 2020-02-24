package oct.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class JdbcUtils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

//	����ר������
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/*
	 * ʹ�� c3p0���ӳ�
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
	 * ����һ�����ӳض���
	 */
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
		
	}

	public static void beginTransaction() throws SQLException {
//		1.��con��ֵ
		Connection con = tl.get();
		if (con != null) {
			throw new SQLException("�Ѿ����������Ҫ�ظ�����");
		}
		con = getConnection();
		tl.set(con);
		con.setAutoCommit(false);
	}

	public static void commitTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new SQLException("û�п������񣬲�Ҫ�ύ");
		}
		con.commit();
		con.close();
		tl.remove();
	}

	public static void rollbackTransaction() throws SQLException {
		Connection con = tl.get();
		if (con == null) {
			throw new SQLException("û������ ��Ҫ�ύ");
		}
		con.rollback();
		con.close();
		tl.remove();
	}
	public static void releaseConnection(Connection connection) throws SQLException {
		/*�ж��Ƿ�Ϊ����ר������  ����� �Ͳ���  ���Ǿ͹�
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



