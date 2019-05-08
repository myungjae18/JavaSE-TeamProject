package messenger.memberlist;

import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

public class ListCellRenderer implements TreeCellRenderer {
	JLabel label;

	public ListCellRenderer() {
		label = new JLabel();

	}

	// 오버라이드
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		// super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf,
		// row, hasFocus);
		Object obj = ((DefaultMutableTreeNode) value).getUserObject(); // 이름을 알수있는 명령어
		Object o = ((DefaultMutableTreeNode) value).getClass(); // 어떤 클래스인지 확인
		int aa = ((DefaultMutableTreeNode) value).getLevel(); // 레벨은 최상위는 0 그 아래로 내려가면 1 씩 증가
		boolean bb = ((DefaultMutableTreeNode) value).isLeaf();
		// System.out.println(((MemberListAdd) value).getClass());
		// MemberListAdd obj2=(MemberListAdd)((DefaultMutableTreeNode)
		// value).getUserObject();
		// System.out.println(obj);
		// System.out.println(o);
		// System.out.println(o+" "+aa);
		// Object oo=((MemberListAdd) value).getClass();
		// Object oo=((DefaultMutableTreeNode) value);
		// System.out.println(tree);
		// Object oo=value;

		if (value instanceof MemberListAdd) {
			MemberListAdd listAdd = (MemberListAdd) value;
			Image image = listAdd.getImg();
			//System.out.println(image);
			if (image != null) {
				label.setIcon(new ImageIcon(image));
			}

			label.setText(listAdd.getName());
		} else {
			label.setIcon(null);
			label.setText("" + value);
		}

		return label;
	}

}
