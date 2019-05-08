package messenger.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import messenger.utils.ImageLoad;

public class SearchPanel extends JPanel {
	public JTextField t_search; // 검색하는 곳
	JLabel lb_search; // 검색아이콘 붙이는 라벨
	Image img_search; // 검색아이콘 이미지
	ImageLoad imageLoad;
	String name = "이름 검색"; // 검색창 안내문구

	public SearchPanel() {
		setLayout(new BorderLayout());
		
		imageLoad = new ImageLoad();
		img_search = imageLoad.getImage("search.png");
		img_search = img_search.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lb_search = new JLabel();
		lb_search.setIcon(new ImageIcon(img_search));

		t_search = new JTextField(name, 35) { // JTextField 테두리 없애기
			public void setBorder(Border border) {

			}
		};
		t_search.setFocusable(false);
		
		// 사이즈 조절
		lb_search.setBounds(20, 0, 80, 50);
		lb_search.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // 아이콘 배치를 위해 padding(안쪽공백)
		t_search.setPreferredSize(new Dimension(410, 50));
		
		// 부착
		add(lb_search, BorderLayout.WEST);
		add(t_search);

		t_search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				t_search.setText("");
				t_search.setFocusable(true);
			}
		});
	}

	// 페이지에 따른 검색 안내문자 설정
	public void setInfoText(int p_num) {
		if (p_num == 0) {
			name = "이름 검색";
		} else if (p_num == 1) {
			name = "채팅방 이름, 참여자 검색";
		}
		t_search.setText(name);
	}
	//서치패널은 2개자 타입이 존재해야함 친구목록 찾기랑 채팅방 목록 찾기
	//그리고 검색은 실시간 키보드 누를때마다 바뀌니까
	//예를들어 김 누르면 김으로 된사람 모두나오고
	//text에 키보드 리스너 줘서 키보드 누를때마다 json보내는 형식으로 해야할거같음....어려울것으로 예상
	/*
	 *
	public void outputJSON() {
		
		BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		JSONObject json = new JSONObject();
		//친구 목록
		json.put("Type", "searchfriend");
		json.put("user_code", "변수");
		
		//채팅방 목록
		 json.put("Type", "searchchatlist");
		json.put("user_code", "변수");
		buffw.write(json.toString());
		
		
	}

	public void inputJSON() {
		BufferedReader buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
		JSONParser parser =new JSONParser();
		String json = buffr.readLine();
		
		JSONObject obj=(JSONObject)parser.parse(json);
		//배열로 오니까  for문 돌려서 obj[i].get 해야 하나??
		//강사님께 질문 
		//주영님께 질문 내가 친구 목록 가져오려고 제이슨 날리면 너는 제이슨을 이런식으로 보내주면 되는건가요?
		//받아올때 [{"name":"이주영", "pk":"30", "pic":"img"},{"name":"김민호", "pk":"10", "pic":"img"}]  이런식으로 서버에서 보내 줘야 하는가??
		obj.get();
		
	}*/
}