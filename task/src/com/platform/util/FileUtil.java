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

public class FileUtil {

	public static final Logger log = Logger.getLogger(FileUtil.class);

	// for batch
	public static File createImageByRule(String sourceUrl, String rule, String companyName, String siteUrl) {
		String[] rules = rule.substring(1, rule.indexOf(".")).split("-");
		int targetW = Integer.parseInt(rules[0]);
		int targetH = Integer.parseInt(rules[1]);

		ImageModel model = getBufferedImageFromFile(sourceUrl, false);
		BufferedImage source = model.getBufferedImage();

		float lv;
		float ww = source.getWidth() / (float) targetW;
		float hh = source.getHeight() / (float) targetH;
		if (ww < hh) {
			lv = hh;
		} else {
			lv = ww;
		}

		double bili = 1d / lv;

		targetW = (int) Math.floor(source.getWidth() / lv);
		targetH = (int) Math.floor(source.getHeight() / lv);

		BufferedImage bfImage = new BufferedImage(targetW, targetH, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bfImage.getGraphics();

		// 增加下面的代码使得背景透明
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, targetW, targetH);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
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
		FileOutputStream out = null;
		try {
			// 生成
			out = new FileOutputStream(sourceUrl);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bfImage);

			param.setQuality(100f, false);

			encoder.encode(bfImage, param);

			File targetFile = new File(sourceUrl + rule);

			if (model.isSpecialImage) {
				ImageIO.write(inverse(bfImage), "JPG", targetFile);
			} else {
				ImageIO.write(bfImage, "JPG", targetFile);
			}

			return targetFile;
		} catch (Exception e) {
			log.error(e);
			return null;
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

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
