package com.platform.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import jcifs.Config;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class SmbUtil {
	private static Logger log = Logger.getLogger(SmbUtil.class);

	static {
		// String path = "D:/workspace/csjmro/trunk/src/smb.properties";
		String path = ServletActionContext.getServletContext().getRealPath("/WEB-INF/classes/smb.properties");
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(path));
		} catch (Exception e) {
			log.error(e);
			System.out.println(e);
		}

		Config.setProperties(props);
		String root = props.getProperty("jcifs.smb.server.root");
		if (!root.startsWith("/"))
			root = "/" + root;
		if (root.endsWith("/"))
			root = root.substring(0, root.length() - 1);
		Config.setProperty("jcifs.smb.server.root", root);
		jcifs.Config.registerSmbURLHandler();
	}

	public static String[] listFiles(String dir) {
		String path = "smb://" + Config.getProperty("jcifs.smb.server.host") + Config.getProperty("jcifs.smb.server.root") + dir;
		String[] files = null;
		try {
			files = new SmbFile(path).list();
		} catch (Exception e) {
			log.error(path);
		}
		return files;
	}

	public static String getStandardTime(String date) {
		return date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8) + " " + date.substring(9, 11) + ":" + date.substring(11, 13) + ":" + date.substring(13, 15);
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

	public static void copyLocal2Server(File localFile, String serverFileUrl) {
		OutputStream out = null;
		FileInputStream fin = null;
		try {
			SmbFile serverFile = getSmbFile(serverFileUrl);

			SmbFile serverDir = getSmbFile(serverFileUrl.substring(0, serverFileUrl.lastIndexOf("/")));
			if (!serverDir.exists()) {
				serverDir.mkdirs();
			}

			out = serverFile.getOutputStream();
			fin = new FileInputStream(localFile);
			IOUtils.copy(fin, out);
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (fin != null)
				IOUtils.closeQuietly(fin);
			if (out != null)
				IOUtils.closeQuietly(out);
		}
	}

	public static void deleteFile(String serverFileUrl) {
		SmbFile serverFile = getSmbFile(serverFileUrl);
		try {
			serverFile.delete();
		} catch (SmbException e) {

		}
	}

	public static InputStream getInputStream(String path) {
		SmbFile file = getSmbFile(path);
		if (file != null) {
			try {
				return new BufferedInputStream(new SmbFileInputStream(file));
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;
	}

	public static void copyServer2Local(String serverFileUrl, String targetFileUrl) {
		SmbFile serverFile = getSmbFile(serverFileUrl);
		OutputStream out = null;
		InputStream fin = null;
		try {
			fin = serverFile.getInputStream();
			out = new FileOutputStream(targetFileUrl);
			IOUtils.copy(fin, out);
		} catch (Exception e) {

		} finally {
			IOUtils.closeQuietly(fin);
			IOUtils.closeQuietly(out);
		}
	}
	
}
