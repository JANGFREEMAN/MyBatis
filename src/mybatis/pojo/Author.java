package mybatis.pojo;

public class Author {

	private int id;
	private User user;
	private int IDCard;
	private String realName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getIDCard() {
		return IDCard;
	}
	public void setIDCard(int iDCard) {
		IDCard = iDCard;
	}
}
