package messenger.chat;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import messenger.utils.ImageLoad;

public class EmoticonList1 extends JPanel{
   ImageIcon emoticon_icon;
   Image emoticon_icon_img;
   EmoticonMain emoticonMain;
   JPanel p_north, p_center, p_south;
   JPanel p_north_east, p_north_west, p_center_east, p_center_west, p_south_east, p_south_west;
   ImageIcon[] iconList = new ImageIcon[12];
   ImageIcon[] iconList2 = new ImageIcon[12];
   JLabel la_north1, la_north2, la_north3, la_north4;
   JLabel la_center1, la_center2, la_center3, la_center4;
   JLabel la_south1, la_south2, la_south3, la_south4;
   Image emo_img, emo_img2;
   ImageIcon emoticon, emoticon2;
   ChatMain chatMain;
   ImageLoad imageLoad;
   
   public EmoticonList1(ChatMain chatMain) {
      this.chatMain = chatMain;
      imageLoad = new ImageLoad();
      p_north = new JPanel();
      p_center = new JPanel();
      p_south = new JPanel();
      p_north_east = new JPanel();
      p_north_west = new JPanel();
      p_center_east = new JPanel();
      p_center_west = new JPanel();
      p_south_east = new JPanel();
      p_south_west = new JPanel();
      
      for(int i = 0; i < iconList.length; i += 1) {
         emo_img = imageLoad.getImage("emoji/emot_" + (i+1) + ".png");
         emo_img = emo_img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
         emo_img2 = emo_img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
         emoticon = new ImageIcon(emo_img);
         emoticon2 = new ImageIcon(emo_img2);
         iconList[i] = emoticon;
         iconList2[i] = emoticon2;
      }
         
      la_north1 = new JLabel(iconList[0]);
      la_north2 = new JLabel(iconList[1]);
      la_north3 = new JLabel(iconList[2]);
      la_north4 = new JLabel(iconList[3]);
      la_center1 = new JLabel(iconList[4]);
      la_center2 = new JLabel(iconList[5]);
      la_center3 = new JLabel(iconList[6]);
      la_center4 = new JLabel(iconList[7]);
      la_south1 = new JLabel(iconList[8]);
      la_south2 = new JLabel(iconList[9]);
      la_south3 = new JLabel(iconList[10]);
      la_south4 = new JLabel(iconList[11]);
      
      
      p_north.setLayout(new BorderLayout());
      p_center.setLayout(new BorderLayout());
      p_south.setLayout(new BorderLayout());
      p_north_east.setLayout(new BorderLayout());
      p_north_west.setLayout(new BorderLayout());
      p_center_east.setLayout(new BorderLayout());
      p_center_west.setLayout(new BorderLayout());
      p_south_east.setLayout(new BorderLayout());
      p_south_west.setLayout(new BorderLayout());
      p_north.setPreferredSize(new Dimension(300, 50));
      p_center.setPreferredSize(new Dimension(300, 50));
      p_south.setPreferredSize(new Dimension(300, 50));
      p_north_east.setPreferredSize(new Dimension(140, 50));
      p_north_west.setPreferredSize(new Dimension(140, 50));
      p_center_east.setPreferredSize(new Dimension(140, 50));
      p_center_west.setPreferredSize(new Dimension(140, 50));
      p_south_east.setPreferredSize(new Dimension(140, 50));
      p_south_west.setPreferredSize(new Dimension(140, 50));
      p_north_west.add(la_north1);
      p_north_west.add(la_north2, BorderLayout.EAST);
      p_north_east.add(la_north3, BorderLayout.WEST);
      p_north_east.add(la_north4);
      p_center_west.add(la_center1);
      p_center_west.add(la_center2, BorderLayout.EAST);
      p_center_east.add(la_center3, BorderLayout.WEST);
      p_center_east.add(la_center4);
      p_south_west.add(la_south1);
      p_south_west.add(la_south2, BorderLayout.EAST);
      p_south_east.add(la_south3, BorderLayout.WEST);
      p_south_east.add(la_south4);
      
      p_north.add(p_north_west, BorderLayout.WEST);
      p_north.add(p_north_east, BorderLayout.EAST);
      p_center.add(p_center_west, BorderLayout.WEST);
      p_center.add(p_center_east, BorderLayout.EAST);
      p_south.add(p_south_west, BorderLayout.WEST);
      p_south.add(p_south_east, BorderLayout.EAST);
   

      la_north1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[0]));
         }
      });
      la_north2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[1]));
         }
      });
      la_north3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[2]));
         }
      });
      la_north4.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[3]));
         }
      });
      la_center1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[4]));
         }
      });
      la_center2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[5]));
         }
      });
      la_center3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[6]));
         }
      });
      la_center4.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[7]));
         }
      });
      la_south1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[8]));
         }
      });
      la_south2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[9]));
         }
      });
      la_south3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[10]));
         }
      });
      la_south4.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            chatMain.area_input.insertComponent(new JLabel(iconList2[11]));
         }
      });
      
      add(p_north);
      add(p_center);
      add(p_south);
//      setBackground(Color.YELLOW);
      setPreferredSize(new Dimension(300, 250));
      setVisible(true);
   }
}