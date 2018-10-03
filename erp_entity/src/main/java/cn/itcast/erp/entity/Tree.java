package cn.itcast.erp.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形菜单数据实体
 * @author Erc
 *
 */
public class Tree {

	/**
	 *  id：节点ID，对加载远程数据很重要。
		text：显示节点文本。
		checked：表示该节点是否被选中。
		children: 一个节点数组声明了若干节点。

	 */
	private String id;//菜单ID
	private String text;//菜单名称
	private boolean checked;//是否被选中
	private List<Tree> children;//子菜单
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Tree> getChildren() {
		if(null == children){
			//确保调用添加二级菜单时出现空引用
			children = new ArrayList<Tree>();
		}
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
}
