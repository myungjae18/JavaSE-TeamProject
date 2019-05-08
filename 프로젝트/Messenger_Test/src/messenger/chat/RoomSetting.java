package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class RoomSetting extends JFrame{
   JPanel p_north, p_center, p_south;
   JPanel p_north_west, p_north_east, p_north_east_north, p_north_east_south;
   JPanel p_center_west_north, p_center_west_center, p_center_west_south;
   JPanel p_center_west, p_center_east;
   JPanel p_colorSelect;
   JLabel la_chatTitle, la_background, la_backgroundChoose, la_profile;
   JButton bt_chooseColor, bt_chooseImg, bt_confirm, bt_cancel;
   JTextField tf_chatTitle;
   ImageIcon profileThumb;
   BackgroundColorList backgroundColorList;
   Rectangle dm;
   ChatMain chatMain;
   Image profile_img;
   Image img, img2;
   String profileImgPath;
   ImageLoad imageLoad;
   JFileChooser imgChooser;
   ImageIcon previewIcon;
   Canvas previewCanvas;
//   Canvas mainCanvas;
   boolean backgroundFlag = false;
   String regist_path;
   MainFrame mainFrame;
   
   public RoomSetting(MainFrame mainFrame, ChatMain chatMain) {
      this.chatMain = chatMain;
      imgChooser = new JFileChooser("C:\\Users\\HAKSUNNAM\\Desktop\\etc\\옮길거\\사진");
      imageLoad = new ImageLoad();
      img = imageLoad.getImage("preview.png");
      previewCanvas = new Canvas() {
         public void paint(Graphics g) {
           //g.setColor(Color.WHITE);
           //g.fillRect(0, 0, 70, 100);
            g.drawImage(img, 0, 0, 70, 100, this);
             g.drawImage(img, 0, 0, 70, 100, this);
         }
      };   

      p_north = new JPanel();
      p_north_west = new JPanel();
      p_north_east = new JPanel();
      p_north_east_north = new JPanel();
      p_north_east_south = new JPanel();
      p_center = new JPanel();
      p_south = new JPanel();
      p_center_west = new JPanel();
      p_center_east = new JPanel();
      p_center_west_north = new JPanel();
      p_center_west_center = new JPanel();
      p_center_west_south = new JPanel();
      p_colorSelect = new JPanel();
      la_chatTitle = new JLabel("채팅방 이름");
      la_chatTitle.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 15));
      tf_chatTitle = new JTextField(15);
      la_background = new JLabel("채팅방 배경화면");
      la_background.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 15));
      la_backgroundChoose = new JLabel("채팅창 배경을 설정합니다");
      la_backgroundChoose.setFont(new Font(mainFrame.getFontString(), Font.PLAIN, 13));
      bt_chooseColor = new JButton("색 선택");
      bt_chooseColor.setBackground(Color.WHITE);
      bt_chooseColor.setFont(new Font(mainFrame.getFontString(), Font.PLAIN, 12));
      bt_chooseImg = new JButton("사진 선택");
      bt_chooseImg.setBackground(Color.WHITE);
      bt_chooseImg.setFont(new Font(mainFrame.getFontString(), Font.PLAIN, 12));
      bt_confirm = new JButton("확인");
      bt_confirm.setBackground(Color.YELLOW);
      bt_confirm.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 12));
      bt_cancel = new JButton("취소");
      bt_cancel.setBackground(Color.WHITE);
      bt_cancel.setFont(new Font(mainFrame.getFontString(), Font.BOLD, 12));
      
      previewCanvas.setPreferredSize(new Dimension(70, 100));
      p_north.setPreferredSize(new Dimension(300, 100));
      p_south.setPreferredSize(new Dimension(300, 50));
      p_center.setPreferredSize(new Dimension(300, 70));
      p_north_west.setPreferredSize(new Dimension(100, 100));
      p_north_east.setPreferredSize(new Dimension(200, 100));
      p_north_east_north.setPreferredSize(new Dimension(200, 50));
      //p_north_east_north.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
      p_north_east_south.setPreferredSize(new Dimension(200, 50));
      p_center_west.setPreferredSize(new Dimension(200, 170));
      p_center_east.setPreferredSize(new Dimension(100, 120));
      p_center_west_north.setPreferredSize(new Dimension(170, 40));
      p_center_west_center.setPreferredSize(new Dimension(170, 25));
      p_center_west_south.setPreferredSize(new Dimension(170, 45));
      p_colorSelect.setPreferredSize(new Dimension(70, 100));
      p_colorSelect.setBackground(Color.WHITE);
      bt_chooseColor.setPreferredSize(new Dimension(85,30));
      bt_chooseImg.setPreferredSize(new Dimension(90,30));
      p_north.setBackground(mainFrame.getColor());
      p_center.setBackground(mainFrame.getColor());
      p_south.setBackground(mainFrame.getColor());
      p_north_east_north.setBackground(mainFrame.getColor());
      p_north_east_south.setBackground(mainFrame.getColor());
      p_north_west.setBackground(mainFrame.getColor());
      p_center_west.setBackground(mainFrame.getColor());
      p_center_east.setBackground(mainFrame.getColor());
      p_center_west_north.setBackground(mainFrame.getColor());
      p_center_west_center.setBackground(mainFrame.getColor());
      p_center_west_south.setBackground(mainFrame.getColor());
      p_north.setBorder(BorderFactory.createLineBorder(new Color(75,75,75)));
      p_center.setBorder(BorderFactory.createLineBorder(new Color(75,75,75)));
      p_center_west_south.setBorder(BorderFactory.createEmptyBorder(0, 8, 20, 0));
      
      p_south.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_north_east_north.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
      p_north_east_south.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_center_west_north.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_center_west_center.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
      p_center_east.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, 0));
      p_north_west.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
      p_north_east_north.setBorder(BorderFactory.createEmptyBorder(10, 50, 0, 0));
      p_north.setLayout(new BorderLayout());
      
      p_north_west.setLayout(new BorderLayout());
      p_north_east.setLayout(new BorderLayout());
      p_north_east_north.setLayout(new BorderLayout());
      p_center.setLayout(new BorderLayout());
      p_center_west.setLayout(new BorderLayout());
      
      p_north.add(p_north_east);
      p_north.add(p_north_west, BorderLayout.WEST);
      

      profile_img = imageLoad.getImage(chatMain.getImgName()).getScaledInstance(80, 80, Image.SCALE_SMOOTH);
      profileThumb = new ImageIcon(profile_img);
      la_profile = new JLabel(profileThumb, JLabel.CENTER);
      la_profile.setPreferredSize(new Dimension(80,80));
      p_north_west.add(la_profile);
      p_north_east_north.add(la_chatTitle);
      p_north_east_south.add(tf_chatTitle);
      p_north_east.add(p_north_east_north);
      p_north_east.add(p_north_east_south, BorderLayout.SOUTH);
      p_center.add(p_center_west, BorderLayout.WEST);
      p_center.add(p_center_east, BorderLayout.EAST);
      p_center_west.add(p_center_west_north, BorderLayout.NORTH);
      p_center_west.add(p_center_west_center);
      p_center_west.add(p_center_west_south, BorderLayout.SOUTH);
      p_center_west_north.add(la_background);
      p_center_west_center.add(la_backgroundChoose);
      p_center_west_south.add(bt_chooseColor);
      p_center_west_south.add(bt_chooseImg);
      p_center_east.add(previewCanvas, BorderLayout.CENTER);
      
      p_south.add(bt_confirm);
      p_south.add(bt_cancel);
      
      backgroundColorList = new BackgroundColorList(this, chatMain);
      
      bt_chooseColor.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dm = getBounds();
            backgroundColorList.setLocation(dm.x + 295, dm.y);
            backgroundColorList.setVisible(true);
         }
      });   
      bt_chooseImg.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            chooseImg(previewCanvas);
         }
      });

      bt_confirm.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String title = tf_chatTitle.getText();
            chatMain.setTitle(title);
            if(backgroundColorList.flag) {
               p_colorSelect.setBackground(backgroundColorList.c);
               chatMain.p_north.setBackground(backgroundColorList.c);
            } else if(backgroundFlag) {
//               setAreaBackground(mainCanvas);
               chatMain.area.repaint();
            } else {
               backgroundColorList.c = chatMain.getColor();
            }
            dispose();
            backgroundColorList.dispose();
         }
      });
      bt_cancel.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            backgroundFlag = false;
            Color c = chatMain.getColor();
            p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            dispose();
            backgroundColorList.dispose();
         }
      });
   
      add(p_north, BorderLayout.NORTH);
      add(p_center, BorderLayout.CENTER);
      add(p_south, BorderLayout.SOUTH);

      setTitle("채팅방 설정");
      setSize(300, 300);
      setVisible(false);
      setResizable(false);   
   }
   public void chooseImg(Canvas can) {
      int result = imgChooser.showOpenDialog(this);
      if(result == JFileChooser.APPROVE_OPTION) {
         File file = imgChooser.getSelectedFile();
         regist_path = file.getAbsolutePath();
         previewIcon = new ImageIcon(regist_path);
         img = previewIcon.getImage();
         img2 = img.getScaledInstance(350, 500, Image.SCALE_SMOOTH);
         can.repaint();
         backgroundFlag = true;
      }
   }
}