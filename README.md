# keep
## 获取keep关注人相册 ##

使用jsoup和fiddler4。分析请求地址。使用jsoup获取请求到的json。从中得到图片URL。需要注意的是安卓手机屏幕滚动刷新。分析得出。上一次请求最后一个图片ID，是下一次请求的参数
    
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

## 配置信息 ##
    配置信息在  cn.zhuzi.keep.utils.SysConstant.java 里面