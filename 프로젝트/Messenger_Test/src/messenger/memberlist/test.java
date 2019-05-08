package messenger.memberlist;

import org.json.simple.JSONObject;

public class test {

	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("Type", "login");
		obj.put("Id", "aa");
		obj.put("Pw", "bb");
		//userClient.order = obj.toString();
		System.out.println(obj.toString());
	}
}
