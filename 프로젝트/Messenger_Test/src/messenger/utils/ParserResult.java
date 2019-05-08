
/*���� �����ӿ� ������ result�� �м���*/
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
	// �Ƹ� �����ؾ��ϴ� ���Ŭ������ ���� �ֵ� ���� Ŭ������ �����ҵ�
	// MainFrame �ҷ����� ���� �˾ƾ���

	public ParserResult(ClientThread ct) {
		this.ct = ct;
		
	}

	public void parse(String result) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		Object obj = null;
		Object obj2 = null;

		try {
			obj = parser.parse(result);// ��ȯ���� Object�̱⶧���� ������ jsonobject�� ����ȯ�ؾ���
			System.out.println("obj : " + obj);

			if (array.getClass() == obj.getClass()) { // json �迭 �������� ������
				JSONObject orderObj = new JSONObject();
				array = (JSONArray) obj;
				ArrayList arrayList = new ArrayList();

				JSONObject findType = (JSONObject) array.get(0);//���� Ÿ������ Ȯ���ϱ� ����
				String type = (String) findType.get("Type");

				if (type.equals("friendresult")) { //ģ�� ��ϰ� 
					for (int i = 0; i < array.size(); i++) {
						JSONObject selectObj = (JSONObject) array.get(i);
						String[] friendList = new String[selectObj.size()-1];//ģ�������� ������� �迭 -1 ������ type �� ���� ����
						
						if (mainFrame == null) { // ó�� �ѹ��� ���������� ������ ����
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

							arrayList.add(friendList);//������� ���� ��� ģ�������� �迭 ��� ��
							//System.out.println(arrayList.size());


						}
					}
					//���������ӿ� ģ�� ����Ʈ ����
					mainFrame.friendList=arrayList; 
					master= new Master(ct, mainFrame , this);//��� ��ü�� �����ϱ� ���� ���� Ŭ����
					
					
					
				}


			} else {// json�� �迭 �������� ���� ������
				JSONObject orderObj = new JSONObject();
				JSONObject jsonObj = (JSONObject) obj;
				String type = (String) jsonObj.get("Type");
				if (type.equals("loginresult")) {
					System.out.println("loginresult ���� ");
					// �� ���� ����!
					MyCode = (String) jsonObj.get("Code");// ����Ʈ �� ����
					MyId = (String) jsonObj.get("Id");
					MyName = (String) jsonObj.get("Name");
					MyBirth = (String) jsonObj.get("Birth");
					MyPhone = (String) jsonObj.get("Phone");
					MyEmail = (String) jsonObj.get("Email");
					MyNick = (String) jsonObj.get("Nick");
					MyImg = (String) jsonObj.get("Img");
					// �α��� ���� �� �����͸� ������

					/*���� �����ӿ� ������ ����
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
					ct.send(orderObj.toString());// ģ�� ��� ���� ���� ���� ��
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
