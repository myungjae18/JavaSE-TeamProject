package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Setting extends JFrame{
	JPanel p_north, p_center, p_south;
	JPanel p_north_west, p_north_east, p_north_east_north, p_north_east_south;
	JPanel p_center_west_north, p_center_west_center, p_center_west_south;
	JPanel p_center_west, p_center_east;
	JPanel p_colorSelect;
	JLabel la_chatTitle, la_background, la_backgroundChoose;
	JButton bt_choose, bt_confirm, bt_cancel;
	JTextField tf_chatTitle;
	ImageIcon profileThumb;
	BackgroundColorList backgroundColorList;
	Rectangle dm;
	ChatMain chatMain;
	
	public Setting(ChatMain chatMain) {
		this.chatMain = chatMain;
		p_north = new JPanel();
		p_north_west = new JPanel();
		p_north_east = new JPanel();
		p_north_east_north = new JPanel();
		p_north_east_south = new JPanel();
		p_center = new JPanel();
//		p_center_west = new JPanel();
//		p_center_east = new JPanel();
		p_south = new JPanel();
		p_center_west = new JPanel();
		p_center_east = new JPanel();
		p_center_west_north = new JPanel();
		p_center_west_center = new JPanel();
		p_center_west_south = new JPanel();
		p_colorSelect = new JPanel();
		la_chatTitle = new JLabel("채팅방 이름");
		tf_chatTitle = new JTextField(20);
		la_background = new JLabel("채팅방 배경화면");
		la_backgroundChoose = new JLabel("채팅창 배경을 설정합니다");
		bt_choose = new JButton("배경화면 선택");
		bt_confirm = new JButton("확인");
		bt_cancel = new JButton("취소");
		
		p_north.setPreferredSize(new Dimension(400, 150));
		p_south.setPreferredSize(new Dimension(400, 70));
		p_center.setPreferredSize(new Dimension(400, 100));
		p_north_west.setPreferredSize(new Dimension(130, 130));
		p_north_east.setPreferredSize(new Dimension(150, 150));
		p_north_east_north.setPreferredSize(new Dimension(150, 75));
		p_north_east_south.setPreferredSize(new Dimension(150, 75));
		p_center_west.setPreferredSize(new Dimension(250, 200));
		p_center_east.setPreferredSize(new Dimension(150, 200));
		p_center_west_north.setPreferredSize(new Dimension(250, 40));
		p_center_west_center.setPreferredSize(new Dimension(250, 30));
		p_center_west_south.setPreferredSize(new Dimension(250, 40));
		p_colorSelect.setPreferredSize(new Dimension(70, 120));
		p_north.setBackground(Color.RED);
//		p_center.setBackground(Color.BLUE);
		p_south.setBackground(Color.GREEN);
		p_north_east_north.setBackground(Color.CYAN);
		p_north_east_south.setBackground(Color.DARK_GRAY);
		p_north_west.setBackground(Color.BLACK);
		p_center_west.setBackground(Color.YELLOW);
		p_center_east.setBackground(Color.RED);
		p_center_west_north.setBackground(Color.PINK);
		p_center_west_center.setBackground(Color.DARK_GRAY);
		p_center_west_south.setBackground(Color.ORANGE);
		
		p_south.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		p_north_east_north.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		p_north_east_south.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		p_center_west_north.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		p_center_west_center.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		p_center_west_south.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		p_north.setLayout(new BorderLayout());
		p_north_east.setLayout(new BorderLayout());
		p_center.setLayout(new BorderLayout());
		p_center_west.setLayout(new BorderLayout());
		p_north.add(p_north_west, BorderLayout.WEST);
		
		p_north.add(p_north_east);
		p_north_east_north.add(la_chatTitle);
		p_north_east_south.add(tf_chatTitle);
		p_north_east.add(p_north_east_north, BorderLayout.NORTH);
		p_north_east.add(p_north_east_south);
		p_center.add(p_center_west, BorderLayout.WEST);
		p_center.add(p_center_east, BorderLayout.EAST);
		p_center_west.add(p_center_west_north, BorderLayout.NORTH);
		p_center_west.add(p_center_west_center);
		p_center_west.add(p_center_west_south, BorderLayout.SOUTH);
		p_center_west_north.add(la_background);
		p_center_west_center.add(la_backgroundChoose);
		p_center_west_south.add(bt_choose);
		p_center_east.add(p_colorSelect, BorderLayout.CENTER);
		p_south.add(bt_confirm);
		p_south.add(bt_cancel);
		
		backgroundColorList = new BackgroundColorList(this, chatMain);

//		p_north_east.setBackground(Color.WHITE);
		tf_chatTitle.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					String title = tf_chatTitle.getText();
					chatMain.setTitle(title);
				}
			}
		});
	
		add(p_north, BorderLayout.NORTH);
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		bt_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dm = getBounds();
				backgroundColorList.setLocation(dm.x + 390, dm.y);
				backgroundColorList.setVisible(true);
			}
		});	
		setTitle("채팅방 설정");
		setSize(400, 400);
		setVisible(false);
		setResizable(false);	
	}
}
