package cn.zhuzi.keep.json;

import java.util.List;

public class AttenPhotoJson {

	private String id;
	private List<String> images ;

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
		return "AttenPhotoJson [id=" + id + ", images=" + images + "]";
	}

}
