package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messenger.utils.ImageLoad;

public class ChatRoomProfile extends JFrame{
   ChatMain chatMain;
   JPanel p_north, p_south, p_center;
   JLabel la_nick, la_member;
   ImageLoad imageLoad;
   Image profile_img;
   ImageIcon profileThumb;
   JLabel[] la_profile = new JLabel[4];
   public ChatRoomProfile(ChatMain chatMain) {
      this.chatMain = chatMain;
      this.setLayout(new BorderLayout());
      imageLoad = new ImageLoad();
      p_north = new JPanel();
      p_center = new JPanel();
      p_south = new JPanel();
      
      la_nick = new JLabel("별명");
      la_member = new JLabel("채팅참가자");
      
      la_member.setPreferredSize(new Dimension(150, 20));
      la_nick.setPreferredSize(new Dimension(50, 20));
      p_north.setPreferredSize(new Dimension(350, 100));
      p_center.setPreferredSize(new Dimension(350, 50));
      p_south.setPreferredSize(new Dimension(350, 200));
      p_center.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
      p_north.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      
      for(int i = 0; i < la_profile.length; i += 1) {
         profile_img = imageLoad.getImage(chatMain.getImgName()).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
         profileThumb = new ImageIcon(profile_img);
         la_profile[i] = new JLabel(profileThumb, JLabel.CENTER);
         p_north.add(la_profile[i]);
      }
      
//      p_north.setBackground(Color.BLACK);
      p_center.setBackground(Color.BLUE);
//      p_south.setBackground(Color.RED);
         
      p_center.add(la_member);
      p_center.add(la_nick);
      add(p_north, BorderLayout.NORTH);
      add(p_center);
      add(p_south, BorderLayout.SOUTH);
         
      setTitle("채팅방 정보");
      setBackground(Color.RED);
      setSize(350, 400);
      setResizable(false);
      setVisible(false);
      
   }
}