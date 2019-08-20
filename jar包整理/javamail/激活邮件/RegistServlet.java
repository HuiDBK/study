package cn.itcast.servlet;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.domain.User;
import cn.itcast.utils.JDBCUtils;
import cn.itcast.utils.MailUtils;

/**
 * 完成用户注册，发送激活邮件
 * 
 * @author seawind
 * 
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 获得form 数据
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		// 2 生成激活码
		String activeCode = MailUtils.generateActiveCode();
		user.setActivecode(activeCode);

		// 3 保存数据到DB
		String sql = "insert into users values(null,?,?,?,null,0,?)";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			queryRunner.update(sql, new Object[] { username, password, email,
					activeCode });
			// 保存数据库成功
			// 4 发送激活邮件
			// 创建会话
			Session session = MailUtils.createSession();
			// 编写邮件
			Message message = MailUtils.generateMessage(session, user);
			// 发送邮件
			MailUtils.sendMail(message, session);

			// 给客户端注册信息提示
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("注册成功！激活邮件已发送，请于2小时内完成账户激活！");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("注册失败！");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
