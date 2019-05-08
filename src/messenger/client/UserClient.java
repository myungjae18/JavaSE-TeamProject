package messenger.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class UserClient extends JFrame {

	int port = 8888;
	Socket client;// 대화용 소켓
	ClientThread ct;

	String order;
	public String result;

	public UserClient(String order) {
		this.order=order;
		connect();
	}

	public void connect() {

		String ip = "192.168.54.13";
		try {
			client = new Socket(ip, port);
			ct = new ClientThread(this, client);
			ct.start();
			System.out.println(order);
			ct.send(order);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}