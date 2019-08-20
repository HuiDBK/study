package cn.itcast.dbutils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.utils.JDBCUtils;
import cn.itcast.vo.Account;

public class ResultSetHandlerTest {
	// ArrayHandler, ArrayListHandler, BeanHandler, BeanListHandler,
	// ColumnListHandler, KeyedHandler, MapHandler, MapListHandler,
	// ScalarHandler
	@Test
	// ScalarHandler ---- 用于查询单值
	public void demo9() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select sum(money) from account";
		double sum = (Double) queryRunner.query(sql, new ScalarHandler(1));
		System.out.println(sum);
	}

	@Test
	// KeyedHandler
	public void demo8() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		Map<Object, Map<String, Object>> map = queryRunner.query(sql,
				new KeyedHandler("name"));
		System.out.println(map);
	}

	@Test
	// MapListHandler
	public void demo7() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";

		// 列名作为key key泛型一定是String 列值作为Map的value 泛型Object
		List<Map<String, Object>> list = queryRunner.query(sql,
				new MapListHandler());
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}
	}

	@Test
	// MapHandler
	public void demo6() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";

		// 列名作为key key泛型一定是String 列值作为Map的value 泛型Object
		Map<String, Object> map = queryRunner.query(sql, new MapHandler());
		System.out.println(map);
	}

	@Test
	// ColumnListHandler
	public void demo5() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		List list = queryRunner.query(sql, new ColumnListHandler(4));
		System.out.println(list);
	}

	@Test
	// BeanListHandler
	public void demo4() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		List<Account> accounts = queryRunner.query(sql,
				new BeanListHandler<Account>(Account.class));
		for (Account account : accounts) {
			System.out.println(account.getId() + "." + account.getName() + ","
					+ account.getMoney());
		}
	}

	@Test
	// BeanHandler
	public void demo3() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		Account account = queryRunner.query(sql, new BeanHandler<Account>(
				Account.class));
		System.out.println(account.getId() + "." + account.getName() + ","
				+ account.getMoney());
	}

	@Test
	// ArrayListHandler
	public void demo2() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		List<Object[]> list = queryRunner.query(sql, new ArrayListHandler());
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}
	}

	@Test
	// ArrayHandler
	public void demo1() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from account";
		Object[] values = queryRunner.query(sql, new ArrayHandler());
		System.out.println(Arrays.toString(values));
	}

}
