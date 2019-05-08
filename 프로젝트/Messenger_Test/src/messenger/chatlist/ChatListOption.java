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
   Font font;  //�̼����� �⺻��Ʈ
   
   public ChatListOption(MainFrame mainFrame) {
      p_openChat = new JPanel();  //ä�ù� ���� �г�
      p_fixChat = new JPanel();  //ä�ù� ��� ���� �г�
      p_exitChat = new JPanel();  //ä�ù� ������ �г�
      
      lb_openChat = new JLabel("ä�ù� ����");
      lb_fixChat = new JLabel("ä�ù� ��� ����");
      lb_exitChat = new JLabel("ä�ù� ������");
      
      //��Ʈ ����
      font = new Font(mainFrame.getFontString(), Font.PLAIN, 12);
      lb_openChat.setFont(font);
      lb_fixChat.setFont(font);
      lb_exitChat.setFont(font);
      
      //������ ����
      Dimension d = new Dimension(100,25);
      lb_openChat.setPreferredSize(d);
      lb_openChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      lb_fixChat.setPreferredSize(d);
      lb_fixChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      lb_exitChat.setPreferredSize(d);
      lb_exitChat.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      
      //���� ����
      p_openChat.setBackground(Color.WHITE);
      p_openChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_fixChat.setBackground(Color.WHITE);
      p_fixChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_exitChat.setBackground(Color.WHITE);
      p_exitChat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      
      //���̾ƿ� ����
      setLayout(new GridLayout(3, 1));
      
      //����
      p_openChat.add(lb_openChat);
      p_fixChat.add(lb_fixChat);
      p_exitChat.add(lb_exitChat);
      
      add(p_openChat);
      add(p_fixChat);
      add(p_exitChat);
      
      //ä�ù� ���� �󺧰� ������ ����
      lb_openChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            new ChatMain("�ȳ�", mainFrame);
            dispose();
         }
      });
      
      //ä�ù� ��� ���� �󺧰� ������ ����
      lb_fixChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(mainFrame, "ä�ù� ��� ����");
            dispose();
         }
      });
      
      //ä�ù� ������ �󺧰� ������ ����
      lb_exitChat.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            JOptionPane.showMessageDialog(mainFrame, "ä�ù� ������");
            dispose();
         }
      });
      
      setSize(100,75);
      setUndecorated(true);
      setVisible(true);
   }
}