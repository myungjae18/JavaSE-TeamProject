package messenger.chatlist;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import messenger.chat.ChatMain;
import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class ChatListMain extends JPanel {
   public JFrame plusBt;
   public JPanel p_plus;
   JLabel lb_plus;
   Image img_plus;
   ImageLoad imageLoad;
   public ChatListPanel[] chatArray = new ChatListPanel[10]; // ä�ø���� ��� �迭
   MainFrame mainFrame;
   ChatListOption chatListOption;
   JScrollPane scroll;

   public ChatListMain(MainFrame mainFrame) {
      this.mainFrame = mainFrame;

      // ä�ø�� ����
      for (int i = 0; i < chatArray.length; i++) {
         chatArray[i] = new ChatListPanel(mainFrame);
         add(chatArray[i]);
         chatArray[i].addMouseListener(new MouseAdapter() {

            // ä��â Ŭ���� �̺�Ʈ ����
            public void mouseClicked(MouseEvent e) {
               if (chatListOption != null) {
                  chatListOption.dispose();
               }
               if (e.getClickCount() > 1) { // ���� Ŭ�� ���� �� ä�ù� ����
                  new ChatMain("�ȳ�", mainFrame);
               }
               if (SwingUtilities.isRightMouseButton(e)) { // ���콺 ��Ŭ�� ���� ��
                  chatListOption = new ChatListOption(mainFrame);
                  int x = (int) e.getLocationOnScreen().getX(); // ���� x�� ������
                  int y = (int) e.getLocationOnScreen().getY();// ���� y�� ������
                  chatListOption.setBounds(x + 5, y + 5, 130, 125);// �ɼ�ȭ�� ��ġ,ũ�� �����ֱ�
               }
            }
         });
      }

      // �߿� ȭ�� ���� �����ϴ� ������
      addComponentListener(new ComponentAdapter() {
         public void componentResized(ComponentEvent e) {
            Dimension d = e.getComponent().getSize();
            int width = (int) d.getWidth();
            for (int i = 0; i < chatArray.length; i++) {
               chatArray[i].setPreferredSize(new Dimension(width, 70));
            }
         }
      });

      createPlusBt(); // ä�ù� ��� ���� �÷�����ư ����

      setBackground(mainFrame.getColor());
      setPreferredSize(new Dimension(500, 650));
      setVisible(false);
   }

   // ä�ù� ��� ���� �÷�����ư ���� �Լ�
   public void createPlusBt() {
      plusBt = new JFrame();
      p_plus = new JPanel();
      lb_plus = new JLabel();
      imageLoad = new ImageLoad();

      img_plus = imageLoad.getImage("plus.png");
      lb_plus.setIcon(new ImageIcon(img_plus));
      p_plus.setBackground(new Color(0, 0, 0, 0));

      p_plus.add(lb_plus);
      plusBt.add(p_plus);

      plusBt.setUndecorated(true);
      plusBt.setBackground(new Color(0, 0, 0, 0));
      plusBt.setAlwaysOnTop(true);
      plusBt.setVisible(false);
   }
}