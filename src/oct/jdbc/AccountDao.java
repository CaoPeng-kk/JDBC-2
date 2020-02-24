package oct.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

public class AccountDao {

	/*
	 * 
	 */
	public void update(String name,double balance) throws SQLException  {
		/* ת�˷���
		 * 
		 */
//		����QuerRunner����  ��������  ��ΪҪ��֤��ε��õ����Ӷ�Ϊͬһ��
		QueryRunner qr = new QueryRunner();
		
		String sql = "update account set balance=balance+? where name = ?";
		Object[] params = {balance,name};
//		��ȡ����
		Connection con = JdbcUtils.getConnection();
		/* ִ��qr.update  --�˴�ע��˷�����Ҫ��������  Ҳ����дһ������̳� QeryRunner 
		 * ��д���� �ڷ����ڲ���������
		 */
		qr.update(con,sql, params);
//		�ͷ�����
		JdbcUtils.releaseConnection(con);    
	}
	
}
