
package messenger.regist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Profile extends JFrame{
   JPanel p_con;
   JPanel p_north, p_south;
   JPanel p_img, p_txt, p_bt;
   JTextArea txt;
   JFileChooser chooser;
   JButton bt_choose, bt_accept, bt_exit;
   Image img;
   String fileName;
   
   public Profile() {
      super("파일 찾기");
      // -------------------------------------
      p_con=new JPanel();
      // -------------------------------------
      p_north=new JPanel();
      p_south=new JPanel();
      // -------------------------------------
      p_img=new JPanel() {
         public void paint(Graphics g) {
            g.drawImage(img, 0, 0, 100, 100, this);
         }
      };
      p_txt=new JPanel();
      p_bt=new JPanel();
      // -------------------------------------
      txt=new JTextArea();      
      bt_choose=new JButton("파일 찾기");
      bt_accept=new JButton("확인");
      bt_exit=new JButton("취소");
      
      txt.setPreferredSize(new Dimension(300, 100));
      txt.setText("자신의 프로필 사진을 지정해주세요\n(확장자명이 jpeg, jpg, png인 파일만 지원합니다");
      txt.setEditable(false);
            
      p_img.setPreferredSize(new Dimension(100, 100));
      p_img.setLayout(new BorderLayout());
      p_img.setBackground(Color.BLACK);
      // -------------------------------------
      p_txt.setPreferredSize(new Dimension(300, 100));
      p_txt.setLayout(new BorderLayout());
      p_txt.add(txt);
      // -------------------------------------
      p_north.setPreferredSize(new Dimension(400, 100));
      p_north.setLayout(new BorderLayout());
      p_north.add(p_img, BorderLayout.WEST);
      p_north.add(p_txt);
      // -------------------------------------
      p_south.setPreferredSize(new Dimension(400, 50));
      p_south.setBackground(Color.ORANGE);
      p_south.add(bt_choose);
      p_south.add(bt_accept);
      p_south.add(bt_exit);
      // -------------------------------------
      p_con.setPreferredSize(new Dimension(400, 180));
      p_con.setLayout(new BorderLayout());
      p_con.add(p_north, BorderLayout.NORTH);
      p_con.add(p_south);
      
      add(p_con);
      
      bt_choose.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            chooser=new JFileChooser();
            chooser.showOpenDialog(p_con);
            File file=chooser.getSelectedFile();
            fileName=file.toString();
            System.out.println(fileName);
            ImageIcon icon=new ImageIcon(fileName);
            img=icon.getImage();
            img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            p_img.repaint();
            //sendImage(img);
         }
      });
      
      bt_accept.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //프로필 사진을 전송
            dispose();
         }
      });
      
      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            dispose();
         }
      });
            
      setVisible(true);
      setSize(400, 180);
   }
   /*public void sendImage(Image img) {
      img
   }*/
}