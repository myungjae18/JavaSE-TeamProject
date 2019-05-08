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
		
		//mainFrame.friendList.size();/// 5가 나오면 총 친구 5명
		makeFriendList();
	}//생성자
	
	//친구목록 화면에 뿌려주게하는 메서드
	public void makeFriendList() {
		ArrayList<String> TypeList = new ArrayList<String>();//타입 이름 저장
		ArrayList<DefaultMutableTreeNode> parentType = new ArrayList<DefaultMutableTreeNode>();// 부모를 저장
		boolean firstMake = true;

		for (int i = 0; i < mainFrame.friendList.size(); i++) {
			String[] obj = (String[]) mainFrame.friendList.get(i);
//			System.out.println(obj[0]); //코드
			System.out.println(obj[1]); // 이름
//			System.out.println(obj[2]);  //닉
//			System.out.println(obj[3]); //타입  얘는 따로 배열 저장해서 돌때마다 비교해야함
//			System.out.println(obj[4]); //이미지
//			System.out.println(c++);
			String ImgPath="dog/"+obj[4];
			if (firstMake) { /// 처음 한번은 무조건 생성
				DefaultMutableTreeNode parent = mainFrame.memberListMain.createParent(obj[3]);
				mainFrame.memberListMain.node_root.add(parent);
				DefaultMutableTreeNode child = mainFrame.memberListMain.createChild(obj[1], ImgPath, obj[0]);
				parent.add(child);
				TypeList.add(obj[3]); // 타입이름 String 형으로 하나를 리스트에 담는다
				firstMake = !firstMake;
				parentType.add(parent);// 부모노드를 저장
			} else {				
				if (TypeList.contains(obj[3])) { // 타입 중복일때
					System.out.println(TypeList.size());
					for (int a = 0; a < TypeList.size(); a++) {
						if (parentType.get(a).toString().equals(obj[3])) {
							DefaultMutableTreeNode child = mainFrame.memberListMain.createChild(obj[1], ImgPath, obj[0]);
							parentType.get(a).add(child);					
							break;
						}
					}
				} else { // 타입 중복이 없을때
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
