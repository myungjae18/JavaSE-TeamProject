package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messenger.utils.ImageLoad;

public class Emoticon extends JFrame{
	JPanel p_north;
	JPanel p_south;
	JPanel p_emoticonTitle;
	JLabel la_emoticonTitle;
	ImageIcon emoticonTitle_icon;
	Image emoticonTitle_img;
	Image emoticonTitle_newImg;
	JPanel p_emoticonTitle2;
	JLabel la_emoticonTitle2;
	ImageIcon emoticonTitle_icon2;
	Image emoticonTitle_img2;
	Image emoticonTitle_newImg2;
	ArrayList emoticonList = new ArrayList();
	JPanel p_emoticon;
	JLabel la_emoticon;
	ImageIcon emoticon_icon;
	Image emoticon_icon_img;
	Image emoticon_icon_newImg;
	ChatMain chatMain;
	Dimension dm;	
	EmoticonList1 emoticonList1;
	EmoticonList2 emoticonList2;
	ImageLoad imageLoad;
	
	public Emoticon(ChatMain chatMain) {
		this.chatMain = chatMain;
		p_north = new JPanel();
		p_south = new JPanel();	
		p_emoticonTitle = new JPanel();
		p_emoticonTitle2 = new JPanel();
		p_emoticon = new JPanel();
		dm = new Dimension();
		emoticonList1 = new EmoticonList1(chatMain);
		emoticonList2 = new EmoticonList2(chatMain);
		imageLoad = new ImageLoad();
		
		//--------------------------------------------------------------------------------------------------
		emoticonTitle_img = imageLoad.getImage("cat.jpg");
		emoticonTitle_img = emoticonTitle_img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		emoticonTitle_icon = new ImageIcon(emoticonTitle_img);
		
		la_emoticonTitle = new JLabel(emoticonTitle_icon, JLabel.CENTER);
		p_emoticonTitle.add(la_emoticonTitle);
		
		emoticonTitle_img2 = imageLoad.getImage("cat.jpg");
		emoticonTitle_img2 = emoticonTitle_img2.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		emoticonTitle_icon2 = new ImageIcon(emoticonTitle_img2);
		
		la_emoticonTitle2 = new JLabel(emoticonTitle_icon2, JLabel.CENTER);
		p_emoticonTitle2.add(la_emoticonTitle2);
		
		//--------------------------------------------------------------------------------------------------------------------------
//		
		p_north.add(p_emoticonTitle);
		p_north.add(p_emoticonTitle2);
		p_south.add(p_emoticon);
		p_emoticon.add(emoticonList1);
		p_emoticon.add(emoticonList2);
		p_emoticonTitle.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setTitle("1번 이모티콘");
				emoticonList1.setVisible(true);
			emoticonList2.setVisible(false);
//				
			}
		});
		p_emoticonTitle2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setTitle("2번 이모티콘");
				emoticonList2.setVisible(true);
				emoticonList1.setVisible(false);
			}
		});
		//----------------------------------------------------------------------------------------------------
		
		


		//-----------------------------------------------------------------------------------------------------------
		setTitle("이모티콘이름, 클릭시 테마별이름 설정");
		add(p_north, BorderLayout.NORTH);
		add(p_south);
		
		
		setSize(300, 300);
		setVisible(false);
	}
}
