package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import messenger.mainframe.MainFrame;
import messenger.option.AddChat;
import messenger.utils.ImageLoad;

public class ChatMain extends JFrame implements ActionListener{
   JPanel p_north, p_north_east, p_north_west, p_south, p_south_north, p_south_south, p_south_north_r
   , p_south_north_c, p_south_north_l, p_emoticon, p_addFile
   , p_addImg, p_wordSearch, p_setting, p_showEmoticon, p_profile;
   JTextPane area;
   StyledDocument doc;
   JScrollPane scroll;
   JScrollBar scrollBar;
   JLabel la_emoticon, la_addFile, la_addImg, la_wordSearch, la_setting, la_profile, la_chat;
   ArrayList chatLogs;
   JTextPane area_input;
   JScrollPane scroll2;
   JButton bt_input, bt_emoticon, bt_addFile;
   JFileChooser chooser; //Ž����
   File file;
   EmoticonMain emoticon;
   RoomSetting setting;
   WordSearch wordSearch;
   ImageIcon emoticon_icon, addFile_icon, addImg_icon, wordSearch_icon, setting_icon, profile_icon;
   Image emoticon_img, emoticon_newImg, addFile_img, addFile_newImg, addImg_img, addImg_newImg;
   Image wordSearch_img, wordSearch_newImg, setting_img, setting_newImg, profile_img;
   Rectangle dm;
   ImageLoad imageLoad;
   private Color color = Color.WHITE;
   private String profileImg = "cat.jpg"; //���⼭ ������ ������ ���� �̸��� ����
   ChatRoomProfile chatRoomProfile;
   MainFrame mainFrame;
   AddChat addChat;
   JSONArray array = new JSONArray();
//   Canvas mainCanvas;

   public ChatMain(String title, MainFrame mainFrame) {
      super(title);
      this.mainFrame = mainFrame;
      addChat = new AddChat(mainFrame);
      chatLogs = new ArrayList();
      p_north = new JPanel();
      p_north_east = new JPanel();
      p_north_west = new JPanel();
      area = new JTextPane();
//         public void paint(Graphics g1) {
////            g1.setColor(Color.RED);
////            g1.drawRect(0, 0, 200, 200);
//            g1.drawImage(setting.img2, 0, 0, 350, 500, this);
//         }
//      };
      area.setPreferredSize(new Dimension(350, 500));
      area.setOpaque(true);
      area.setEditable(false);
//      doc = area.getStyledDocument();
      scroll = new JScrollPane(area);
      scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      chooser = new JFileChooser("");
      
      p_south = new JPanel();
      p_south_north = new JPanel();
      p_south_south = new JPanel();
      p_south_north_l = new JPanel();
      p_south_north_c = new JPanel();
      p_south_north_r = new JPanel();      
      area_input = new JTextPane();
      scroll2 = new JScrollPane(area_input);
      scrollBar = scroll.getVerticalScrollBar();
      bt_input = new JButton("����");
      bt_input.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 20));
      //bt_emoticon = new JButton("�̸�Ƽ��");
      emoticon = new EmoticonMain(this);
      setting = new RoomSetting(mainFrame, this);
      wordSearch = new WordSearch(this);
      //bt_addFile = new JButton("��������");
      p_profile = new JPanel();
      p_emoticon = new JPanel();
      p_addFile = new JPanel();
      p_addImg = new JPanel();
      p_wordSearch = new JPanel();
      p_setting = new JPanel();
      p_showEmoticon = new JPanel();
      imageLoad = new ImageLoad();
      chatRoomProfile = new ChatRoomProfile(this);
      
      
      //--------------------------------------------------------------------------------------��� ������ �����
      profile_img = imageLoad.getImage(profileImg).getScaledInstance(30, 30, Image.SCALE_SMOOTH);
      profile_icon = new ImageIcon(profile_img);
      
      wordSearch_img = imageLoad.getImage("search.png");
      wordSearch_img = wordSearch_img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
      wordSearch_icon = new ImageIcon(wordSearch_img);

      setting_img = imageLoad.getImage("settings.png");
      setting_img = setting_img.getScaledInstance(15, 17, Image.SCALE_SMOOTH);
      setting_icon = new ImageIcon(setting_img);
         
      //--------------------------------------------------------------------------------------�ϴ� ������ �����
      emoticon_img = imageLoad.getImage("smile.png");
      emoticon_img = emoticon_img.getScaledInstance(27, 27, Image.SCALE_SMOOTH);
      emoticon_icon = new ImageIcon(emoticon_img);
      
      addFile_img = imageLoad.getImage("folder.png");
      addFile_img = addFile_img.getScaledInstance(27, 27, Image.SCALE_SMOOTH);
      addFile_icon = new ImageIcon(addFile_img);
      
      addImg_img = imageLoad.getImage("image.png");
      addImg_img = addImg_img.getScaledInstance(27, 27, Image.SCALE_SMOOTH);
      addImg_icon = new ImageIcon(addImg_img);
      
      
      //--------------------------------------------------------------------------------------��ư ����
      la_wordSearch = new JLabel(wordSearch_icon, JLabel.CENTER);
      la_setting = new JLabel(setting_icon, JLabel.CENTER);
      la_emoticon = new JLabel(emoticon_icon, JLabel.CENTER);
      la_addFile = new JLabel(addFile_icon, JLabel.CENTER);
      la_addImg = new JLabel(addImg_icon, JLabel.CENTER);
      la_profile = new JLabel(profile_icon, JLabel.CENTER);
      
      area_input.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ENTER) {
               System.out.println("����");
               sendToServer();
               sendText();
            }
         }
      });
      
      //-------------------------------------------------------------------------------------------------���̱�
      p_north.setLayout(new BorderLayout());
      p_north_east.setLayout(new BorderLayout());
      p_south.setLayout(new BorderLayout());
      p_showEmoticon.setPreferredSize(new Dimension());   
      p_profile.add(la_profile);
//      setting.p_north_west.add(la_profile);
      p_wordSearch.add(la_wordSearch);
      p_setting.add(la_setting);
      p_emoticon.add(la_emoticon);
      p_addFile.add(la_addFile);
      p_addImg.add(la_addImg);
//      setJMenuBar(bar);
      add(scroll);
      scrollBar.setValue(scrollBar.getMaximum());
      p_south.setPreferredSize(new Dimension(350, 100));
      p_north_west.setPreferredSize(new Dimension(50, 50));
      p_north_east.setPreferredSize(new Dimension(300, 50));
      p_south_north.setLayout(new BorderLayout());
      p_south_north_l.setPreferredSize(new Dimension(0, 50));
      p_south_north_c.setPreferredSize(new Dimension(350, 50));
      p_south_north_r.setPreferredSize(new Dimension(0, 50));
      p_north.setPreferredSize(new Dimension(350, 50));
      area_input.setPreferredSize(new Dimension(230, 40));
      p_north_west.add(p_profile);
      p_north_east.add(p_wordSearch, BorderLayout.WEST);
      p_north_east.add(p_setting, BorderLayout.EAST);
      p_north.add(p_north_west, BorderLayout.WEST);
      p_north.add(p_north_east);
      p_south_north.add(p_south_north_l, BorderLayout.WEST);
      p_south_north.add(p_south_north_c);
      p_south_north.add(p_south_north_r, BorderLayout.EAST);
      p_south_north_c.add(scroll2);
      p_south_north_c.add(bt_input);
      p_south_south.add(p_emoticon);
      p_south_south.add(p_addFile);
      p_south_south.add(p_addImg);
      p_south.add(p_south_north);
      p_south.add(p_south_south, BorderLayout.SOUTH);
      add(p_north, BorderLayout.NORTH);
      add(p_south, BorderLayout.SOUTH);
      p_north_east.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
//      p_south_north_c.setBackground(Color.RED);
//      p_south_south.setBackground(Color.BLUE);   

//      area.setBackground(Color.GREEN);
      //��ư������
      la_profile.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            System.out.println("ä�ù������� ���");
            dm = getBounds();
            chatRoomProfile.setLocation(dm.x - 350, dm.y);
            chatRoomProfile.setVisible(true);
         }
      });
      la_wordSearch.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            dm = getBounds();
            wordSearch.setLocation(dm.x + 335, dm.y);
            wordSearch.setVisible(true);
         }
      });
      la_emoticon.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            dm = getBounds();
            emoticon.setLocation(dm.x - 284, dm.y + 200);
            emoticon.setVisible(true);      
            System.out.println(dm.x + "\n" + dm.y);
         }
      });
      la_addFile.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            addFile();
         }
      });
      la_addImg.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            addImg();
         }
      });
      la_setting.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            dm = getBounds();
            setting.setLocation(dm.x + 340, dm.y);
            setting.setVisible(true);
         }      
      });
      
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            dispose();
         }
      });
      setMinimumSize(new Dimension(350, 500));
      setBounds(800, 300, 350, 500);
      setVisible(true);
      
      System.out.println(getWidth());
      System.out.println(getHeight());
      //setDefaultCloseOperation(EXIT_ON_CLOSE); �̰� ���� �ٲ��� ���� �ȵ�
      
   }
   
   
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == bt_input) {
         System.out.println("����");
         sendToServer();
         sendText();
      }
   }
   public void addFile() {
      int result = chooser.showOpenDialog(this);
      if(result == JFileChooser.APPROVE_OPTION) {
         //����(������ ������ ������ ��ȯ)
         file = chooser.getSelectedFile(); //���ϼ���
         System.out.println("���� ÷���ϱ�");
      }
   }
   public void addImg() {
      int result = chooser.showOpenDialog(this);
      if(result == JFileChooser.APPROVE_OPTION) {
         file = chooser.getSelectedFile();
         String path = file.getAbsolutePath();
         ImageIcon icon = new ImageIcon(path);
         Image img = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         icon = new ImageIcon(img);
         JLabel la_icon = new JLabel(icon);
         la_icon.setPreferredSize(new Dimension(800, 50));
         area.insertComponent(la_icon);
         la_icon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
               System.out.println("�̹��� �ٿ�ε� �۾�");
            }
         });
      }

   }
//   Image �������̹��� = �����Ҿ�����.getImage();  //ImageIcon�� Image�� ��ȯ.
//
//   Image ������̹��� = �������̹���.getScaledInstance(����, ����, java.awt.Image.SCALE_SMOOTH);
//
//   ImageIcon ����Ⱦ����� = new ImageIcon(������̹���); //Image�� ImageIcon ����
//   for(int i = 0; i < chatLogs.size(); i += 1) {
//      area.insertComponent((Component) chatLogs.get(i));
//   }
   public void sendText() {
      String inputText = area_input.getText();
      la_chat = new JLabel(inputText);
      la_chat.setOpaque(true);
//      la_chat.setBackground(Color.BLUE);
      la_chat.setPreferredSize(new Dimension(800, 30));
      chatLogs.add(la_chat);
      area.insertComponent(la_chat);
      System.out.println(chatLogs.size());
      area_input.setText("");
   }
   public Color getColor() {
      return color;
   }
   
   public String getImgName() {
      return profileImg;
   }
   
   public void setColor(Color color) {
      this.color = color;
   }
   
   public void sendToServer() {
//      array = addChat.getArray();
//      System.out.println(array);
//      for(int i = 0; i < addChat.addFriend.getItemCount(); i += 1) {
//         JSONObject obj = new JSONObject();
//         array.add(i, obj.put("User_code", );
//         array.add(i, obj.put("Chat_log", chatLogs.get(i)));
//      }
//      String order = array.toJSONString();
   }
   
}