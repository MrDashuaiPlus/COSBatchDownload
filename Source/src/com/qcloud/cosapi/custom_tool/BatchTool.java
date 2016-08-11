package com.qcloud.cosapi.custom_tool;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qcloud.cosapi.api.CosCloud;
import com.qcloud.cosapi.javabeans.FileBean;

public class BatchTool {
	// 通过控制台获取AppId,SecretId,SecretKey
	public static int APP_ID = 10003421;
	public static  String SECRET_ID = "AKIDLGvDdsE7YnjeSg7GRdaiuHy7rF0laYBR";
	public static String SECRET_KEY = "ik1ozSOu4hUDHzT7UBJntQtoUCveK2UN";
	public static CosCloud cos = new CosCloud(APP_ID, SECRET_ID, SECRET_KEY);

	public static ArrayList<FileBean> getAllFileBeansOfDirFullPart(ArrayList<FileBean> srcList,
			ArrayList<FileBean> list) {
		for (int i = 0; i < srcList.size(); i++) {
			FileBean fileBean = srcList.get(i);
			if (fileBean.isDir() == false) {
				list.add(fileBean);
			} else {
				ArrayList<FileBean> sublist = getAllFileBeansOfDir(fileBean.getBucketName(),
						fileBean.getDirectory() + fileBean.getName() + "/");
				getAllFileBeansOfDirFullPart(sublist, list);
			}
		}
		return list;
	}

	public static ArrayList<FileBean> getAllFileBeansOfDirFull(String bucketName, String remotePath) {
		ArrayList<FileBean> list = new ArrayList<FileBean>();
		ArrayList<FileBean> srcList = getAllFileBeansOfDir(bucketName, remotePath);
		getAllFileBeansOfDirFullPart(srcList, list);

		return list;
	}

	public static ArrayList<FileBean> getAllFileBeansOfDir(String bucketName, String remotePath) {

		ArrayList<FileBean> list = new ArrayList<FileBean>();
		String context = "";
		getAllFileBeansOfDirPart(bucketName, remotePath, context, list);
		return list;

	}

	public static void getAllFileBeansOfDirPart(String bucketName, String remotePath, String context,
			ArrayList<FileBean> list) {
		int pageSize = 20;
		int count = 0;
		String mContext = "";
		try {
			String resultString = cos.getFolderList(bucketName, remotePath, pageSize, context, 0,
					CosCloud.FolderPattern.Both);
			JSONObject jsonObject = new JSONObject(resultString);
			if (jsonObject.getString("code").equals("0")) {
				JSONObject data = jsonObject.getJSONObject("data");
				mContext = data.getString("context");
				JSONArray jsonArray = data.getJSONArray("infos");
				count = jsonArray.length();
				for (int i = 0; i < jsonArray.length(); i++) {
					FileBean fileBean = new FileBean();
					JSONObject fileJsonObject = jsonArray.getJSONObject(i);
					fileBean.setName(fileJsonObject.getString("name"));
					fileBean.setDirectory(remotePath);
					fileBean.setBucketName(bucketName);
					if (fileJsonObject.has("access_url")) {
						fileBean.setAccess_url(fileJsonObject.getString("access_url"));
					} else {
						fileBean.setDir(true);
					}
					list.add(fileBean);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (count == pageSize && !mContext.isEmpty()) {
			getAllFileBeansOfDirPart(bucketName, remotePath, mContext, list);
		}
	}
}
