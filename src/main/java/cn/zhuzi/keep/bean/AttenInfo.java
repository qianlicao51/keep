package cn.zhuzi.keep.bean;

/**
 * 关注的实体类
 */
public class AttenInfo {
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 注册时间
	 */
	private String created;

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		// created=2016-02-25T11:05:06.000Z

		this.created = created.split("T")[0] + "_";
	}

	public String getUsername() {
		return created + username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "AttenInfo [username=" + username + ", id=" + id + "]";
	}

}
