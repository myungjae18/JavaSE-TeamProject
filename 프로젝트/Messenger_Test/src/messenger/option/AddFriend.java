package messenger.option;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class AddFriend extends JFrame {
   JPanel p_wrapper, p_title, p_center, p_button;
   JPanel p_search, p_list, p_profile;
   JLabel lb_title, lb_search, lb_list;
   JTextField t_search;
   JButton bt_add, bt_cancel;
   Image img_search;
   ImageLoad imageLoad;
   MainFrame mainFrame;

   Font font; // 미설정시 기본 폰트
   boolean flag_search = false; // search창에서 enter를 눌렀는지 여부

   public AddFriend(MainFrame mainFrame) {
      this.mainFrame = mainFrame;

      p_wrapper = new JPanel(); // 전체 패널
      p_title = new JPanel(); // 타이틀 패널
      p_search = new JPanel(); // 검색 패널
      p_list = new JPanel(); // 검색 결과가 붙을 패널
      p_center = new JPanel(); // 검색과 검색 결과가 붙을 패널
      p_button = new JPanel(); // 확인,취소버튼을 붙일 패널

      lb_title = new JLabel("친구 추가");
      lb_title.setForeground(Color.WHITE);

      imageLoad = new ImageLoad();
      img_search = imageLoad.getImage("search.png");
      img_search = img_search.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
      lb_search = new JLabel();
      lb_search.setIcon(new ImageIcon(img_search));
      t_search = new JTextField("아이디로 친구 찾기", 20) { // JTextField 테두리 없애기
         public void setBorder(Border border) {
         }
      };
      t_search.setFocusable(false);

      lb_list = new JLabel("아이디로 친구를 추가하세요", SwingConstants.CENTER);
      bt_add = new JButton("추가");
      bt_cancel = new JButton("취소");

      // 폰트 설정
      font = new Font(mainFrame.getFontString(), Font.PLAIN, 14);
      lb_title.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 16));
      t_search.setFont(font);
      lb_list.setFont(font);
      bt_add.setFont(font);
      bt_cancel.setFont(font);

      // 사이즈 조절
      p_title.setPreferredSize(new Dimension(400, 40));
      p_title.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
      lb_search.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
      t_search.setPreferredSize(new Dimension(250, 40));
      p_search.setPreferredSize(new Dimension(400, 50));
      lb_list.setPreferredSize(new Dimension(300, 20));
      p_list.setPreferredSize(new Dimension(400, 250));
      p_list.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
      p_center.setPreferredSize(new Dimension(400, 300));
      bt_add.setPreferredSize(new Dimension(70, 40));
      bt_cancel.setPreferredSize(new Dimension(70, 40));
      p_button.setPreferredSize(new Dimension(400, 60));
      p_button.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_wrapper.setPreferredSize(new Dimension(400, 400));

      // 배경색 설정
      p_title.setBackground(mainFrame.getColor());
      p_search.setBackground(Color.WHITE);
      p_search.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_list.setBackground(Color.WHITE);
      p_center.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      bt_add.setBackground(Color.YELLOW);
      bt_cancel.setBackground(Color.WHITE);
      p_button.setBackground(Color.WHITE);
      p_wrapper.setBorder(BorderFactory.createLineBorder(Color.GRAY));

      // 레이아웃 설정
      p_search.setLayout(new BorderLayout());
      p_center.setLayout(new BorderLayout());
      p_wrapper.setLayout(new BorderLayout());

      // 부착
      p_title.add(lb_title);
      p_search.add(lb_search, BorderLayout.WEST);
      p_search.add(t_search);
      p_list.add(lb_list);
      p_center.add(p_search, BorderLayout.NORTH);
      p_center.add(p_list);
      p_button.add(bt_add);
      p_button.add(bt_cancel);

      p_wrapper.add(p_title, BorderLayout.NORTH);
      p_wrapper.add(p_center);
      p_wrapper.add(p_button, BorderLayout.SOUTH);
      add(p_wrapper);

      // 검색창을 클릭했을 때 안내문구가 안보이게 설정
      t_search.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            t_search.setText("");
            t_search.setFocusable(true);
         }

         public void mouseExited(MouseEvent e) {
            t_search.setText("아이디로 친구 찾기");
            t_search.setFocusable(false);
         }
      });

      // 검색창과 리스너 연결
      t_search.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
               t_search.setText("아이디로 친구 찾기");
               t_search.setFocusable(false);
               flag_search = true;
               showProfile();
            }
         }
      });

      // 확인 버튼 눌렀을 때 친구 추가
      bt_add.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AddFriend.this, "친구 추가 완료");
            dispose();
         }
      });

      // 취소 버튼과 리스너 연결
      bt_cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });

      setSize(400, 400);
      setResizable(false);
      setUndecorated(true);
      setVisible(true);
   }

   // 검색 결과를 나타내는 프로필 생성
   public void showProfile() {
      p_profile = new JPanel(); // 프로필 전체 패널
      JPanel p_img = new JPanel(); // 프사를 붙일 패널
      JPanel p_name = new JPanel(); // 이름과 별명을 붙일 패널

      JLabel lb_img = new JLabel();
      Image img = imageLoad.getImage("cat.jpg");
      img = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
      BufferedImage circleBuffer = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = circleBuffer.createGraphics();
      g2.setComposite(AlphaComposite.Src);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.fill(new RoundRectangle2D.Float(0, 0, 150, 150, 130, 130));
      g2.setComposite(AlphaComposite.SrcAtop);
      g2.drawImage(img, 0, 0, 150, 150, null);
      g2.dispose();
      lb_img.setIcon(new ImageIcon(circleBuffer));

      JLabel lb_name_info = new JLabel("이름", SwingConstants.LEFT);
      JLabel lb_name = new JLabel("홍길동", SwingConstants.LEFT);
      JLabel lb_nick_info = new JLabel("별명", SwingConstants.LEFT);
      JLabel lb_nick = new JLabel("나는길동이", SwingConstants.LEFT);

      // 폰트 설정
      font = new Font(mainFrame.getFontString(), Font.BOLD, 17);
      lb_name_info.setFont(font);
      lb_nick_info.setFont(font);
      font = new Font(mainFrame.getFontString(), Font.PLAIN, 15);
      lb_name.setFont(font);
      lb_nick.setFont(font);

      // 레이아웃 설정
      p_profile.setLayout(new BorderLayout());

      // 사이즈 조절
      p_profile.setPreferredSize(new Dimension(350, 200));
      p_img.setPreferredSize(new Dimension(170, 200));
      p_img.setBorder(BorderFactory.createEmptyBorder(17, 20, 0, 0));
      lb_name_info.setPreferredSize(new Dimension(100, 30));
      lb_name.setPreferredSize(new Dimension(100, 30));
      lb_nick_info.setPreferredSize(new Dimension(100, 30));
      lb_nick.setPreferredSize(new Dimension(100, 30));
      p_name.setPreferredSize(new Dimension(180, 200));
      p_name.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

      // 배경색 설정
      p_img.setBackground(mainFrame.getColor());
      p_name.setBackground(mainFrame.getColor());
      p_profile.setBorder(BorderFactory.createLineBorder(Color.GRAY));

      // 검색하면 안내문구가 안보이게 설정
      if (flag_search) {
         p_list.remove(lb_list);
         p_list.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
      }

      // 부착
      p_img.add(lb_img);
      p_name.add(lb_name_info);
      p_name.add(lb_name);
      p_name.add(lb_nick_info);
      p_name.add(lb_nick);

      p_profile.add(p_img, BorderLayout.WEST);
      p_profile.add(p_name);
      p_list.add(p_profile);
   }
}