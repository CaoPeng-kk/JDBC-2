package oct.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {

	/*
	 * 
	 */
	public void update(String name,double balance) throws SQLException  {
		/* 转账方法
		 * 
		 */
//		创建QuerRunner对象  不给参数  因为要保证多次调用的连接都为同一个
		QueryRunner qr = new QueryRunner();
		
		String sql = "update account set balance=balance+? where name = ?";
		Object[] params = {balance,name};
//		获取连接
		Connection con = JdbcUtils.getConnection();
		/* 执行qr.update  --此处注意此方法需要传入连接  也可以写一个子类继承 QeryRunner 
		 * 重写方法 在方法内部加上连接
		 */
		qr.update(con,sql, params);
//		释放连接
		JdbcUtils.releaseConnection(con);    
	}
	
}
