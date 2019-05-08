/*메인 프레임에 들어오는 result값 분석기*/
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
//			if (array.getClass() == obj.getClass()) { // json 배열 형식으로 받을때
//				System.out.println("이건 배열 형식입니당");
//				System.out.println("배열" + obj);
//				array = (JSONArray) obj;
//				System.out.println(array.size());
//				for(int i=0;i<array.size();i++) {
//					(JSONObject) obj2=array[i];
//				}
//				if (type.equals("typeresult")) {
//					System.out.println("됩니당");
//				}
//			} // json 배열형식이 아닐때
		
			if (type.equals("loginresult")) {
				JSONObject orderObj = new JSONObject();
				// 내 정보 저장!
//				MyCode = (String) jsonObj.get("Code");// 프린트 잘 나옴
//				MyId = (String) jsonObj.get("Id");
//				MyName = (String) jsonObj.get("Name");
//				MyBirth = (String) jsonObj.get("Birth");
//				MyPhone = (String) jsonObj.get("Phone");
//				MyEmail = (String) jsonObj.get("Email");
//				MyNick = (String) jsonObj.get("Nick");
				// 로그인 성고 내 데이터를 가져옴

				orderObj.put("Type", "friendlist");
				orderObj.put("Code", MyId);
				ct.send(orderObj.toString());// 친구 목록 가져 오게 오더 함
			}else if() {
				
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
