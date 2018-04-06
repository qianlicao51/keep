package cn.zhuzi.keep.json;

import java.util.List;

import cn.zhuzi.keep.bean.AttenInfo;

/**
 * 关注人json
 *
 */
public class AttenListJson {

	private List<AttenInfo> users ;

	public List<AttenInfo> getUsers() {
		return users;
	}

	public void setUsers(List<AttenInfo> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "AttenListJson [users=" + users + "]";
	}

}
