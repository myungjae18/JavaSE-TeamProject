package messenger.utils;

import java.net.Socket;
import java.util.ArrayList;

import javax.swing.tree.DefaultMutableTreeNode;

import messenger.chatlist.ChatListMain;
import messenger.client.ClientThread;
import messenger.mainframe.MainFrame;
import messenger.memberlist.MemberListAdd;
import messenger.memberlist.MemberListMain;

public class Master {
	Socket socket;
	ClientThread ct;
	MainFrame mainFrame;
	MemberListMain mamberListMain;
	ChatListMain chatListMain;
	ParserResult parse;

	public Master(ClientThread ct, MainFrame mainFrame, ParserResult parse) {
		this.ct = ct;
		this.mainFrame = mainFrame;
		this.parse=parse;
		
		//mainFrame.friendList.size();/// 5�� ������ �� ģ�� 5��
		makeFriendList();
	}//������
	
	//ģ����� ȭ�鿡 �ѷ��ְ��ϴ� �޼���
	public void makeFriendList() {
		ArrayList<String> TypeList = new ArrayList<String>();//Ÿ�� �̸� ����
		ArrayList<DefaultMutableTreeNode> parentType = new ArrayList<DefaultMutableTreeNode>();// �θ� ����
		boolean firstMake = true;

		for (int i = 0; i < mainFrame.friendList.size(); i++) {
			String[] obj = (String[]) mainFrame.friendList.get(i);
//			System.out.println(obj[0]); //�ڵ�
			System.out.println(obj[1]); // �̸�
//			System.out.println(obj[2]);  //��
//			System.out.println(obj[3]); //Ÿ��  ��� ���� �迭 �����ؼ� �������� ���ؾ���
//			System.out.println(obj[4]); //�̹���
//			System.out.println(c++);
			String ImgPath="dog/"+obj[4];
			if (firstMake) { /// ó�� �ѹ��� ������ ����
				DefaultMutableTreeNode parent = mainFrame.memberListMain.createParent(obj[3]);
				mainFrame.memberListMain.node_root.add(parent);
				DefaultMutableTreeNode child = mainFrame.memberListMain.createChild(obj[1], ImgPath, obj[0]);
				parent.add(child);
				TypeList.add(obj[3]); // Ÿ���̸� String ������ �ϳ��� ����Ʈ�� ��´�
				firstMake = !firstMake;
				parentType.add(parent);// �θ��带 ����
			} else {				
				if (TypeList.contains(obj[3])) { // Ÿ�� �ߺ��϶�
					System.out.println(TypeList.size());
					for (int a = 0; a < TypeList.size(); a++) {
						if (parentType.get(a).toString().equals(obj[3])) {
							DefaultMutableTreeNode child = mainFrame.memberListMain.createChild(obj[1], ImgPath, obj[0]);
							parentType.get(a).add(child);					
							break;
						}
					}
				} else { // Ÿ�� �ߺ��� ������
					DefaultMutableTreeNode parent = mainFrame.memberListMain.createParent(obj[3]);
					mainFrame.memberListMain.node_root.add(parent);
					DefaultMutableTreeNode child = mainFrame.memberListMain.createChild(obj[1], ImgPath, obj[0]);
					parent.add(child);
					TypeList.add(obj[3]);
					parentType.add(parent);
				}

			}

		}
	}

}
