package com.xushi.util.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * 图片处理工具 
 */
public class ImageUtil {
	static final Log _Log = LogFactory.getLog(ImageUtil.class);

	private BufferedImage bufferedImage;
	private String fileName;
	/** 图片格式名 */
	private String formatName;
	/**图片大小**/
	private long fileSize;
	/**图片保存路径**/
	private String savePath; 
	/**原图片保存路径**/
	private String oriPath; 
	/**要处理的宽度**/
	private int pwidth;
	/**要处理的高度**/
	private int pheight;
	/**水印图片**/
	private String waterMarkPath;
	/**默认是按照比例缩放的 **/
	private boolean isKeepAspectRatio = true;
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFormatName() {
		return formatName;
	}

	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getPwidth() {
		return pwidth;
	}

	public void setPwidth(int pwidth) {
		this.pwidth = pwidth;
	}

	public int getPheight() {
		return pheight;
	}

	public void setPheight(int pheight) {
		this.pheight = pheight;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getOriPath() {
		return oriPath;
	}

	public void setOriPath(String oriPath) {
		this.oriPath = oriPath;
	}

	public String getWaterMarkPath() {
		return waterMarkPath;
	}

	public void setWaterMarkPath(String waterMarkPath) {
		this.waterMarkPath = waterMarkPath;
	}

	public boolean isKeepAspectRatio() {
		return isKeepAspectRatio;
	}

	public void setKeepAspectRatio(boolean isKeepAspectRatio) {
		this.isKeepAspectRatio = isKeepAspectRatio;
	}

	/*****
	 * 将普通文件流变成 BufferedImage方式
	 * @param imageFile
	 * @throws IOException
	 */
	public ImageUtil(File imageFile) throws IOException {
		//this.savePath = savepath;
		ImageInputStream in = ImageIO.createImageInputStream(imageFile);
		if (null != in) {
			Iterator<ImageReader> it = ImageIO.getImageReaders(in);
			if (it.hasNext()) {
				ImageReader reader = it.next();
				// 取得图片格式名
				formatName = reader.getFormatName();
				// 读取图片为bufferedImage
				ImageReadParam imagereadparam = reader.getDefaultReadParam();
				reader.setInput(in, true, true);
				bufferedImage = reader.read(0, imagereadparam);
				reader.dispose();
				in.close();
			}
		}
		fileName = imageFile.getAbsolutePath();
	}
	
	/****
	 * 直接将图片变成BufferedImage
	 */
	public ImageUtil(String oripath) throws IOException {
		this.oriPath = oripath;
		this.bufferedImage = Thumbnails.of(oripath).asBufferedImage();  
	}
	
	/**
	 * 普通的缩放图片
	 * @throws IOException 
	 * 方法名: resize <br />  
	 * 描述:使用thumbnailator.jar <br /> 
	 * 参数：<br />  
	 * @return void <br />    
	 * @throws
	 */
	public void resize() throws IOException {
		int nWidth = bufferedImage.getWidth(); //原图片宽
		int nHeight = bufferedImage.getHeight(); //原图片高
		if(nWidth>pwidth || nHeight>pheight){
			 Thumbnails.of(this.bufferedImage)
			           .size(pwidth, pheight)
			           .keepAspectRatio(this.isKeepAspectRatio)
			           .outputQuality(1.0f)
			           .toFile(savePath);
		} /*else {
			 Thumbnails.of(bufferedImage)
			    .size(nWidth, nHeight)
			    .toFile(savePath);			
		}*/
	}
	
	/*****
	 * 处理水印图片
	 */
	public void resizeAndWaterMark() throws IOException {
		int nWidth = bufferedImage.getWidth(); //原图片宽
		int nHeight = bufferedImage.getHeight(); //原图片高
		if(nWidth>pwidth || nHeight>pheight){
			 Thumbnails.of(bufferedImage)
			           .size(pwidth, pheight)
			           .keepAspectRatio(this.isKeepAspectRatio)
			           //watermark(位置，水印图，透明度) 
			           .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(this.waterMarkPath)), 0.5f)   
			           .outputQuality(1.0f)
			           .toFile(savePath);
		}else{
			 Thumbnails.of(bufferedImage)
			    .size(nWidth, nHeight)
	            .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(this.waterMarkPath)), 0.5f)   
	            .outputQuality(1.0f)
	            .toFile(savePath);
		}
	}
	
//	Thumbnails.of("images/a380_1280x1024.jpg")   
//    .size(1280, 1024)  
//    .outputFormat("png")   
//    .toFile("c:/a380_1280x1024.png");   

	
	public static void main(String[] args) {
		File imageFile = new File("d:/bb.jpg");
		try {
			ImageUtil test = new ImageUtil(imageFile);
			//test.setOriPath("d:/bb.jpg");
			test.setSavePath("d:/bb_l.jpg");
			test.setWaterMarkPath("D:/common/各类图库/P200907221517581176030293.png");
			test.setPheight(801);
			test.setPwidth(801);
			test.resize();
			test.resizeAndWaterMark();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
