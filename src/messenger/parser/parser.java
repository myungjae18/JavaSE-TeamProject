/*���� �����ӿ� ������ result�� �м���*/
package messenger.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messenger.mainframe.MainFrame;

public class parser {

	public parser(String result) {
		JSONParser parser = new JSONParser();
		JSONArray array = new JSONArray();
		Object obj = null;
		Object obj2 = null;

		try {
			obj = parser.parse(result);
			JSONObject jsonObj = (JSONObject) obj;
			String type = (String) jsonObj.get("Type");
//			if (array.getClass() == obj.getClass()) { // json �迭 �������� ������
//				System.out.println("�̰� �迭 �����Դϴ�");
//				System.out.println("�迭" + obj);
//				array = (JSONArray) obj;
//				System.out.println(array.size());
//				for(int i=0;i<array.size();i++) {
//					(JSONObject) obj2=array[i];
//				}
//				if (type.equals("typeresult")) {
//					System.out.println("�˴ϴ�");
//				}
//			} // json �迭������ �ƴҶ�
		
			if (type.equals("loginresult")) {
				JSONObject orderObj = new JSONObject();
				// �� ���� ����!
//				MyCode = (String) jsonObj.get("Code");// ����Ʈ �� ����
//				MyId = (String) jsonObj.get("Id");
//				MyName = (String) jsonObj.get("Name");
//				MyBirth = (String) jsonObj.get("Birth");
//				MyPhone = (String) jsonObj.get("Phone");
//				MyEmail = (String) jsonObj.get("Email");
//				MyNick = (String) jsonObj.get("Nick");
				// �α��� ���� �� �����͸� ������

				orderObj.put("Type", "friendlist");
				orderObj.put("Code", MyId);
				ct.send(orderObj.toString());// ģ�� ��� ���� ���� ���� ��
			}else if() {
				
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
