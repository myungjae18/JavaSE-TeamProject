package messenger.chat;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordSearch extends JFrame{
	JPanel p_north, p_south;
	JTextField tf_search;
	JButton bt_search;
	ChatMain chatmain;
	
	public WordSearch(ChatMain chatmain) {
		this.chatmain = chatmain;
		p_north = new JPanel();
		p_south = new JPanel();
		tf_search = new JTextField(20);
		bt_search = new JButton("검색");
		
		p_north.add(tf_search);
		p_south.add(bt_search);

		tf_search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					System.out.println("검색 시작");
					search();
				}
			}
		});
		
//		p_north.setPreferredSize(new Dimension(150, 40));
		add(p_north);
		add(p_south, BorderLayout.SOUTH);
		setTitle("단어 검색");
		setSize(300, 100);
		setVisible(false);
	}
	public void search() {
//		Pattern pattern = Pattern.compile("^[가-힣a-zA-Z0-9]*$"); 
//		String chatMoniter = chatmain.area.getText();
//		System.out.println(chatMoniter);
//		String searchTarget = tf_search.getText();
//		System.out.println(searchTarget);
//		Matcher searchWord = pattern.matcher(arg0)
//		while(chatMoniter.matches(searchWord));
		
	}

}
