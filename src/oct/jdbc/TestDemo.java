package oct.jdbc;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class TestDemo {

	
	@Test
	public void fun1() throws SQLException {
		AccountDao dao = new AccountDao();
		dao.update("zs", 200);
	}
	
}
