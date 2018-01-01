package jsw.report.viewBean;

/**
 * Menutable generated by hbm2java
 */
public class Menutable implements java.io.Serializable {

	private int menuId;
	private String menuName;
	private String subMenu;
	private String isleaf;
	private String action;
private String href;
	public Menutable() {
	}

	public Menutable(int menuId, String menuName, String subMenu, String isleaf, String action,String href) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.subMenu = subMenu;
		this.isleaf = isleaf;
		this.action = action;
		this.href=href;
	}

	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSubMenu() {
		return this.subMenu;
	}

	public void setSubMenu(String subMenu) {
		this.subMenu = subMenu;
	}

	public String getIsleaf() {
		return this.isleaf;
	}

	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public String toString() {
		return "Menutable [menuId=" + menuId + ", menuName=" + menuName + ", subMenu=" + subMenu + ", isleaf=" + isleaf
				+ ", action=" + action + "]";
	}

}