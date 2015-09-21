package com.platform.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import jcifs.Config;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public class SmbUtil20140625 {
	private static Logger logger = Logger.getLogger(SmbUtil20140625.class);

	public static String getSmbUrl(String path) {
		StringBuffer buf = new StringBuffer("smb://");
		// buf.append(Config.getProperty("jcifs.smb.client.username")+":");
		// buf.append(Config.getProperty("jcifs.smb.client.password")+"@");
		buf.append(Config.getProperty("jcifs.smb.server.host"));
		buf.append(Config.getProperty("jcifs.smb.server.root"));
		if (path != null)
			buf.append(path.trim());
		if (logger.isDebugEnabled())
			logger.debug("getSmbUrl:\t" + buf.toString());
		return buf.toString();
	}
	
	public static SmbFile getSmbFile(String serverFileUrl) {
		String buf = "smb://";
		buf += Config.getProperty("jcifs.smb.server.host");
		buf += Config.getProperty("jcifs.smb.server.root");
		try {
			return new SmbFile(buf + serverFileUrl);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 文件默认编码读取为文�?
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileContent(String path) {
		return getFileContent(path, null);
	}

	/**
	 * 按指定文件编码读取为文本
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileContent(String path, String charset) {
		SmbFile file = getSmbFile(path);
		if (file == null)
			return null;
		String text = "";
		try {
			InputStream in = file.getInputStream();
			if (charset == null)
				text = IOUtils.toString(in);
			else
				text = IOUtils.toString(in, charset);
			IOUtils.closeQuietly(in);
		} catch (IOException ioe) {
			logger.error("获取文件{" + path + "}InputStram异常:", ioe);
		}
		return text;
	}

	public static int deleteDir(SmbFile file) {
		int ret = 0;
		try {
			if (file == null || !file.exists())
				return -1;
			if (file.isFile())
				return -2;
			file.delete();
			ret = 1;
		} catch (SmbException se) {
			logger.error("deleteDir:删除目录异常:" + file.getPath(), se);
		}
		return ret;
	}

	public static int deleteDir(String file) {
		return deleteDir(getSmbFile(file));
	}

	public static int deleteFileOrDir(String path) {
		return deleteFileOrDir(getSmbFile(path));
	}

	public static int deleteFileOrDir(SmbFile file) {
		int ret = 0;
		try {
			if (file == null)
				return -1;
			file.delete();
			ret = 1;
		} catch (SmbException se) {
			logger.error("deleteFile:删除文件异常:" + file.getPath(), se);
		}
		return ret;
	}

	public static int createDir(String dir) {
		return createDir(getSmbFile(dir));
	}

	public static int createDir(SmbFile file) {
		int ret = 0;
		try {
			if (file == null || file.exists())
				return -1;
			if (file.isFile())
				return -2;
			file.mkdirs();
			ret = 1;
		} catch (SmbException se) {
			logger.error("createDir:创建目录异常:" + file.getPath(), se);
		}
		return ret;
	}

	/**
	 * 自动�?��文件是否存在，目录是否存在，如果都不存在则自动新建�?返回null表示异常
	 * 
	 * @param file
	 * @return SmbFile
	 */

	/**
	 * 默认覆盖�?��OutputStream
	 * 
	 * @param path
	 *            //Smb服务器的相对路径
	 * @return
	 */
	public static OutputStream getFileOutputStream(String path) {
		SmbFile file = getSmbFile(path);
		OutputStream out = null;
		try {
			if (file == null || !file.exists() || file.isDirectory())
				logger.warn("SmbFile={" + path + "}不存在或不是文件.");
			else
				out = file.getOutputStream();
		} catch (SmbException se) {
			logger.error("getFileOutputStream(path)SmbException异常:", se);
		} catch (IOException ioe) {
			logger.error("getFileOutputStream(path)获取文件流异�?", ioe);
		}
		return out;
	}

	public static OutputStream getFileOutputStream(SmbFile file, boolean append) {
		if (file == null)
			return null;
		SmbFileOutputStream out = null;
		try {
			out = new SmbFileOutputStream(file, append);
		} catch (MalformedURLException murle) {
			logger.warn("getSmbFile::初始化SmbFile对象时URL异常:", murle);
		} catch (UnknownHostException uhe) {
			logger.error("getFileOutputStream未知主机:" + file.getPath(), uhe);
		} catch (SmbException se) {
			logger.error("getFileOutputStream异常:" + file.getPath(), se);
		}
		return out;
	}

	private static void createLocalNewfile(File file) {
		String path = file.getAbsolutePath();
		int pos = path.lastIndexOf(File.separator);
		if (pos > 0)
			path = path.substring(0, pos);
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory())
			;
		else
			dir.mkdirs();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				logger.error("createLocalNewfile:" + file.getAbsolutePath() + "异常:", ioe);
			}
		}
	}

}
