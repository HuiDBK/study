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
 * �ṩpull����xml ��������
 * 
 * @author seawind
 * 
 */
public class PullHelper {
	// ʹ��pull����xml �õ�һ�����󼯺�
	public static List<User> parseXml2List(String fileName) throws Exception {
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = factory.newPullParser();
		// ���ý����� �ļ�������
		// InputStream in = PullHelper.class.getResourceAsStream("/" +
		// fileName);
		InputStream in = new FileInputStream("c:/" + fileName);
		parser.setInput(in, "utf-8");
		List<User> users = null;
		User user = null;

		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			// ���ĵ���ʼʱ ��ʼ�����϶���
			if (eventType == XmlPullParser.START_DOCUMENT) {
				users = new ArrayList<User>();
			} else if (eventType == XmlPullParser.START_TAG) {
				// ��user��ǩ��ʼʱ ����user����
				if (parser.getName().equals("user")) {
					user = new User();
				} else if (parser.getName().equals("username")) {
					// ��װuser����������
					user.setUsername(parser.nextText());
				} else if (parser.getName().equals("password")) {
					// ��װuser����������
					user.setPassword(parser.nextText());
				} else if (parser.getName().equals("nickname")) {
					// ��װuser����������
					user.setNickname(parser.nextText());
				} else if (parser.getName().equals("email")) {
					// ��װuser����������
					user.setEmail(parser.nextText());
				}
			} else if (eventType == XmlPullParser.END_TAG) {
				// �� user��ǩ����ʱ ���user���� ������
				if (parser.getName().equals("user")) {
					users.add(user);
				}
			}
			eventType = parser.next();
		}
		return users;
	}

	// �������ж�����Ϣ ��дxml
	public static void writeList2Xml(List<User> users, String fileName)
			throws Exception {
		// ʹ�����л�����
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		XmlSerializer serializer = factory.newSerializer();
		// ���������
		// String fullPath = PullHelper.class.getResource("/" +
		// fileName).getFile();
		OutputStream output = new FileOutputStream("c:/" + fileName);
		serializer.setOutput(output, "utf-8");
		// �������
		serializer.startDocument("utf-8", true);
		// д����
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
