
/*메인 프레임에 들어오는 result값 분석기*/
package messenger.utils;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messenger.client.ClientThread;
import messenger.login.MemberLogin;
import messenger.mainframe.MainFrame;

public class ParserResult {

	public String MyCode;
	public String MyId;
	public String MyName;
	public String MyBirth;
	public String MyPhone;
	public String MyEmail;
	public String MyNick;
	public String MyImg;

	ClientThread ct;
	MemberLogin memberLogin;
	MainFrame mainFrame;
	Master master;
	// 아마 실행해야하는 모든클래스를 가진 애도 따로 클래스로 빼야할듯
	// MainFrame 불러오는 시점 알아야함

	public ParserResult(ClientThread ct) {
		this.ct = ct;
		
	}

	public void parse(String result) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		Object obj = null;
		Object obj2 = null;

		try {
			obj = parser.parse(result);// 반환형이 Object이기때문에 쓰려면 jsonobject로 형변환해야함
			System.out.println("obj : " + obj);

			if (array.getClass() == obj.getClass()) { // json 배열 형식으로 받을때
				JSONObject orderObj = new JSONObject();
				array = (JSONArray) obj;
				ArrayList arrayList = new ArrayList();

				JSONObject findType = (JSONObject) array.get(0);//무슨 타입인지 확인하기 위해
				String type = (String) findType.get("Type");

				if (type.equals("friendresult")) { //친구 목록값 
					for (int i = 0; i < array.size(); i++) {
						JSONObject selectObj = (JSONObject) array.get(i);
						String[] friendList = new String[selectObj.size()-1];//친구정보를 담기위한 배열 -1 이유는 type 은 빼기 위함
						
						if (mainFrame == null) { // 처음 한번만 메인프레임 생성을 위해
							mainFrame = new MainFrame(ct, MyCode);
							
							friendList[0] = (String) selectObj.get("Code");
							friendList[1] = (String) selectObj.get("Name");
							friendList[2] = (String) selectObj.get("Nick");
							friendList[3] = (String) selectObj.get("Relation");
							friendList[4] = (String) selectObj.get("Img");
							arrayList.add(friendList);
						} else {
							
							friendList[0] = (String) selectObj.get("Code");
							friendList[1] = (String) selectObj.get("Name");
							friendList[2] = (String) selectObj.get("Nick");
							friendList[3] = (String) selectObj.get("Relation");
							friendList[4] = (String) selectObj.get("Img");

							arrayList.add(friendList);//여기까지 오면 모든 친구정보를 배열 담기 끝
							//System.out.println(arrayList.size());


						}
					}
					//메인프레임에 친구 리스트 저장
					mainFrame.friendList=arrayList; 
					master= new Master(ct, mainFrame , this);//모든 객체에 접근하기 쉽게 만든 클래스
					
					
					
				}


			} else {// json을 배열 형식으로 받지 않을때
				JSONObject orderObj = new JSONObject();
				JSONObject jsonObj = (JSONObject) obj;
				String type = (String) jsonObj.get("Type");
				if (type.equals("loginresult")) {
					System.out.println("loginresult 나옴 ");
					// 내 정보 저장!
					MyCode = (String) jsonObj.get("Code");// 프린트 잘 나옴
					MyId = (String) jsonObj.get("Id");
					MyName = (String) jsonObj.get("Name");
					MyBirth = (String) jsonObj.get("Birth");
					MyPhone = (String) jsonObj.get("Phone");
					MyEmail = (String) jsonObj.get("Email");
					MyNick = (String) jsonObj.get("Nick");
					MyImg = (String) jsonObj.get("Img");
					// 로그인 성고 내 데이터를 가져옴

					/*메인 프레임에 내정보 저장
					mainFrame.MyCode=MyCode;
					mainFrame.MyId=MyId;
					mainFrame.MyName=MyName;
					mainFrame.MyBirth=MyBirth;
					mainFrame.MyPhone=MyPhone;
					mainFrame.MyPhone=MyPhone;
					mainFrame.MyEmail=MyEmail;
					mainFrame.MyNick=MyNick;
					mainFrame.MyImg=MyImg;
					*/
					orderObj.put("Type", "friendlist");
					orderObj.put("Code", MyCode);
					ct.send(orderObj.toString());// 친구 목록 가져 오게 오더 함
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
