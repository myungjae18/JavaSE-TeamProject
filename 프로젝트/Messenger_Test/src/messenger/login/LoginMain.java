package messenger.login;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class LoginMain extends JFrame {

	MemberLogin login;

	// ���α׷��� ���� �� ����Ŭ �����ͺ��̽��� ��� ���ٵǾ� �Ѵ�
	public LoginMain() {
		super("����Ʈ��");
		login = new MemberLogin();
		add(login);

		// ������ â ����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setBounds(700, 200, 500, 800);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LoginMain();
	}
}