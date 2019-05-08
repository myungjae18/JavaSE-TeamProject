package messenger.chatlist;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class ChatListPanel extends JPanel {
   public JPanel p_left, p_center, p_right;
   public JPanel p_img, p_name, p_content;
   public JLabel la_img, la_name, la_content, la_time;
   Image img;
   ImageLoad imageLoad;
   MainFrame mainFrame;

   public ChatListPanel(MainFrame mainFrame) {
      this.mainFrame = mainFrame;
      setLayout(new BorderLayout());

      p_left = new JPanel();
      p_center = new JPanel();
      p_right = new JPanel();
      p_img = new JPanel();
      p_name = new JPanel();
      p_content = new JPanel();
      la_img = new JLabel();
      la_name = new JLabel("", SwingConstants.LEFT);
      la_content = new JLabel("", SwingConstants.LEFT);
      la_time = new JLabel();

      imageLoad = new ImageLoad();
      img = imageLoad.getImage("cat.jpg");
      img = img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
      
      BufferedImage circleBuffer = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = circleBuffer.createGraphics();
      g2.setComposite(AlphaComposite.Src);//불투명 알파값 오브젝트(뭔 말인지 어려워서 모르겠음) 이게 시작이고 밑에꺼가 전송처 인듯?
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//이미지 계단현상 방지
      //g2.setClip(new Ellipse2D.Float(0, 0, 60, 60));
      g2.fill(new RoundRectangle2D.Float(0, 0, 60, 60, 50, 50));
      g2.setComposite(AlphaComposite.SrcAtop);
      g2.drawImage(img, 0, 0, 60, 60, null);
      g2.dispose();

      la_img.setIcon(new ImageIcon(circleBuffer));
      la_name.setText("채팅방 이름");
      la_name.setFont(new Font("돋움", Font.BOLD, 18));
      la_name.setPreferredSize(new Dimension(250, 20));
      la_content.setText("채팅 내용 안녕하세요 1234");
      la_content.setFont(new Font("돋움", Font.PLAIN, 14));
      la_content.setPreferredSize(new Dimension(250, 20));
      la_time.setText("오후 1:00");
      la_time.setFont(new Font("돋움", Font.PLAIN, 14));
      la_time.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));

      p_left.setBackground(Color.WHITE);
      p_center.setBackground(Color.WHITE);
      p_right.setBackground(Color.WHITE);
      p_img.setBackground(Color.WHITE);
      p_name.setBackground(Color.WHITE);
      p_content.setBackground(Color.WHITE);

      p_img.add(la_img);
      p_name.add(la_name);
      p_content.add(la_content);
      
      p_img.setPreferredSize(new Dimension(60, 70));
      la_img.setBounds(0, 0, 60, 60);

      p_img.setLayout(new BorderLayout());
      p_center.setLayout(new BorderLayout());
      
      p_center.add(p_name, BorderLayout.NORTH);
      p_center.add(p_content, BorderLayout.SOUTH);
      p_left.add(p_img);
      p_left.add(p_center);
      p_right.add(la_time);

      add(p_left, BorderLayout.WEST);
      add(p_right, BorderLayout.EAST);

      addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            ChatListPanel p = (ChatListPanel) e.getComponent();
            Color c = mainFrame.getColor();
            p.p_left.setBackground(c);
            p.p_center.setBackground(c);
            p.p_right.setBackground(c);
            p.p_img.setBackground(c);
            p.p_name.setBackground(c);
            p.p_content.setBackground(c);
            p.setBackground(c);
         }

         public void mouseExited(MouseEvent e) {
            ChatListPanel p = (ChatListPanel) e.getComponent();
            p.p_left.setBackground(Color.WHITE);
            p.p_center.setBackground(Color.WHITE);
            p.p_right.setBackground(Color.WHITE);
            p.p_img.setBackground(Color.WHITE);
            p.p_name.setBackground(Color.WHITE);
            p.p_content.setBackground(Color.WHITE);
            p.setBackground(Color.WHITE);
         }
      });

      setPreferredSize(new Dimension(500, 70));
      setBackground(Color.WHITE);
   }
}