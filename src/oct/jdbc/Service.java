package oct.jdbc;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class Service {

	private AccountDao dao = new AccountDao();
	/* ×ªÕË ÑÝÊ¾
	 * 
	 */
	@Test
	public void Account() throws SQLException {
		try {
			JdbcUtils.beginTransaction();
			dao.update("zs", -100);
			dao.update("ww", 100);
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Òì³£");
			JdbcUtils.rollbackTransaction();
		}
		
	}
	
}
