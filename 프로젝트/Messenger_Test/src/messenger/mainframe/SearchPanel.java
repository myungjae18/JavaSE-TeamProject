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
	public JTextField t_search; // �˻��ϴ� ��
	JLabel lb_search; // �˻������� ���̴� ��
	Image img_search; // �˻������� �̹���
	ImageLoad imageLoad;
	String name = "�̸� �˻�"; // �˻�â �ȳ�����

	public SearchPanel() {
		setLayout(new BorderLayout());
		
		imageLoad = new ImageLoad();
		img_search = imageLoad.getImage("search.png");
		img_search = img_search.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		lb_search = new JLabel();
		lb_search.setIcon(new ImageIcon(img_search));

		t_search = new JTextField(name, 35) { // JTextField �׵θ� ���ֱ�
			public void setBorder(Border border) {

			}
		};
		t_search.setFocusable(false);
		
		// ������ ����
		lb_search.setBounds(20, 0, 80, 50);
		lb_search.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // ������ ��ġ�� ���� padding(���ʰ���)
		t_search.setPreferredSize(new Dimension(410, 50));
		
		// ����
		add(lb_search, BorderLayout.WEST);
		add(t_search);

		t_search.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				t_search.setText("");
				t_search.setFocusable(true);
			}
		});
	}

	// �������� ���� �˻� �ȳ����� ����
	public void setInfoText(int p_num) {
		if (p_num == 0) {
			name = "�̸� �˻�";
		} else if (p_num == 1) {
			name = "ä�ù� �̸�, ������ �˻�";
		}
		t_search.setText(name);
	}
	//��ġ�г��� 2���� Ÿ���� �����ؾ��� ģ����� ã��� ä�ù� ��� ã��
	//�׸��� �˻��� �ǽð� Ű���� ���������� �ٲ�ϱ�
	//������� �� ������ ������ �Ȼ�� ��γ�����
	//text�� Ű���� ������ �༭ Ű���� ���������� json������ �������� �ؾ��ҰŰ���....���������� ����
	/*
	 *
	public void outputJSON() {
		
		BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		JSONObject json = new JSONObject();
		//ģ�� ���
		json.put("Type", "searchfriend");
		json.put("user_code", "����");
		
		//ä�ù� ���
		 json.put("Type", "searchchatlist");
		json.put("user_code", "����");
		buffw.write(json.toString());
		
		
	}

	public void inputJSON() {
		BufferedReader buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
		JSONParser parser =new JSONParser();
		String json = buffr.readLine();
		
		JSONObject obj=(JSONObject)parser.parse(json);
		//�迭�� ���ϱ�  for�� ������ obj[i].get �ؾ� �ϳ�??
		//����Բ� ���� 
		//�ֿ��Բ� ���� ���� ģ�� ��� ���������� ���̽� ������ �ʴ� ���̽��� �̷������� �����ָ� �Ǵ°ǰ���?
		//�޾ƿö� [{"name":"���ֿ�", "pk":"30", "pic":"img"},{"name":"���ȣ", "pk":"10", "pic":"img"}]  �̷������� �������� ���� ��� �ϴ°�??
		obj.get();
		
	}*/
}