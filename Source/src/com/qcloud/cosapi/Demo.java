package com.qcloud.cosapi;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.qcloud.cosapi.api.CosCloud;
import com.qcloud.cosapi.custom_tool.BatchTool;
import com.qcloud.cosapi.javabeans.FileBean;
import com.zhan_dui.download.DownloadManager;
import com.zhan_dui.download.DownloadMission;

public class Demo {
	// 通过控制台获取AppId,SecretId,SecretKey

	public static void main(String[] args) {
		String path = Demo.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.print(path);
		path = path.substring(0).replace("COSBatchDownload.jar", "");
		File file = new File(path + File.separator + "config.xml");

		try {
			JAXBContext context = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Config config = (Config) unmarshaller.unmarshal(file);
			BatchTool.APP_ID = config.getAPP_ID();
			BatchTool.SECRET_ID = config.getSECRET_ID();
			BatchTool.SECRET_KEY = config.getSECRET_KEY();
			BatchTool.cos = new CosCloud(BatchTool.APP_ID, BatchTool.SECRET_ID, BatchTool.SECRET_KEY);
			ArrayList<FileBean> list = BatchTool.getAllFileBeansOfDirFull(config.getBucketName(),
					config.getRemotePath());
			DownloadManager downloadManager = DownloadManager.getInstance();

			for (int i = 0; i < list.size(); i++) {
				FileBean fileBean = list.get(i);
				try {
					downloadManager.addMission(fileBean.getAccess_url(),
							config.getLocalPath() + fileBean.getDirectory(), fileBean.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			downloadManager.start();
			while (true) {

				System.out.println("Downloader information Speed:" + downloadManager.getReadableTotalSpeed()
						+ " Down Size:" + downloadManager.getReadableDownloadSize() + " totalFileSize: "
						+ downloadManager.getReadableTotalFileSize() + " pencent: "
						+ String.format("%.2f",downloadManager.getDownloadPercent())+"%");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (downloadManager.isAllFileFinished()) {
					System.out.println("下载完成！");
					System.exit(0);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
