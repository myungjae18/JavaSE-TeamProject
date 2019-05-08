package messenger.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import messenger.client.ClientThread;
import messenger.mainframe.MainFrame;
import messenger.regist.MemberRegist;
import messenger.utils.ImageLoad;

//�α��� ȭ���� �����Ѵ�
public class MemberLogin extends JPanel {
   JPanel p_north, p_center, p_idArea, p_pwArea, p_south; // ���� ������ ���� �г�
   JButton bt_accept, bt_regist; // �α��� Ȯ�� ��ư�� ȸ�� ���� ��ư
   public JTextField n_id, n_pw; // ���̵�� ��й�ȣ�� ǥ���� �ؽ�Ʈ
   JTextField t_id; //���̵� �Է� â
   JPasswordField t_pw; //��й�ȣ �Է� â
   // -------------------------------------
   LoginMain loginMain;
   Image image;
   ImageLoad imageLoad;

   JSONObject obj = new JSONObject();

   int port = 8888;
   Socket client;// ��ȭ�� ����
   ClientThread ct;

   String order;

   public String MyCode;
   public String MyId;
   public String MyName;
   public String MyBirth;
   public String MyPhone;
   public String MyEmail;
   public String MyNick;
   
   MainFrame mainFrame;// ���� �����ӿ� result�� ����������
   int count=0;

   public MemberLogin() {
      setLayout(new BorderLayout());
      /* <�̹��� ���� �۾�> */
//      imageLoad = new ImageLoad();
//      image = imageLoad.getImage("ball.png");
//      image = image.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

      /* <�ν��Ͻ� ȣ��> */

      p_north = new JPanel() {
         public void paint(Graphics g) {
            g.drawImage(image, 0, 0, this);
         }
      };
      p_center = new JPanel();
      p_south = new JPanel();
      p_idArea = new JPanel();
      p_pwArea = new JPanel();
      // -------------------------------------
      n_id = new JTextField(2) {
         public void setBorder(Border border) {
         }
      };
      n_pw = new JTextField(2) {
         public void setBorder(Border border) {
         }
      };
      // -------------------------------------
      t_id = new JTextField(15);
      t_pw = new JPasswordField(15);
      // -------------------------------------
      bt_accept = new JButton("Ȯ��");
      bt_regist = new JButton("����");

      /* <�ؽ�Ʈ ���� ����> */
      n_id.setFont(new Font("", Font.BOLD, 20));
      n_id.setEditable(false);
      n_id.setBackground(Color.YELLOW);
      n_id.setSize(30, 30);
      n_id.setText("id");
      // -------------------------------------
      n_pw.setFont(new Font("", Font.BOLD, 20));
      n_pw.setEditable(false);
      n_pw.setBackground(Color.YELLOW);
      n_pw.setSize(30, 30);
      n_pw.setText("pw");
      // -------------------------------------
      t_id.setFont(new Font(null, Font.PLAIN, 20));
      // -------------------------------------
      t_pw.setFont(new Font(null, Font.PLAIN, 20));

      /* <���� �гε� ���� ����> */
      p_north.setPreferredSize(new Dimension(500, 500));
      // -------------------------------------
      p_center.setPreferredSize(new Dimension(500, 190));
      p_center.setBackground(Color.YELLOW);
      p_center.add(p_idArea);
      p_center.add(p_pwArea, BorderLayout.SOUTH);
      // -------------------------------------
      p_idArea.setPreferredSize(new Dimension(500, 40));
      p_idArea.setBackground(Color.YELLOW);
      p_idArea.add(n_id);
      p_idArea.add(t_id);
      // -------------------------------------
      p_pwArea.setPreferredSize(new Dimension(500, 150));
      p_pwArea.setBackground(Color.YELLOW);
      p_pwArea.add(n_pw);
      p_pwArea.add(t_pw);
      // -------------------------------------
      p_south.setPreferredSize(new Dimension(500, 50));
      p_south.setBackground(Color.YELLOW);
      p_south.add(bt_accept);
      p_south.add(bt_regist);

      /* <�α��� Ȯ�� ��ư ��� ����> */
      bt_accept.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String order = accept();
            ct.send(order);
            t_id.setText("");
            t_pw.setText("");

         }
      });

      /* ȸ�� ���� ��ư�� ��� ���� */
      bt_regist.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MemberRegist regist = new MemberRegist(ct);
         }
      });

      /* <Login �г� ���� ����> */
      setLayout(new BorderLayout());
      // -------------------------------------
      add(p_north, BorderLayout.NORTH);
      add(p_center, BorderLayout.CENTER);
      add(p_south, BorderLayout.SOUTH);
      // -------------------------------------
      setBackground(Color.YELLOW);
      setPreferredSize(new Dimension(500, 800));
      setVisible(true);
      connectServer();// ��������
   }

   public String accept() {
      String c_id = t_id.getText();
      String c_pw = new String(t_pw.getPassword());
      obj.put("Type", "login");
      obj.put("Id", c_id);
      obj.put("Pw", c_pw);
      String order = obj.toString();


      if (c_id.length() == 0) {
         JOptionPane.showMessageDialog(p_center, "���̵� �Է����ּ���");
         return null;
      }
      if (c_pw.length() == 0) {
         JOptionPane.showMessageDialog(p_center, "��й�ȣ�� �Է����ּ���");
         return null;
      }
      return order;
   }

   public void connectServer() {

      String ip = "127.0.0.1";
      try {
         client = new Socket(ip, port);
         ct = new ClientThread(client, this);
         ct.start();


      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }



}