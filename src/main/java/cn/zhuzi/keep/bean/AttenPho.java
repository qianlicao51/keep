package cn.zhuzi.keep.bean;

import java.util.List;

/**
 * 用户相册
 */
public class AttenPho {
	/**
	 * 图片id
	 */
	private String id;

	private List<String> images;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "AttenPho [id=" + id + ", images=" + images + "]";
	}

}
