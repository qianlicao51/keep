package cn.zhuzi.keep.json;

import java.util.List;

/**
 * 
 *图片json
 */
public class PhtotJson {
	private List<AttenPhotoJson> data ;

	public List<AttenPhotoJson> getData() {
		return data;
	}

	public void setData(List<AttenPhotoJson> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PhtotJson [data=" + data + "]";
	}
	

}
