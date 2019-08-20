package cn.itcast.dbcp;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

public class DBCPTest {

	@Test
	public void demo2() throws Exception {
		InputStream in = DBCPTest.class
				.getResourceAsStream("/dbcpconfig.properties");
		Properties properties = new Properties();
		properties.load(in);

		DataSource basicDataSource = BasicDataSourceFactory
				.createDataSource(properties);

		Connection conn = basicDataSource.getConnection();
		String sql = "select  * from user";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("name"));
		}
		conn.close();
	}

	@Test
	public void demo1() throws Exception {
		BasicDataSource basicDataSource = new BasicDataSource();
		ResourceBundle bundle = ResourceBundle.getBundle("dbcpconfig");
		basicDataSource.setUsername(bundle.getString("username"));
		basicDataSource.setPassword(bundle.getString("password"));
		basicDataSource.setUrl(bundle.getString("url"));
		basicDataSource.setDriverClassName(bundle.getString("driverClassName"));
		Connection conn = basicDataSource.getConnection();
		String sql = "select  * from user";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			System.out.println(rs.getString("name"));
		}
		conn.close();
	}
}
