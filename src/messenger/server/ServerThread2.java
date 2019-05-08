package messenger.server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import messenger.db.ConnectionManager;

public class ServerThread2 extends Thread {
	MultiServer multiServer;
	Socket client;
	String order;
	String result;
	Object obj;
	ObjectInputStream ois;
	ObjectOutputStream oOs;
	ConnectionManager conManger;
	PreparedStatement pstmt;
	ResultSet rs;
	Connection con;
	Byte[] ImageByte;

	JSONArray array = new JSONArray();

	public ServerThread2(ServerThread serverThread) {
		try {
			ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
			ObjectOutputStream oOs=new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	// µè±â
	public void listen() {
		try {
			File file=new File()
			BufferedImage image=ImageIO.read(ois);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	// ¸»ÇÏ±â
	public void send(String result) {
		ImageIO.write(image, "jpg", File);
	}

	public void run() {
		while (true) {
			System.out.println("run");
			listen();
		}
	}
}