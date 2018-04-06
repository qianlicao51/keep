package cn.zhuzi.keep;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import cn.zhuzi.keep.bean.AttenInfo;
import cn.zhuzi.keep.json.AttenPhotoJson;
import cn.zhuzi.keep.json.PhtotJson;
import cn.zhuzi.keep.utils.DownPhoUtils;
import cn.zhuzi.keep.utils.SysConstant;

import com.alibaba.fastjson.JSON;

public class SysDownThread extends Thread {

	public SysDownThread(AttenInfo attenInfo) {

		try {
			String lastId = "";
			boolean stop = true;
			String picListURL = SysConstant.PHOTO_URL_PRE + attenInfo.getId() + SysConstant.PHOTO_URL_PART2 + SysConstant.PHOTO_URL_PART4;
			Connection connect = Jsoup.connect(picListURL);
			Response resp = connect.ignoreContentType(true).execute();
			String html = resp.body();
			PhtotJson photoJson = JSON.parseObject(html, PhtotJson.class);

			File file = new File(SysConstant.SAVE_FILE, attenInfo.getUsername());
			if (!file.exists()) {
				file.mkdirs();
			}
			for (AttenPhotoJson pho : photoJson.getData()) {

				lastId = pho.getId();
				List<String> images = pho.getImages();
				for (String img : images) {

					DownPhoUtils.downPic(img, attenInfo.getUsername());
				}
			}
			while (stop) {
				picListURL = SysConstant.PHOTO_URL_PRE + attenInfo.getId() + SysConstant.PHOTO_URL_PART2 + SysConstant.PHOTO_URL_PART3 + lastId + SysConstant.PHOTO_URL_PART4;
				Connection connection = Jsoup.connect(picListURL).ignoreContentType(true);
				// 使用这种方式 比起 get() ,可以避免出现 <span>标签 有利于json转换。
				String otherPic = connection.execute().body().toString();
				PhtotJson parseObject = JSON.parseObject(otherPic, PhtotJson.class);
				// 停止继续
				if (parseObject.getData().isEmpty()) {
					stop = false;
				} else {
					for (AttenPhotoJson pho : parseObject.getData()) {
						lastId = pho.getId();
						List<String> images = pho.getImages();
						for (String img : images) {
							DownPhoUtils.downPic(img, attenInfo.getUsername());
						}
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private AttenInfo attenInfo;

	public AttenInfo getAttenInfo() {
		return attenInfo;
	}

	public void setAttenInfo(AttenInfo attenInfo) {
		this.attenInfo = attenInfo;
	}

}
