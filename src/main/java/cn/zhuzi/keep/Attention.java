package cn.zhuzi.keep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;

import cn.zhuzi.keep.bean.AttenInfo;
import cn.zhuzi.keep.json.AttenJson;
import cn.zhuzi.keep.utils.SysConstant;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 关注信息列表
 */
public class Attention {

	/**
	 * 获取关注列表
	 */
	public static List<AttenInfo> getAttenInfo() {
		List<AttenInfo> attenInfoList = new ArrayList<AttenInfo>();
		AttenJson attenListJson = null;
		// 关注列表
		String followUrl = SysConstant.FOLLWOURL_PRE + SysConstant.MyID + SysConstant.FOLLWOURL_SUFF;
		String nextUrl = "";
		try {
			String attenInfoListJson = Jsoup.connect(followUrl).headers(SysConstant.headMap).ignoreContentType(true).get().body().html();
			attenListJson = JSON.parseObject(attenInfoListJson, AttenJson.class);
			// TODO 滚动屏幕获取更多关注
			List<AttenInfo> users = attenListJson.getData().getUsers();
			attenInfoList.addAll(users);
			String lastUid = users.get(users.size() - 1).getId();
			boolean hasNext = true;
			while (hasNext) {
				nextUrl = followUrl + SysConstant.FOLLWOURL_SUFF_PART2 + lastUid;
				String attenNextPart = Jsoup.connect(nextUrl).headers(SysConstant.headMap).ignoreContentType(true).get().body().html();
				attenListJson = JSON.parseObject(attenNextPart, AttenJson.class);
				if (attenListJson.getData().getUsers().isEmpty()) {
					hasNext = false;
				} else {
					List<AttenInfo> users2 = attenListJson.getData().getUsers();
					attenInfoList.addAll(users2);
					lastUid = users2.get(users2.size() - 1).getId();
				}
			}
		} catch (IOException e) {
		}
		return attenInfoList;
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<AttenInfo> attenInfo = getAttenInfo();
		DownAttenPhoto.downPic(attenInfo);
		long end = System.currentTimeMillis();
		System.out.println("开始时间" + new DateTime(start).toString("yyyy-MM-dd HH:mm:ss"));
		System.out.println("结束时间" + new DateTime(end).toString("yyyy-MM-dd HH:mm:ss"));

	}
}
