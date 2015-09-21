package com.platform.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理
 * 
 * @author shengguo.hu 2011-8-17 下午04:30:21
 */
public class FileUtil20140625 {

	public static final Logger logger = Logger.getLogger(FileUtil20140625.class);

	private static class ImageModel {
		private BufferedImage bufferedImage;
		private boolean isSpecialImage;

		public ImageModel(BufferedImage bufferedImage, boolean isSpecialImage) {
			this.bufferedImage = bufferedImage;
			this.isSpecialImage = isSpecialImage;
		}

		public BufferedImage getBufferedImage() {
			return bufferedImage;
		}

		public void setBufferedImage(BufferedImage bufferedImage) {
			this.bufferedImage = bufferedImage;
		}

		public boolean isSpecialImage() {
			return isSpecialImage;
		}

		public void setSpecialImage(boolean isSpecialImage) {
			this.isSpecialImage = isSpecialImage;
		}
	}

	/**
	 * 等比缩放 lv: 3.5
	 */
	public static File resizeByScale(String sourcePath, String targetPath, float lv, String siteUrl, String companyName, boolean fromNet, boolean isReplace) {
		// 处理并发
		if (!isReplace) {
			File old = new File(targetPath + "-" + lv + ".jpg");
			if (old.exists()) {
				return old;
			}
		}

		try {
			ImageModel model = getBufferedImageFromFile(sourcePath, fromNet);
			BufferedImage source = model.getBufferedImage();

			String suffix = "";
			if (lv != 0) {
				suffix = "-scale" + String.valueOf(lv).replace(".", "_") + ".jpg";
			}

			return resizeToSmall(sourcePath, targetPath, source, lv, siteUrl, companyName, suffix, model.isSpecialImage());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 指定规格,等比生成 rule: 168-168
	 */
	public static File resizeByRule(String sourcePath, String targetPath, String rule, String siteUrl, String companyName, boolean fromNet, boolean isReplace) {
		// 处理并发
		if (!isReplace) {
			File old = new File(targetPath + "-" + rule + ".jpg");
			if (old.exists()) {
				return old;
			}
		}

		ImageModel model = getBufferedImageFromFile(sourcePath, fromNet);
		BufferedImage source = model.getBufferedImage();
		if (source == null) {
			return null;
		}

		String[] rules = rule.split("-");

		float w = Float.parseFloat(rules[0]);
		float h = Float.parseFloat(rules[1]);

		float lv;
		if (source.getWidth() / w < source.getHeight() / h) {
			lv = source.getHeight() / (float) h;
		} else {
			lv = source.getWidth() / (float) w;
		}

		return resizeToSmall(sourcePath, targetPath, source, lv, siteUrl, companyName, "-" + rule + ".jpg", model.isSpecialImage());
	}

	/**
	 * 取宽高中较大的,图片完整显示,等比生成 size: 950
	 */
	public static File resizeBySize(String sourcePath, String targetPath, int size, String siteUrl, String companyName, boolean fromNet, boolean isReplace) {
		// 处理并发
		if (!isReplace) {
			File old = new File(targetPath + "-" + size + ".jpg");
			if (old.exists()) {
				return old;
			}
		}

		ImageModel model = getBufferedImageFromFile(sourcePath, fromNet);
		BufferedImage source = model.getBufferedImage();
		if (source == null) {
			return null;
		}

		float lv;
		if (source.getWidth() < source.getHeight()) {
			lv = source.getHeight() / (float) size;
		} else {
			lv = source.getWidth() / (float) size;
		}

		return resizeToSmall(sourcePath, targetPath, source, lv, siteUrl, companyName, "-size" + size + ".jpg", model.isSpecialImage());
	}

	/**
	 * 取图片左上部分像素 rule: cut168-168
	 */
	public static File resizeByCut(String sourcePath, String targetPath, String rule, String siteUrl, String companyName, boolean fromNet, boolean isReplace) {
		// 处理并发
		if (!isReplace) {
			File old = new File(targetPath + "-" + rule + ".jpg");
			if (old.exists()) {
				return old;
			}
		}

		ImageModel model = getBufferedImageFromFile(sourcePath, fromNet);
		BufferedImage source = model.getBufferedImage();
		if (source == null) {
			return null;
		}

		String[] rules = rule.replace("cut", "").split("-");
		float wlv = source.getWidth() / Float.parseFloat(rules[0]);
		float hlv = source.getHeight() / Float.parseFloat(rules[1]);

		float lv = wlv < hlv ? wlv : hlv;
		// 大则不变,小则放大
		resizeToLarge(sourcePath, targetPath, source, lv, null, null, "-" + rule + ".jpg", model.isSpecialImage());
		// 截取
		ImageModel model2 = getBufferedImageFromFile(targetPath + "-" + rule + ".jpg", fromNet);
		BufferedImage t = model2.getBufferedImage();
		t = t.getSubimage(0, 0, Integer.parseInt(rules[0]), Integer.parseInt(rules[1]));

		return resizeToSmall(sourcePath, targetPath, t, 1, siteUrl, companyName, "-" + rule + ".jpg", model2.isSpecialImage());
	}

	private static File resizeToSmall(String sourcePath, String targetPath, BufferedImage source, float lv, String siteUrl, String companyName, String suffix, boolean isSpecialImage) {
		if (source == null) {
			return null;
		}
		lv = lv < 1 ? 1 : lv;
		int targetW = (int) Math.floor(source.getWidth() / lv);
		int targetH = (int) Math.floor(source.getHeight() / lv);
		return resize(sourcePath, targetPath, source, lv, targetW, targetH, siteUrl, companyName, suffix, isSpecialImage);
	}

	private static File resizeToLarge(String sourcePath, String targetPath, BufferedImage source, float lv, String siteUrl, String companyName, String suffix, boolean isSpecialImage) {
		if (source == null) {
			return null;
		}
		lv = lv > 1 ? 1 : lv;
		int targetW = (int) Math.round(source.getWidth() / lv);
		int targetH = (int) Math.round(source.getHeight() / lv);
		return resize(sourcePath, targetPath, source, lv, targetW, targetH, siteUrl, companyName, suffix, isSpecialImage);
	}

	/**
	 * 新的图片处理方式:解决图片失真问题,解决图片文字锯齿问题
	 * 
	 * @param targetPath
	 * @param source
	 * @param lv
	 * @param siteUrl
	 * @param companyName
	 * @param suffix
	 * @return
	 */
	private static File resize(String sourcePath, String targetPath, BufferedImage source, float lv, int targetW, int targetH, String siteUrl, String companyName, String suffix, boolean isSpecialImage) {
		// 解决图片无法读取的问题,原因为图片类型不一致
		BufferedImage bfImage = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bfImage.getGraphics();

		// 增加下面的代码使得背景透明
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, targetW, targetH);

		// 图像
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		double bili = 1d / lv;
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(bili, bili));

		// 文字
		int defaultFontSize = 1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (StringUtil.isNotBlank(companyName)) {
			g.setColor(new Color(182, 182, 182));// #b6b6b6
			int fontSize = (int) (Math.floor(targetW / (float) companyName.length()) * 0.8);
			fontSize = fontSize > 32 ? 32 : (fontSize < defaultFontSize ? defaultFontSize : fontSize);
			g.setFont(new Font("宋体", Font.BOLD, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g.drawString(companyName, (targetW - (getLength(companyName)) * fontSize) / 2, (targetH - fontSize) / 2 + fontSize);
		}
		if (StringUtil.isNotBlank(siteUrl)) {
			g.setColor(new Color(182, 182, 182));// #b6b6b6
			int fontSize = (int) (Math.floor(targetW / (float) siteUrl.length()) * 0.8);
			fontSize = fontSize > 18 ? 18 : (fontSize < defaultFontSize ? defaultFontSize : fontSize);
			g.setFont(new Font("宋体", Font.BOLD, fontSize));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g.drawString(siteUrl, targetW - (getLength(siteUrl) + 4) * fontSize - 15, targetH - fontSize * 2);
		}
		g.dispose();
		FileOutputStream os = null;
		try {
			File targetDir;
			if (targetPath.lastIndexOf("/") != -1) {
				targetDir = new File(targetPath.substring(0, targetPath.lastIndexOf("/")));
			} else {
				targetDir = new File(targetPath.substring(0, targetPath.lastIndexOf("\\")));
			}
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}

			// 生成
			os = new FileOutputStream(targetPath + suffix);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bfImage);

			long length = new File(sourcePath).length();
			if (length > 4 * 1024 * 1024) {
				param.setQuality(0.3f, false);
			} else if (length > 3 * 1024 * 1024) {
				param.setQuality(0.4f, false);
			} else if (length > 2 * 1024 * 1024) {
				param.setQuality(0.5f, false);
			} else if (length > 1 * 1024 * 1024) {
				param.setQuality(0.6f, false);
			} else if (length > 512 * 1024) {
				param.setQuality(0.7f, false);
			} else {
				param.setQuality(0.95f, false);// 大小减半
			}

			encoder.encode(bfImage, param);

			File targetFile = new File(targetPath + suffix);

			if (isSpecialImage) {
				ImageIO.write(inverse(bfImage), "JPG", targetFile);
			}
			return targetFile;
		} catch (Exception e) {
			logger.error(e);
			return null;
		} finally {
			IOUtils.closeQuietly(os);
		}
	}

	// 图片反色处理
	private static BufferedImage inverse(BufferedImage image) {
		BufferedImage createImage = null;
		if (image != null) {
			int width = 0;
			int height = 0;
			width = image.getWidth();
			height = image.getHeight();
			int imageRGB[] = new int[width * height];
			image.getRGB(0, 0, width, height, imageRGB, 0, width);
			createImage = new BufferedImage(width, height, image.getType());
			for (int i = 0; i < imageRGB.length; i++) {
				imageRGB[i] = imageRGB[i] ^ 0xffffffff;
			}
			createImage.setRGB(0, 0, width, height, imageRGB, 0, width);
			imageRGB = null;
		}
		return createImage;
	}

	private static ImageModel getBufferedImageFromFile(String sourcePath, boolean fromNet) {
		BufferedImage source = null;
		boolean isSpecial = false;
		if (fromNet) {
			DataInputStream in = null;
			HttpURLConnection connection = null;
			try {
				URL url = new URL(sourcePath);
				connection = (HttpURLConnection) url.openConnection();
				in = new DataInputStream(connection.getInputStream());
				source = ImageIO.read(in);
			} catch (Exception ex) {
				// log.error("can not read source image file: " + sourcePath);
			} finally {
				connection.disconnect();
				IOUtils.closeQuietly(in);
			}
		} else {
			File f = new File(sourcePath);
			try {
				source = ImageIO.read(f);
			} catch (Exception e) {
				// 部分图片模式无法读取
				FileInputStream in = null;
				BufferedOutputStream out = null;
				try {
					in = new FileInputStream(sourcePath);
					JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
					source = decoder.decodeAsBufferedImage();
					// 当type为1时无需反色
					if (source.getType() != 1) {
						isSpecial = true;
					}
				} catch (Exception ex) {
					// log.error("can not read source image file: " +
					// sourcePath);
				} finally {
					IOUtils.closeQuietly(in);
					IOUtils.closeQuietly(out);
				}
			}
		}
		return new ImageModel(source, isSpecial);
	}

	/**
	 * 删除文件，可以是文件或文件夹
	 * 
	 * @param fileName
	 *            要删除的文件名
	 * @return 删除成功返回true，否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			return false;
		} else {
			if (file.isFile()) {
				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 删除目录及目录下的文件
	 * 
	 * @param dir
	 *            要删除的目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			return false;
		}
		boolean flag = true;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = FileUtil20140625.deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} else if (files[i].isDirectory()) {
				flag = FileUtil20140625.deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			return false;
		}
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteFilesWithSuffix(String filePath, String[] rules) {
		deleteFile(filePath);
		if (rules != null) {
			for (String suffix : rules) {
				deleteFile(filePath + "_" + suffix + ".jpg");
			}
		}
		return true;
	}

	private static int getLength(String text) {
		int length = 0;
		for (int i = 0; i < text.length(); i++) {
			if (new String(text.charAt(i) + "").getBytes().length > 1) {
				length += 2;
			} else {
				length += 1;
			}
		}
		return length / 2;
	}

}
