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
	public DefaultMutableTreeNode node_root; // �̰� ģ���� �����ɶ����� ���ܾ� �ϹǷ� ArrayList��ߵɵ�
	DefaultMutableTreeNode node_family, node_friend, node_colleague;
	ListCellRenderer renderer;// node�� ������ �̹��� ������ ���� �ʿ��� Ŭ����
	public JTree tree;
	ImageLoad imageLoad;
	// ���� ����Ʈ ����� ������ �迭
	ArrayList<DefaultMutableTreeNode> parentList = new ArrayList<DefaultMutableTreeNode>(); // �������� ������ ����Ʈ
	ArrayList<DefaultMutableTreeNode> childList = new ArrayList<DefaultMutableTreeNode>();// ģ������ ������ ����Ʈ
	// Icon icon, icon2, icon3; ���򰡴�
	MemberListAdd memberListAdd;
	MainFrame mainFrame;
	public JScrollPane scroll;

	public MemberListMain(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		node_root = new DefaultMutableTreeNode("���� ���");
		imageLoad = new ImageLoad();

		tree = new JTree(node_root);// ����Ʈ������(��ȣ)
		renderer = new ListCellRenderer();

		tree.setFont(new Font("�������", Font.BOLD, 30));
		tree.setCellRenderer(renderer);
		// tree.setEditable(true);//ģ����Ͽ��� ���������ϰ��ϴ� �޼���
		// -------------------------------------------------------------------------------------------

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 1) {// ����Ŭ���� ä�ù� ����
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

	public DefaultMutableTreeNode createParent(String typeName) { // ���� ���� �����
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(typeName);
		parentList.add(parent);
		return parent;
	}

	public DefaultMutableTreeNode createChild(String name, String imgPath, String MyCode) { // �ڽĸ���� // �ϸ�ɵ�
		MemberListAdd child = new MemberListAdd(name, imgPath, MyCode);
		childList.add(child);
		return child;
	}

	public void joinChatRoom() {
		if (parentList.contains(tree.getSelectionPath().getLastPathComponent())) {
			System.out.println("�θ� �̹Ƿ� �ƹ��͵� ���� ����");
			System.out.println(tree.getSelectionPath().getLastPathComponent());

		} else if (tree.getSelectionPath().getLastPathComponent().toString().equals("���� ���")) {
			System.out.println("�ֻ����Ƿ� �ƹ��͵� ���� ����");
		} else {
			// �̶� �����ؾ���..
			MemberListAdd node = (MemberListAdd) tree.getSelectionPath().getLastPathComponent();
			String a = node.toString();
			System.out.println("���� ������ : " + a);
			// ���⼭ 1:1 ä���� �����;���...
			// �������� 1:1ä�ù��� ������ ä�÷α׸� ����;��ϰ�
			// ä�ù��� ������ �ȵ������� �۾����� ���������� insert into 'ä�ù�' ��������
			// ���ڵ�� ���� �ڵ� ����
			JSONObject obj = new JSONObject();
			obj.put("Type", "chatStart");
			obj.put("MyCode", mainFrame.MyCode);
			obj.put("FriendCode", node.getCode());

			mainFrame.ct.send(obj.toString());
		}
	}

}
