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
 * ����û�ע�ᣬ���ͼ����ʼ�
 * 
 * @author seawind
 * 
 */
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 ���form ����
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		// 2 ���ɼ�����
		String activeCode = MailUtils.generateActiveCode();
		user.setActivecode(activeCode);

		// 3 �������ݵ�DB
		String sql = "insert into users values(null,?,?,?,null,0,?)";
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		try {
			queryRunner.update(sql, new Object[] { username, password, email,
					activeCode });
			// �������ݿ�ɹ�
			// 4 ���ͼ����ʼ�
			// �����Ự
			Session session = MailUtils.createSession();
			// ��д�ʼ�
			Message message = MailUtils.generateMessage(session, user);
			// �����ʼ�
			MailUtils.sendMail(message, session);

			// ���ͻ���ע����Ϣ��ʾ
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("ע��ɹ��������ʼ��ѷ��ͣ�����2Сʱ������˻����");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ע��ʧ�ܣ�");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
