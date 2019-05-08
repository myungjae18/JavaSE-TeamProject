package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messenger.utils.ImageLoad;

public class BackgroundColorList extends JFrame{
   RoomSetting setting;
   JPanel p_north, p_center, p_south;
   JLabel la_north1, la_north2, la_north3;
   JLabel la_center1, la_center2, la_center3;
   JLabel la_south1, la_south2, la_south3;
   Image[] backgroundImg = new Image[9];
   ImageIcon[] backgroundColor = new ImageIcon[9];
   ChatMain chatMain;
   Color[] color = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE
         , Color.MAGENTA, Color.PINK, Color.WHITE, Color.LIGHT_GRAY};
   Color c;
   ImageLoad imageLoad;
   boolean flag = false;
   
   public BackgroundColorList(RoomSetting setting, ChatMain chatMain) {
      this.setting = setting;
      this.chatMain = chatMain;
      imageLoad = new ImageLoad();
      p_north = new JPanel();
      p_center = new JPanel();
      p_south = new JPanel();
      for(int i = 0; i < backgroundColor.length; i += 1) {
         backgroundImg[i] = imageLoad.getImage("Color/" + (i+1) + ".PNG");
         backgroundColor[i] = new ImageIcon(backgroundImg[i]);
      }
      la_north1 = new JLabel(backgroundColor[0]);
      la_north2 = new JLabel(backgroundColor[1]);
      la_north3 = new JLabel(backgroundColor[2]);
      la_center1 = new JLabel(backgroundColor[3]);
      la_center2 = new JLabel(backgroundColor[4]);
      la_center3 = new JLabel(backgroundColor[5]);
      la_south1 = new JLabel(backgroundColor[6]);
      la_south2 = new JLabel(backgroundColor[7]);
      la_south3 = new JLabel(backgroundColor[8]);

      //-----------------------------------------------------------------------------------------
      p_north.setLayout(new BorderLayout());
      p_center.setLayout(new BorderLayout());
      p_south.setLayout(new BorderLayout());
      p_north.setPreferredSize(new Dimension(300, 120));
      p_center.setPreferredSize(new Dimension(300, 130));
      p_south.setPreferredSize(new Dimension(300, 120));
      //--------------------------------------------------------------------------------
      la_north1.setPreferredSize(new Dimension(90, 110));
      la_north2.setPreferredSize(new Dimension(90, 110));
      la_north3.setPreferredSize(new Dimension(90, 110));
      la_center1.setPreferredSize(new Dimension(90, 110));
      la_center2.setPreferredSize(new Dimension(90, 110));
      la_center3.setPreferredSize(new Dimension(90, 110));
      la_south1.setPreferredSize(new Dimension(90, 110));
      la_south2.setPreferredSize(new Dimension(90, 110));
      la_south3.setPreferredSize(new Dimension(90, 110));
      //------------------------------------------------------------------------------------------
      p_north.add(la_north1, BorderLayout.WEST);
      p_north.add(la_north2);
      p_north.add(la_north3, BorderLayout.EAST);
      p_center.add(la_center1, BorderLayout.WEST);
      p_center.add(la_center2);
      p_center.add(la_center3, BorderLayout.EAST);
      p_south.add(la_south1, BorderLayout.WEST);
      p_south.add(la_south2);
      p_south.add(la_south3, BorderLayout.EAST);
      add(p_north, BorderLayout.NORTH);
      add(p_center);
      add(p_south, BorderLayout.SOUTH);
//      p_north.setBackground(Color.RED);
//      p_center.setBackground(Color.ORANGE);
//      p_south.setBackground(Color.YELLOW);
      
      //-----------------------------------------------------------------------------------------
      la_north1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[0];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
//            chatMain.wordSearch_icon.
            flag = true;
         }
      });
      la_north2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[1];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_north3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[2];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_center1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[3];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_center2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[4];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_center3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[5];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_south1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[6];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_south2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[7];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      la_south3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            c = color[8];
            setting.p_colorSelect.setBackground(c);
            chatMain.p_north.setBackground(c);
            chatMain.area.setBackground(c);
            flag = true;
         }
      });
      setResizable(false);
      setTitle("채팅창 배경색 변경");
      setSize(300, 405);
      setVisible(false);
   }
}