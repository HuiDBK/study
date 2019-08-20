package cn.itcast.javamail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.junit.Test;

public class SendMailAPITest {
	@Test
	public void demo2() throws AddressException, UnsupportedEncodingException,
			MessagingException {
		// 发送最复杂邮件
		// 包含附件的邮件
		Properties props = new Properties();// 发送邮件、接收邮件
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.transport.protocol", "smtp");
		Session session = Session.getDefaultInstance(props);

		Message message = createMessage(session);

		// 发送邮件
		Transport transport = session.getTransport();
		transport.connect("aaa", "123");
		transport.sendMessage(message, message.getAllRecipients());

	}

	public Message createMessage(Session session) throws AddressException,
			MessagingException, UnsupportedEncodingException {

		// 编写嵌入图片的邮件
		Message message = new MimeMessage(session);

		// 所有邮件必须信息
		message.setFrom(new InternetAddress("aaa@estore.com"));
		message.setRecipient(RecipientType.TO, new InternetAddress(
				"bbb@estore.com"));
		message.setSubject("这是一个包含附件和嵌入图片的邮件");

		// 编写邮件正文
		MimeBodyPart content = new MimeBodyPart();
		content.setContent("该邮件既有图片 又有附件！图片<img src='cid:mypic'/>",
				"text/html;charset=utf-8");

		MimeBodyPart pic = new MimeBodyPart();
		DataHandler dh1 = new DataHandler(new FileDataSource("mm.jpg"));
		pic.setDataHandler(dh1);
		pic.setContentID("mypic");

		MimeBodyPart attachment = new MimeBodyPart();
		DataHandler dh2 = new DataHandler(new FileDataSource("love.mp3"));
		attachment.setDataHandler(dh2);
		attachment.setFileName(MimeUtility.encodeText("因为爱情.mp3"));

		// 整合
		MimeMultipart m1 = new MimeMultipart();
		m1.addBodyPart(content);
		m1.addBodyPart(pic);
		m1.setSubType("related");

		MimeBodyPart temp = new MimeBodyPart();
		temp.setContent(m1);

		// 继续整合
		MimeMultipart m2 = new MimeMultipart();
		m2.addBodyPart(temp);
		m2.addBodyPart(attachment);
		m2.setSubType("mixed");

		message.setContent(m2);
		return message;
	}

	@Test
	public void demo1() throws AddressException, MessagingException {
		// 发送邮件 三个步骤
		// 1、准备session 连接 2、准备邮件 Message对象 3、发送Transport
		Properties props = new Properties();
		// props.setProperty("mail.host", "localhost"); // 接收邮件和 发送邮件都可以用
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.transport.protocol", "smtp");
		// 要想创建邮件，需要一个邮件会话，连接smtp
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);// 打开控制台日志
		// 编写第一封javamail 邮件
		Message message = new MimeMessage(session);
		// 设置发件人
		message.setFrom(new InternetAddress("aaa@estore.com"));
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(
				"bbb@estore.com"));
		// 设置邮件主题
		message.setSubject("javamail 简单邮件");
		// 设置邮件正文
		message.setText("第一封简单邮件");

		// 发送邮件
		Transport transport = session.getTransport();
		transport.connect("aaa", "123");
		transport.sendMessage(message, message.getAllRecipients());
	}
}
