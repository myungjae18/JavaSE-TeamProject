package messenger.chatlist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import messenger.chat.ChatMain;
import messenger.mainframe.MainFrame;

public class ChatListOption extends JFrame{
   JPanel p_openChat, p_fixChat, p_exitChat;
   JLabel lb_openChat, lb_fixChat, lb_exitChat;
   Font font;  //미설정시 기본폰트
   
   public ChatListOption(MainFrame mainFrame) {
      p_openChat = new JPanel();  //채팅방 열기 패널
      p_fixChat = new JPanel();  //채팅방 상단 고정 패널
      p_exitChat = new JPanel();  //채팅방 나가기 패널
      
      lb_openChat = new JLabel("채팅방 열기");
      lb_fixChat = new JLabel("채팅방 상단 고정");
      lb_exitChat = new JLabel("채팅방 나가기");
      
      //폰트 설정
      font = new Font(mainFrame.getFontString(), Font.PLAIN, 12);
      lb_openChat.setFont(font);
      lb_fixChat.setFont(font);
      lb_exitChat.setFont(font);
      
      //사이즈 조절
      Dimension d = new Dimension(100,25);
      lb_openChat.setPreferredSize(d);
      lb_openChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      lb_fixChat.setPreferredSize(d);
      lb_fixChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      lb_exitChat.setPreferredSize(d);
      lb_exitChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      
      //배경색 설정
      p_openChat.setBackground(Color.WHITE);
      p_openChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_fixChat.setBackground(Color.WHITE);
      p_fixChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_exitChat.setBackground(Color.WHITE);
      p_exitChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      
      //레이아웃 설정
      setLayout(new GridLayout(3, 1));
      
      //부착
      p_openChat.add(lb_openChat);
      p_fixChat.add(lb_fixChat);
      p_exitChat.add(lb_exitChat);
      
      add(p_openChat);
      add(p_fixChat);
      add(p_exitChat);
      
      //채팅방 열기 라벨과 리스너 연결
      lb_openChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            new ChatMain("안녕", mainFrame);
            dispose();
         }
      });
      
      //채팅방 상단 고정 라벨과 리스너 연결
      lb_fixChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(mainFrame, "채팅방 상단 고정");
            dispose();
         }
      });
      
      //채팅방 나가기 라벨과 리스너 연결
      lb_exitChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(mainFrame, "채팅방 나가기");
            dispose();
         }
      });
      
      setSize(100,75);
      setUndecorated(true);
      setVisible(true);
   }
}