package messenger.client;

import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import messenger.login.MemberLogin;

public class ImageThread extends Thread{
	ObjectInputStream ois;
	ObjectOutputStream oos;
	String imgFileName;
	
	public ImageThread(Socket client, MemberLogin memberLogin) {
		try {
			ois=new ObjectInputStream(client.getInputStream());
			oos=new ObjectOutputStream(client.getOutputStream());
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(Image img) {
		try {
			oos.writeObject((Object)img);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listen() {
		try {
			ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		listen();
	}
}
