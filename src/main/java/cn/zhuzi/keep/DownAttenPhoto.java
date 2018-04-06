package cn.zhuzi.keep;

import java.util.List;

import cn.zhuzi.keep.bean.AttenInfo;
import cn.zhuzi.keep.utils.DownPhoUtils;

public class DownAttenPhoto {

	public static void downPic(List<AttenInfo> list) {

		if (list != null) {
			for (AttenInfo attenInfo : list) {
				System.out.println(attenInfo + "下载开始");
				new SysDownThread(attenInfo).start();
				System.out.println(attenInfo + "下载完毕");
				System.out.println();
			}
			
			//异常图片
			DownPhoUtils.downConnectOut();
			
		}
	}
}
