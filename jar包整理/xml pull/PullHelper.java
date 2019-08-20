package cn.itcast.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import cn.itcast.model.User;

/**
 * 提供pull操作xml 两个方法
 * 
 * @author seawind
 * 
 */
public class PullHelper {
	// 使用pull解析xml 得到一个对象集合
	public static List<User> parseXml2List(String fileName) throws Exception {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		// 设置解析器 文件输入流
		// InputStream in = PullHelper.class.getResourceAsStream("/" +
		// fileName);
		InputStream in = new FileInputStream("c:/" + fileName);
		parser.setInput(in, "utf-8");
		List<User> users = null;
		User user = null;

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// 在文档开始时 初始化集合对象
			if (eventType == XmlPullParser.START_DOCUMENT) {
				users = new ArrayList<User>();
			} else if (eventType == XmlPullParser.START_TAG) {
				// 在user标签开始时 创建user对象
				if (parser.getName().equals("user")) {
					user = new User();
				} else if (parser.getName().equals("username")) {
					// 封装user的其它属相
					user.setUsername(parser.nextText());
				} else if (parser.getName().equals("password")) {
					// 封装user的其它属相
					user.setPassword(parser.nextText());
				} else if (parser.getName().equals("nickname")) {
					// 封装user的其它属相
					user.setNickname(parser.nextText());
				} else if (parser.getName().equals("email")) {
					// 封装user的其它属相
					user.setEmail(parser.nextText());
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				// 在 user标签结束时 添加user对象 到集合
				if (parser.getName().equals("user")) {
					users.add(user);
				}
			}
			eventType = parser.next();
		}
		return users;
	}

	// 将集合中对象信息 回写xml
	public static void writeList2Xml(List<User> users, String fileName)
			throws Exception {
		// 使用序列化对象
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlSerializer serializer = factory.newSerializer();
		// 设置输出流
		// String fullPath = PullHelper.class.getResource("/" +
		// fileName).getFile();
		OutputStream output = new FileOutputStream("c:/" + fileName);
		serializer.setOutput(output, "utf-8");
		// 声明语句
		serializer.startDocument("utf-8", true);
		// 写内容
		serializer.startTag(null, "users");
		for (User user : users) {
			serializer.startTag(null, "user");
			serializer.startTag(null, "username");
			serializer.text(user.getUsername());
			serializer.endTag(null, "username");
			serializer.startTag(null, "password");
			serializer.text(user.getPassword());
			serializer.endTag(null, "password");
			serializer.startTag(null, "nickname");
			serializer.text(user.getNickname());
			serializer.endTag(null, "nickname");
			serializer.startTag(null, "email");
			serializer.text(user.getEmail());
			serializer.endTag(null, "email");
			serializer.endTag(null, "user");
		}
		serializer.endTag(null, "users");
		serializer.endDocument();
	}
}
