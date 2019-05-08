package messenger.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import messenger.db.ConnectionManager;

public class ServerThread extends Thread {
	MultiServer multiServer;
	Socket client;
	BufferedReader buffr;
	BufferedWriter buffw;
	String order;
	String result;
	Object obj;
	ConnectionManager conManger;
	PreparedStatement pstmt;
	ResultSet rs;
	Connection con;
	ConnectionManager connectionManger;
	JSONArray array = new JSONArray();

	boolean flag = true;

	public ServerThread(MultiServer multiServer, Socket client, ConnectionManager connectionManger) {
		this.multiServer = multiServer;
		this.client = client;
		this.connectionManger=connectionManger;
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 듣기
	public void listen() {
		try {
			order = buffr.readLine();
			// 서버에 접속한 모든 아바타의 send를 호출하자!!
			for (int i = 0; i < multiServer.list.size(); i++) {
				ServerThread st = multiServer.list.get(i);
				st.send(order);
				// st.send(type);
			}
			multiServer.area.append(order + "\n");
			multiServer.bar.setValue(multiServer.bar.getMaximum());

			if (order != null) {
				Parser();
			}
		} catch (IOException e) {
			System.out.println("클라이언트가 나갔습니다\n ");
			// 벡터에서 제거
			multiServer.list.remove(this);
			multiServer.area.append("현재 : " + multiServer.list.size() + "명 입니다\n");
			e.printStackTrace();
		}
	}

	// 말하기
	public void send(String result) {
		try {
			buffw.write(result + "\n");
			buffw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (flag) {
			listen();
		}
	}

	public void Parser() {
		JSONParser parser = new JSONParser();
		String sql = null;
		Connection con = multiServer.con;
		PreparedStatement pstmt=null;;
		ResultSet rs=null;;
		try {
			obj = parser.parse(order);
		} catch (org.json.simple.parser.ParseException e1) {
			e1.printStackTrace();
		}
		JSONObject jsonObj = (JSONObject) obj;
		String type = (String) jsonObj.get("Type");

		if (type.equals("join")) {
			multiServer.area.append("가입 하게???\n");
			sql = "insert into users(user_code, user_name, user_id, user_pw, user_birth, user_phone, user_email, user_nick, user_img)"
					+ " values(seq_users.nextval, '" + (String) jsonObj.get("Name") + "', '"
					+ (String) jsonObj.get("Id") + "', '" + (String) jsonObj.get("Pw") + "', '"
					+ (String) jsonObj.get("Birth") + "', '" + (String) jsonObj.get("Phone") + "', '"
					+ (String) jsonObj.get("Email") + "', '" + (String) jsonObj.get("Nick") + "' '"
					+ (String) jsonObj.get("Img") + "')";
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("login")) {
			multiServer.area.append("로그인 하게???\n");
			sql = "select * from users where user_id = '" + (String) jsonObj.get("Id") + "' and user_pw = '"
					+ (String) jsonObj.get("Pw") + "'";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (!rs.isBeforeFirst()) {
					send("nodata");
				}
				while (rs.next() == true) {
					JSONObject rObj = new JSONObject();
					String id = rs.getString("user_id");
					String code = rs.getString("user_code");
					String name = rs.getString("user_name");
					String birth = rs.getString("user_birth");
					String phone = rs.getString("user_phone");
					String email = rs.getString("user_email");
					String nick = rs.getString("user_nick");
					String img = rs.getString("user_img");
					rObj.put("Type", "loginresult");
					rObj.put("Code", code);
					rObj.put("Id", id);
					rObj.put("Name", name);
					rObj.put("Birth", birth);
					rObj.put("Phone", phone);
					rObj.put("Email", email);
					rObj.put("Nick", nick);
					rObj.put("Img", img);
					send(rObj.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("friendlist")) {
			multiServer.area.append("친구목록보게???\n");
			sql = "select distinct users.user_code, users.user_name, users.user_nick, users_list.type, users.user_img  from users join users_list on users.user_code = users_list.friend where users_list.user_code = 5 ORDER BY users.user_name";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next() == true) {
					//System.out.println("서버 들어옴옴");
					JSONObject rObj = new JSONObject();
					String code = rs.getString("user_code");
					String name = rs.getString("user_name");
					String nick = rs.getString("user_nick");
					String relation = rs.getString("type");
					String img = rs.getString("user_img");
					rObj.put("Type", "friendresult");
					rObj.put("Code", code);
					rObj.put("Name", name);
					rObj.put("Nick", nick);
					rObj.put("Relation", relation);
					rObj.put("Img", img);
					array.add(rObj);
				}
				send(array.toJSONString());
				System.out.println(array.toJSONString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("chatStart")) { //친구목록에서 더블클릭시 실행
			String myCode = (String) jsonObj.get("MyCode");
			String friendCode = (String) jsonObj.get("FriendCode");
			sql = "SELECT CHAT_CODE FROM(SELECT CHAT_CODE, SUM(CASE WHEN USER_CODE IN (" + myCode + ", "
					+ friendCode
					+ ") THEN 1 ELSE 0 END) AS TargetCnt FROM CHAT_MEMBERS GROUP BY CHAT_CODE HAVING COUNT(*)  = 2) Tbl WHERE Tbl.TargetCnt = 2";
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				String myName = null;
				String friendName=null;
				if (!rs.isBeforeFirst()) { ///채팅방없을 때 새로운 채팅방 개설
					
					sql="select user_name from users where user_code="+myCode+"";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {	//와일 지워도됨					
						myName=rs.getString("user_name");
					}
					sql="select user_name from users where user_code="+friendCode+"";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()) {						
						friendName=rs.getString("user_name");
					}
					String chatName = "'"+myName+" ,"+friendName+"'";
					sql = "INSERT INTO CHAT_ROOM(chat_code,chat_name) VALUES (seq_chat.nextval, "+chatName+")";
					System.out.println(sql);
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					sql = "select seq_chat.currval from dual";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					rs.next();
					String chatCode=rs.getString("currval");
					sql="INSERT INTO CHAT_MEMBERS(CHAT_CODE, USER_CODE) VALUES("+chatCode+", "+myCode+")";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
					sql="INSERT INTO CHAT_MEMBERS(CHAT_CODE, USER_CODE) VALUES("+chatCode+", "+friendCode+")";
					pstmt = con.prepareStatement(sql);
					pstmt.executeUpdate();
					
				}else {
				
					while(rs.next()) {
						//채팅방있으므로 채팅로그 가져와야함
						System.out.println(rs.getString("chat_code"));//이게 채팅방 코드임
						System.out.println("채팅방있으므로 채팅로그 가져와야함");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (type.equals("friendadd")) {
			multiServer.area.append("인싸냐???\n");
			sql = "insert into users_list(user_code   ,type   ,friend) values (" + (String) jsonObj.get("Code") + ", '"
					+ (String) jsonObj.get("Relation") + "'   , select user_code from users where user_nick = '"
					+ (String) jsonObj.get("Nick") + "')";
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("frienddelet")) {
			multiServer.area.append("손절하게???\n");
			sql = "delete from users_list where user_code =" + (String) jsonObj.get("Code") + " and fried = "
					+ (String) jsonObj.get("delet");
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("chatlist")) {
			multiServer.area.append("채팅목록 필요해???");
			sql = "select chat_room.chat_name from chat_room join chat_members"
					+ "on chat_room.chat_code = chat_members.chat_code where chat_members.user_code = "
					+ (String) jsonObj.get("Code");
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next() == true) {
					JSONObject rObj = new JSONObject();
					rObj.put("Type", "chatlistresult");
					String chat_name = rs.getString("chat_room.chat_name");
					rObj.put("Chat_name", chat_name);
					array.add(rObj);
				}
				send(array.toJSONString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("chating")) { // 채팅 처음 입력시
			sql = "insert into chat_logs(chat_code, user_code, chat_log, time) values ('"
					+ (String) jsonObj.get("C.Code") + "', '" + (String) jsonObj.get("U.Code") + "', "
					+ (String) jsonObj.get("Log") + "', sysdate)";
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			multiServer.area.append("말하게???");
		} else if (type.equals("chatstart")) {
			System.out.println("니말 들을사람 더필요해???");
		} else if (type.equals("chatmemberadd")) {
			multiServer.area.append("니말 들을사람 더필요해???");
			sql = "insert into chat_members(chat_code, user_code) values ('" + (String) jsonObj.get("C.Code") + "', '"
					+ (String) jsonObj.get("U.Code") + ")";
			try {
				pstmt = con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (type.equals("file")) {
			System.out.println("파일 보내게???");
		} else if (type.equals("img")) {
			System.out.println("이미지 보내게???");
		} else if (type.equals("emoticon")) {
			System.out.println("이모티콘 보내게???");
		} else if (type.equals("f.search")) {
			sql = "select user_name, user_nick from users where user_nick lick '" + (String) jsonObj.get("Search")
					+ "' ";
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next() == true) {
					JSONObject rObj = new JSONObject();
					rObj.put("Type", "f.search");
					String name = rs.getString("user_name");
					rObj.put("User_name", name);
					String nick = rs.getString("user_nick");
					rObj.put("User_nick", nick);
					array.add(rObj);
				}
				send(array.toJSONString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			multiServer.area.append("친구 검색");
		}
	}

}