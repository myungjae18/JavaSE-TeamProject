package messenger.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messenger.login.MemberLogin;
import messenger.utils.ParserResult;

public class ClientThread extends Thread {

	Socket client;// ��ȭ�� ����
	BufferedReader buffr;
	BufferedWriter buffw;
	MemberLogin memberLogin;
	ParserResult parserResult;
	FileInputStream fis;
	FileOutputStream fos;
	public ClientThread(Socket client, MemberLogin memberLogin) {
		this.memberLogin=memberLogin;
		parserResult= new ParserResult(this);
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));		

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ������ �޽��� ������
	public void send(String order) {
		try {
			buffw.write(order + "\n");
			buffw.flush();// flush() �� ��¿��� �����Ѵ�!!
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listen() {
		try {
			String result = buffr.readLine(); // �α��� Ȯ�� �޼��� ����
			//System.out.println(result);
			if (result.equals("nodata")) {
				JOptionPane.showMessageDialog(null, "���̵� ����� Ʋ���ϴ�");
			} else {
				System.out.println("������ ���� �޼���" + result);
				//sendResult(result); // result�� �ؼ� �ϱ����� �Լ� ȣ��
				//memberLogin.parseResult(result); //����α信 result ������ mainFrame�� �ѷ���
				parserResult.parse(result);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 

	public void run() {
		while (true) {
			listen();
		}
	}
}


