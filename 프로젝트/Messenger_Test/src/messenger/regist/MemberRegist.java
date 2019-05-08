package messenger.regist;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messenger.client.ClientThread;
import messenger.mainframe.MainFrame;

public class MemberRegist extends JFrame {
   JPanel p_container; //전체를 감싸는 패널
   JPanel p_north, p_center, p_south; //전체 패널을 크게 3개로 구분
   JPanel p_emptyLeft, p_emptyRight, p_eRNorth, p_eRCenter; //빈 공간을 채울 패널
   JPanel p_label, p_txt; //p_center안의 기능에 따라 구분
   JLabel la_name, la_id, la_pw, la_nick, la_birth, la_phone, la_email;
   JTextField t_name, t_id, t_pw, t_nick, t_birth, t_phone, t_email;
   JButton bt_checkId, bt_checkNick;
   JButton bt_regist;
   JButton bt_profile;
   ImageIcon icon;
   Image img;
   
   JSONParser parser;
   JSONArray array;
   Object obj = null;
   JSONObject jsonObj;
   String type;
   
   ClientThread ct;
   Profile profile;
   
   String order;
   String name, id, pw, nick, birth, phone, email; //각 텍스트창의 내용
   int count=0; //아이디 중복확인 여부를 확인할 변수
   
   //LoginMain loginMain;
   
   public MemberRegist(ClientThread ct) {
      super("회원 가입");
      this.ct=ct;
      icon=new ImageIcon("E:/java_developer/javaSE/Messenger/res/image_rion.jpg");
      img=icon.getImage();
      img.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
      /*<인스턴스 호출>*/
      p_container=new JPanel();
      p_north=new JPanel() {
         public void paint(Graphics g) {
            g.drawImage(img, 50, 50, this);
         }
      };
      p_center=new JPanel();
      p_south=new JPanel();
      //--------------------------------------
      p_label=new JPanel();
      p_txt=new JPanel();
      //--------------------------------------
      p_emptyLeft=new JPanel();
      p_emptyRight=new JPanel();
      p_eRNorth=new JPanel();
      p_eRCenter=new JPanel();
      //--------------------------------------
      la_name=new JLabel("이름");
      la_id=new JLabel("ID");
      la_pw=new JLabel("password");
      la_nick=new JLabel("nickName");
      la_birth=new JLabel("생일");
      la_phone=new JLabel("휴대폰");
      la_email=new JLabel("Email");
      //--------------------------------------
      t_name=new JTextField();
      t_id=new JTextField();
      t_pw=new JTextField();
      t_nick=new JTextField();
      t_birth=new JTextField();
      t_phone=new JTextField();
      t_email=new JTextField();
      //--------------------------------------
      bt_checkId=new JButton("중복 확인");
      bt_checkNick=new JButton("중복 확인");
      bt_regist=new JButton("회원 가입");
      bt_profile=new JButton("프로필 사진");
            
      /*<label 관련 설정>*/
      Dimension d_la=new Dimension(70, 30);
      la_name.setPreferredSize(d_la);
      la_id.setPreferredSize(d_la);
      la_pw.setPreferredSize(d_la);
      la_nick.setPreferredSize(d_la);
      la_birth.setPreferredSize(d_la);
      la_phone.setPreferredSize(d_la);
      la_email.setPreferredSize(d_la);
      //bt_check.setPreferredSize(new Dimension(100, 30));
      
      /*<textField 관련 설정>*/
      Dimension d_txt=new Dimension(200, 30);
      t_name.setPreferredSize(d_txt);
      t_id.setPreferredSize(d_txt);
      t_pw.setPreferredSize(d_txt);
      t_nick.setPreferredSize(d_txt);
      t_birth.setPreferredSize(d_txt);
      t_phone.setPreferredSize(d_txt);
      t_email.setPreferredSize(d_txt);
      
      /*<내부 패널 관련 설정>*/
      p_emptyLeft.setBounds(0, 700, 50, 800);
      //p_emptyLeft.setPreferredSize(new Dimension(50, 800));
      p_emptyLeft.setBackground(Color.ORANGE);
      //--------------------------------------
      p_north.setPreferredSize(new Dimension(450, 400));
      p_north.setBackground(Color.ORANGE);
      //--------------------------------------
      p_center.setBounds(50, 500, 450, 350);
      //p_center.setPreferredSize(new Dimension(450, 300));
      p_center.setLayout(new BorderLayout());
      p_center.add(p_label, BorderLayout.WEST);
      p_center.add(p_txt);
      p_center.add(p_emptyRight, BorderLayout.EAST);
      //--------------------------------------
      p_label.setPreferredSize(new Dimension(100, 350));
      p_label.setBackground(Color.ORANGE);
      p_label.add(la_name);
      p_label.add(la_id);
      p_label.add(la_pw);
      p_label.add(la_nick);
      p_label.add(la_birth);
      p_label.add(la_phone);
      p_label.add(la_email);
      //--------------------------------------
      p_txt.setPreferredSize(new Dimension(200, 350));
      p_txt.setBackground(Color.ORANGE);
      p_txt.add(t_name);
      p_txt.add(t_id);
      p_txt.add(t_pw);
      p_txt.add(t_nick);
      p_txt.add(t_birth);
      p_txt.add(t_phone);
      p_txt.add(t_email);
      p_txt.add(bt_profile);
      bt_profile.setPreferredSize(new Dimension(200, 30));
      //--------------------------------------
      p_emptyRight.setPreferredSize(new Dimension(150, 350));
      p_emptyRight.setBackground(Color.ORANGE);
      p_emptyRight.add(p_eRNorth);
      p_emptyRight.add(bt_checkId);
      p_emptyRight.add(p_eRCenter);
      p_emptyRight.add(bt_checkNick);
      bt_checkId.setBounds(450, 635, 80, 30);
      bt_checkNick.setPreferredSize(new Dimension(90, 30));
      //--------------------------------------
      p_eRNorth.setPreferredSize(new Dimension(150, 30));
      p_eRNorth.setBackground(Color.ORANGE);
      //--------------------------------------
      p_eRCenter.setPreferredSize(new Dimension(150, 30));
      p_eRCenter.setBackground(Color.ORANGE);
      //--------------------------------------
      
      p_south.setPreferredSize(new Dimension(450, 50));
      p_south.setBackground(Color.ORANGE);
      p_south.add(bt_regist);
            
      /*<p_container 관련 설정>*/
      p_container.setPreferredSize(new Dimension(450, 800));
      p_container.setBackground(Color.ORANGE);
      p_container.setLayout(new BorderLayout());
      p_container.add(p_north, BorderLayout.NORTH);
      p_container.add(p_center);
      p_container.add(p_south, BorderLayout.SOUTH);
      
      /*<버튼에 이벤트 구현>*/
//      bt_checkId.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            checkId();         
//         }
//      });
//      
//      bt_checkNick.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            checkNick();
//         }
//      });
      
      bt_regist.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            regist();   
         }
      });
      
      bt_profile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            profile=new Profile();
         }
      });
            
      /*<Frame 관련 설정>*/
      setLayout(new BorderLayout());
      add(p_emptyLeft);
      add(p_container, BorderLayout.EAST);
      
      setBounds(200, 200, 500, 800);
      setResizable(false);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   public void parseResult(String result) {
      parser = new JSONParser();
      array = new JSONArray();
      
      try {
         obj = parser.parse(result);
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      jsonObj = (JSONObject) obj;
      type = (String) jsonObj.get("Type");
   }
   
   /*public void checkId() {
      if (type.equals("loginresult")) {
         if(id == (String) jsonObj.get("Id")) {
            JOptionPane.showMessageDialog(this, "이미 등록된 아이디입니다. 재입력 후 다시 시도해주세요");
            return;
         }else {
            JOptionPane.showMessageDialog(this, "사용 가능한 아이디입니다");
            count++;
         }
      }
   }*/
   
   /*public void checkNick() {
      if (type.equals("loginresult")) {
         if(nick == (String) jsonObj.get("Nick")) {
            JOptionPane.showMessageDialog(this, "이미 등록된 닉네임입니다. 재입력 후 다시 시도해주세요");
            return;
         }else {
            JOptionPane.showMessageDialog(this, "사용 가능한 닉네임입니다");
            count++;
         }
      }
   }*/
   public void regist() {
      name=t_name.getText();
      id=t_id.getText();
      pw=t_pw.getText();
      nick=t_nick.getText();
      birth=t_birth.getText();
      phone=t_phone.getText();
      email=t_email.getText();
      
      //아이디 및 닉네임 중복확인을 완료했는지 확인하기
      /*if(count!=2) {
         JOptionPane.showMessageDialog(this, "아이디 및 닉네임 중복 확인을 해주세요");
         return;
      }*/
      //텍스트창의 내용이 비어있는지 확인하기
      if(name.length()==0) {
         JOptionPane.showMessageDialog(this, "본인의 이름을 입력해주세요");
         return;
      }
      if(id.length()==0) {
         JOptionPane.showMessageDialog(this, "사용할 아이디를 입력해주세요");
         return;
      }
      if(pw.length()==0) {
         JOptionPane.showMessageDialog(this, "사용할 비밀번호를 입력해주세요(10자 이내)");
         return;
      }
      if(nick.length()==0) {
         JOptionPane.showMessageDialog(this, "사용할 닉네임을 입력해주세요");
         return;
      }
      if(birth.length()==0) {
         JOptionPane.showMessageDialog(this, "본인의 생일을 입력해주세요");
         return;
      }
      if(phone.length()==0) {
         JOptionPane.showMessageDialog(this, "본인의 핸드폰 번호를 입력해주세요");
         return;
      }
      if(email.length()==0) {
         JOptionPane.showMessageDialog(this, "등록할 이메일 주소를 입력해주세요");
         return;
      }
      //String imgName=profile.fileName;
      JSONObject json=new JSONObject();
      json.put("Type", "join");
      json.put("Name", name);
      json.put("Id", id);
      json.put("Pw", pw);
      json.put("Nick", nick);
      json.put("Birth", birth);
      json.put("Phone", phone);
      json.put("Email", email);
      json.put("Img", "good");
      order=json.toString();
      System.out.println(order);
      ct.send(order);
   }
}