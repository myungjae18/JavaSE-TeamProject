package messenger.login;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class LoginMain extends JFrame {

	MemberLogin login;

	// 프로그램을 켰을 때 오라클 데이터베이스에 즉시 접근되야 한다
	public LoginMain() {
		super("네이트톡");
		login = new MemberLogin();
		add(login);

		// 윈도우 창 종료
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