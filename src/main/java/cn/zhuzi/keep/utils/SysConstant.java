package cn.zhuzi.keep.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SysConstant {
	public static Map<String, String> headMap = new HashMap<String, String>();
	/**
	 * 认证信息key
	 */
	public static final String Ahthor_KEY = "Authorization";
	/**
	 * 认证信息val
	 */
	private static final String Ahthor_VAL = "Bearer .cZepoPPf_DnStLUQjjeJnxbnWC*******************s";

	static {
		headMap.put(Ahthor_KEY, Ahthor_VAL);
		headMap.put("x-timestamp", System.currentTimeMillis() + "");

	}
	public static final String MyID = "56834a0b7d803e4c3a5cd367";
	/**
	 * 关注的url 前缀
	 */
	public static final String FOLLWOURL_PRE = "https://api.gotokeep.com/social/v2/people/";
	/**
	 * 关注的url 后缀
	 */
	public static final String FOLLWOURL_SUFF = "/followings";
	/**
	 * Z这部分需要滚动屏幕
	 */
	public static final String FOLLWOURL_SUFF_PART2 = "?lastId=";
	/**
	 * 图片URL
	 */
	public static final String PHOTO_URL_PRE = "https://api.gotokeep.com/social/v4/people/";
	public static final String PHOTO_URL_PART2 = "/listmore?type=photo";
	public static final String PHOTO_URL_PART4 = "&limit=20";
	public static final String PHOTO_URL_PART3 = "&lastId=";

	public static final File SAVE_FILE = new File("e://keep");

	/**
	 * 下载异常信息 存放位置
	 */
	public static final File CONNECT_OUT_FILE = new File(SAVE_FILE,"connect_out.txt");
	public static final File CONNECT_OUT_FILE_BAK = new File(SAVE_FILE,"connect_out_bak.txt");

	public static final String PIC_URL_PRE = "picture/";
	/**
	 * 回车换行
	 */
	public static final CharSequence NEXT_LINE="\r\n";
	
	public static final String CHARSET="UTF-8";
}
