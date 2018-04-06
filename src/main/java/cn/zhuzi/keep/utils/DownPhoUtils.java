package cn.zhuzi.keep.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.helper.StringUtil;
import org.junit.Test;

public class DownPhoUtils {

	public static void downPic(String img, String username) {
		String imgSub = img.substring(img.indexOf(SysConstant.PIC_URL_PRE) + SysConstant.PIC_URL_PRE.length());
		File saveFile = new File(SysConstant.SAVE_FILE, username + "/" + imgSub.replaceAll("/", "-"));
		try {
			if (!saveFile.exists()) {
				FileUtils.copyURLToFile(new URL(img), saveFile, 5000, 5000);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			try {
				FileUtils.write(SysConstant.CONNECT_OUT_FILE, username + "<>" + img + "<>" + saveFile, true);
				FileUtils.write(SysConstant.CONNECT_OUT_FILE, SysConstant.NEXT_LINE, true);
				// Unix 的换行 \n ;Mac -->\r
			} catch (IOException e1) {
			}
			System.out.println("          下载异常 " + username + "  " + img);
		}
	}

	/**
	 * 下载异常图片
	 */
	public static void downConnectOut() {
		try {

			while (true) {

				List<String> readLines = FileUtils.readLines(SysConstant.CONNECT_OUT_FILE);
				if (SysConstant.NEXT_LINE.equals(readLines.toString())) {
					break;
				}
				System.out.println("处理异常");
				SysConstant.CONNECT_OUT_FILE.delete();
				for (String line : readLines) {
					System.out.println(line);
					if (!StringUtil.isBlank(line)) {
						String[] split = line.split("<>");
						downPic(split[1], split[0]);
					}

				}
			}
		} catch (IOException e) {
		}
	}
}
