package messenger.option;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.List;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class AddChat extends JFrame {
   JPanel p_wrapper, p_title, p_left, p_addList, p_button;
   JPanel p_search, p_friendList;
   JLabel lb_title, lb_search, lb_addList;
   JTextField t_search;
   JButton bt_add, bt_cancel;
   JScrollPane scroll, addScroll;
   Image img_search;
   ImageLoad imageLoad;
   MainFrame mainFrame;

   Font font; // 미설정시 기본 폰트
   boolean flag_visible = false; // AddChat 패널의 visible
   int addCount = 0; // addList에 붙는 패널 개수
   ArrayList friendList = new ArrayList(); // 친구이름을 담는 배열
   ArrayList<JPanel> addList = new ArrayList(); // 추가되는 친구 패널을 담는 배열
   JSONArray array = new JSONArray(); // 서버로 보낼 새채팅방 생성에 필요한 정보를 담는 배열

   public AddChat(MainFrame mainFrame) {
      this.mainFrame = mainFrame;

      p_wrapper = new JPanel(); // 전체 패널
      p_title = new JPanel(); // 타이틀 패널
      p_left = new JPanel(); // 검색과 친구목록이 붙을 패널
      p_search = new JPanel(); // 검색 패널
      p_friendList = new JPanel(); // 친구목록 패널
      p_addList = new JPanel(); // 대화상대로 추가한 친구목록 패널
      p_button = new JPanel(); // 확인,취소버튼을 붙일 패널

      lb_title = new JLabel("대화상대 선택");
      lb_title.setForeground(Color.WHITE);

      imageLoad = new ImageLoad();
      img_search = imageLoad.getImage("search.png");
      img_search = img_search.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

      lb_search = new JLabel();
      lb_search.setIcon(new ImageIcon(img_search));
      t_search = new JTextField("이름 검색", 20) { // JTextField 테두리 없애기
         public void setBorder(Border border) {
         }
      };
      t_search.setFocusable(false);

      lb_addList = new JLabel("초대할 친구를 선택하세요");
      bt_add = new JButton("확인");
      bt_cancel = new JButton("취소");
      scroll = new JScrollPane(p_left, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      addScroll = new JScrollPane(p_addList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

      // 폰트 설정
      font = new Font(mainFrame.getFontString(), Font.PLAIN, 14);
      lb_title.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 16));
      t_search.setFont(font);
      lb_addList.setFont(font);
      bt_add.setFont(font);
      bt_cancel.setFont(font);

      // 친구목록 생성 - 임시
      friendList.add("김민호");
      friendList.add("이주영");
      friendList.add("남학선");
      friendList.add("오혜린");
      friendList.add("신명재");
      getFriend(0, "김민호");
      getFriend(1, "이주영");
      getFriend(2, "남학선");
      getFriend(3, "오혜린");
      getFriend(4, "신명재");

      // 사이즈 조절
      p_title.setPreferredSize(new Dimension(600, 40));
      p_title.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
      lb_search.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
      t_search.setPreferredSize(new Dimension(300, 40));
      p_left.setPreferredSize(new Dimension(350, 400));
      lb_addList.setBounds(125, 200, 125, 20);
      p_addList.setPreferredSize(new Dimension(210, 400));
      p_addList.setBorder(BorderFactory.createEmptyBorder(180, 15, 0, 0));
      bt_add.setPreferredSize(new Dimension(70, 40));
      bt_cancel.setPreferredSize(new Dimension(70, 40));
      p_button.setPreferredSize(new Dimension(600, 60));
      p_button.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_wrapper.setPreferredSize(new Dimension(600, 500));

      // 배경색 설정
      p_title.setBackground(mainFrame.getColor());
      p_search.setBackground(Color.WHITE);
      p_search.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_friendList.setBackground(Color.WHITE);
      bt_add.setBackground(Color.YELLOW);
      bt_cancel.setBackground(Color.WHITE);
      p_button.setBackground(Color.WHITE);
      p_wrapper.setBorder(BorderFactory.createLineBorder(Color.GRAY));

      // 레이아웃 설정
      p_left.setLayout(new BorderLayout());
      p_search.setLayout(new BorderLayout());
      p_wrapper.setLayout(new BorderLayout());

      // 부착
      p_title.add(lb_title);
      p_search.add(lb_search, BorderLayout.WEST);
      p_search.add(t_search);
      p_left.add(p_search, BorderLayout.NORTH);
      p_left.add(p_friendList);
      p_addList.add(lb_addList);
      p_button.add(bt_add);
      p_button.add(bt_cancel);

      p_wrapper.add(p_title, BorderLayout.NORTH);
      p_wrapper.add(scroll, BorderLayout.WEST);
      p_wrapper.add(addScroll, BorderLayout.EAST);
      p_wrapper.add(p_button, BorderLayout.SOUTH);
      add(p_wrapper);

      // 검색창을 클릭했을 때 안내문구가 안보이게 설정
      t_search.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            t_search.setText("");
            t_search.setFocusable(true);
         }

         public void mouseExited(MouseEvent e) {
            t_search.setText("이름 검색");
            t_search.setFocusable(false);
         }
      });

      // 검색창과 리스너 연결
      t_search.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_ENTER) {
               t_search.setText("이름 검색");
               t_search.setFocusable(false);
               JOptionPane.showMessageDialog(AddChat.this, "검색");
            }
         }
      });

      // 확인 버튼 눌렀을 때 새 채팅방 생성
      bt_add.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(AddChat.this, "채팅 생성");
            dispose();
            send();
         }
      });

      // 취소 버튼과 리스너 연결
      bt_cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();
         }
      });

      setSize(600, 500);
      setResizable(false);
      setUndecorated(true);
      setVisible(true);
   }

   // 친구목록에 붙을 패널을 생성하는 함수
   public void getFriend(int code, String name) {
      JPanel p_friend = new JPanel(); // 친구 한명의 전체 패널
      JPanel p_content = new JPanel(); // 프사와 이름을 붙일 패널
      JPanel p_check = new JPanel(); // 체크박스를 붙일 패널

      JLabel lb_img = new JLabel();
      JLabel lb_name = new JLabel(name);
      lb_name.setFont(font);

      Image img = imageLoad.getImage("dog.jpg");
      img = img.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
      BufferedImage circleBuffer = new BufferedImage(35, 35, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = circleBuffer.createGraphics();
      g2.setComposite(AlphaComposite.Src);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.fill(new RoundRectangle2D.Float(0, 0, 35, 35, 25, 25));
      g2.setComposite(AlphaComposite.SrcAtop);
      g2.drawImage(img, 0, 0, 35, 35, null);
      g2.dispose();
      lb_img.setIcon(new ImageIcon(circleBuffer));
      Checkbox check = new Checkbox();

      // 레이아웃 설정
      p_friend.setLayout(new BorderLayout());
      p_content.setLayout(new BorderLayout());

      // 사이즈 조절
      p_friend.setPreferredSize(new Dimension(330, 40));
      p_content.setPreferredSize(new Dimension(300, 40));
      p_content.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
      lb_name.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
      p_check.setPreferredSize(new Dimension(30, 40));

      // 배경색 설정
      p_friend.setBackground(Color.WHITE);
      p_friend.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_content.setBackground(Color.WHITE);
      p_check.setBackground(Color.WHITE);

      // 부착
      p_content.add(lb_img, BorderLayout.WEST);
      p_content.add(lb_name);
      p_check.add(check);
      p_friend.add(p_content, BorderLayout.WEST);
      p_friend.add(p_check, BorderLayout.EAST);
      p_friendList.add(p_friend);

      // 체크박스 설정시 p_addList에 패널 부착, 해제시 부착한 패널 제거
      check.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent e) {
            if (check.getState()) {
               addFriend(code, name, check);
            } else {
               removeFriend(code);
            }
         }
      });

      // 친구목록에 마우스를 올리면 패널에 색이 칠해지고, 내리면 다시 돌아오게 설정
      p_friend.addMouseListener(new MouseAdapter() {
         public void mouseEntered(MouseEvent e) {
            Color c = mainFrame.getColor();
            p_friend.setBackground(c);
            p_content.setBackground(c);
            p_check.setBackground(c);
            check.setBackground(c);
         }

         public void mouseExited(MouseEvent e) {
            p_friend.setBackground(Color.WHITE);
            p_content.setBackground(Color.WHITE);
            p_check.setBackground(Color.WHITE);
            check.setBackground(Color.WHITE);
         }
      });
   }

   // 대화상대로 추가하는 친구목록에 붙을 패널을 생성하는 함수
   public void addFriend(int code, String name, Checkbox check) {
      JPanel p_addfriend = new JPanel(); // 친구 한명의 전체 패널
      JPanel p_content = new JPanel(); // 프사와 이름을 붙일 패널
      JPanel p_x = new JPanel(); // 'x' 표시를 붙일 패널
      JPanel p_empty; // addList를 채울 빈 패널

      JLabel lb_img = new JLabel();
      ImageLoad imageLoad = new ImageLoad();
      Image img = imageLoad.getImage("dog.jpg");
      img = img.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
      BufferedImage circleBuffer = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = circleBuffer.createGraphics();
      g2.setComposite(AlphaComposite.Src);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.fill(new RoundRectangle2D.Float(0, 0, 25, 25, 20, 20));
      g2.setComposite(AlphaComposite.SrcAtop);
      g2.drawImage(img, 0, 0, 25, 25, null);
      g2.dispose();
      lb_img.setIcon(new ImageIcon(circleBuffer));

      JLabel lb_name = new JLabel(name);
      lb_name.setFont(font);
      lb_name.setForeground(Color.WHITE);
      JLabel lb_x = new JLabel("x");
      lb_x.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 18));
      lb_x.setForeground(Color.WHITE);

      addCount++;

      // 레이아웃 설정
      p_addfriend.setLayout(new BorderLayout());
      p_content.setLayout(new BorderLayout());
      p_x.setLayout(new BorderLayout());

      // 사이즈 조절
      p_addfriend.setPreferredSize(new Dimension(210, 30));
      p_content.setPreferredSize(new Dimension(180, 30));
      p_content.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
      lb_name.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
      p_x.setPreferredSize(new Dimension(30, 30));
      lb_x.setBorder(BorderFactory.createEmptyBorder(0, 5, 8, 0));

      // 배경색 설정
      p_addfriend.setBackground(mainFrame.getColor());
      p_addfriend.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      p_content.setBackground(mainFrame.getColor());
      p_x.setBackground(mainFrame.getColor());

      // 부착
      p_content.add(lb_img, BorderLayout.WEST);
      p_content.add(lb_name);
      p_x.add(lb_x);
      p_addfriend.add(p_content, BorderLayout.WEST);
      p_addfriend.add(p_x, BorderLayout.EAST);

      // 대화상대를 추가하면 안내문구가 안보이게 설정
      if (addCount == 1) {
         p_addList.remove(lb_addList);
         p_addList.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      }

      // addList에 먼저 빈 패널을 담는다
      for (int i = 0; i < friendList.size(); i++) {
         addList.add(p_empty = new JPanel());
      }
      addList.set(code, p_addfriend);
      p_addList.add(p_addfriend);
      p_addList.updateUI();

      // 'x' 클릭시 추가한 패널을 다시 지운다
      lb_x.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            addCount--;
            p_addList.remove(p_addfriend);
            p_addList.updateUI();
            check.setState(false);
         }
      });
   }

   // 대화상대로 추가한 친구패널을 제거하는 함수
   public void removeFriend(int code) {
      JPanel p_empty; // addList를 다시 채울 빈 패널

      addCount--;

      // 추가한 친구가 없으면 안내문구가 보이도록 설정
      if (addCount == 0) {
         p_addList.add(lb_addList);
         p_addList.setBorder(BorderFactory.createEmptyBorder(180, 15, 0, 0));
      }

      p_addList.remove(addList.get(code));
      p_addList.updateUI();
      addList.set(code, p_empty = new JPanel());
   }

   // 서버로 보낼 새 채팅방 생성에 필요한 정보를 담는 함수
   public void send() {
      List addFriend = new List();
      addFriend.add("오혜린");
      addFriend.add("김민호");
      addFriend.add("이주영");

      // 채팅방 이름 설정
      String chat_name = "";
      for (int i = 0; i < addFriend.getItemCount(); i++) {
         if (i == addFriend.getItemCount() - 1) {
            chat_name += addFriend.getItem(i);
         } else {
            chat_name += addFriend.getItem(i) + ",";
         }
      }

      // 채팅방 타입 설정
      String chat_type;
      if (addFriend.getItemCount() == 1) {
         chat_type = "개인";
      } else {
         chat_type = "그룹";
      }

      for (int i = 0; i < addFriend.getItemCount(); i++) {
         JSONObject obj = new JSONObject();
         obj.put("Chatmember", addFriend.getItem(i));
         obj.put("ChatName", chat_name);
         obj.put("ChatType", chat_type);
         array.add(obj);
      }
   }
}