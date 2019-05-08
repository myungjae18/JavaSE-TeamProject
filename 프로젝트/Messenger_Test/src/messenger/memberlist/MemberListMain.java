package messenger.memberlist;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import messenger.mainframe.MainFrame;
import messenger.utils.ImageLoad;

public class MemberListMain extends JPanel {
	TreeModel treeModel;
	public DefaultMutableTreeNode node_root; // 이거 친구가 생성될때마다 생겨야 하므로 ArrayList써야될듯
	DefaultMutableTreeNode node_family, node_friend, node_colleague;
	ListCellRenderer renderer;// node에 아이콘 이미지 입히기 위해 필요한 클래스
	public JTree tree;
	ImageLoad imageLoad;
	// 각각 리스트 목록을 저장할 배열
	ArrayList<DefaultMutableTreeNode> parentList = new ArrayList<DefaultMutableTreeNode>(); // 상위폴더 저장할 리스트
	ArrayList<DefaultMutableTreeNode> childList = new ArrayList<DefaultMutableTreeNode>();// 친구들을 저장할 리스트
	// Icon icon, icon2, icon3; 지움가능
	MemberListAdd memberListAdd;
	MainFrame mainFrame;
	public JScrollPane scroll;

	public MemberListMain(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		node_root = new DefaultMutableTreeNode("나의 목록");
		imageLoad = new ImageLoad();

		tree = new JTree(node_root);// 메인트리생성(민호)
		renderer = new ListCellRenderer();

		tree.setFont(new Font("맑은고딕", Font.BOLD, 30));
		tree.setCellRenderer(renderer);
		// tree.setEditable(true);//친구목록에서 수정가능하게하는 메서드
		// -------------------------------------------------------------------------------------------

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {// 더블클릭시 채팅방 생성
					joinChatRoom();
				}
			}
		});

		tree.setPreferredSize(new Dimension(500, 650));
		setLayout(new BorderLayout());
		scroll = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(500, 612));
		this.add(scroll, BorderLayout.NORTH);

		tree.setBackground(mainFrame.getColor());
		setPreferredSize(new Dimension(500, 650));
	}

	protected TreePath MemberListAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	public DefaultMutableTreeNode createParent(String typeName) { // 상위 폴더 만들기
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(typeName);
		parentList.add(parent);
		return parent;
	}

	public DefaultMutableTreeNode createChild(String name, String imgPath, String MyCode) { // 자식만들기 // 하면될듯
		MemberListAdd child = new MemberListAdd(name, imgPath, MyCode);
		childList.add(child);
		return child;
	}

	public void joinChatRoom() {
		if (parentList.contains(tree.getSelectionPath().getLastPathComponent())) {
			System.out.println("부모 이므로 아무것도 하지 않음");
			System.out.println(tree.getSelectionPath().getLastPathComponent());

		} else if (tree.getSelectionPath().getLastPathComponent().toString().equals("나의 목록")) {
			System.out.println("최상위므로 아무것도 하지 않음");
		} else {
			// 이때 실행해야함..
			MemberListAdd node = (MemberListAdd) tree.getSelectionPath().getLastPathComponent();
			String a = node.toString();
			System.out.println("누구 눌렀니 : " + a);
			// 여기서 1:1 채팅을 가져와야함...
			// 서버에선 1:1채팅방이 있으면 채팅로그를 끌어와야하고
			// 채팅방이 개설이 안되있으면 글씨쓰고 엔터쳤을때 insert into '채팅방' 만들어야함
			// 내코드랑 상대방 코드 보냄
			JSONObject obj = new JSONObject();
			obj.put("Type", "chatStart");
			obj.put("MyCode", mainFrame.MyCode);
			obj.put("FriendCode", node.getCode());

			mainFrame.ct.send(obj.toString());
		}
	}

}
