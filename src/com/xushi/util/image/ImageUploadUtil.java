package com.xushi.util.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * 圖片上傳的工具類
 * 
 * @author chemphone
 * */
public class ImageUploadUtil {
	static  String imagename;
	static  int width;
	static  int height;
	
	/**
	 * 獲取保存的圖片名
	 * **/
	public static String getImagename() {
		return imagename;
	}

	/**
	 * 圖片裁剪
	 * **/
	public static void imgCut(String savePath,MultipartFile multipartFile, int x, int y, int desWidth,
            int desHeight) {
		try {
					Image img;
					ImageFilter cropFilter;
					BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
					int width = bi.getWidth();
					int height = bi.getHeight();
					if(width>300){
						System.out.println(width);
						double b = width/300.0;
						width = 300;
						height = (int)(height/b);
					}
					if(width<300 && height>360){
						double b = height/360;
						width = (int)(width/b);
						height = 360;
					}
					System.out.println("w:" +width +",h:" + height);
					if (width >= desWidth && height >= desHeight) {
						Image image = bi.getScaledInstance(width, height,Image.SCALE_DEFAULT);
						cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
						img = Toolkit.getDefaultToolkit().createImage(
								new FilteredImageSource(image.getSource(), cropFilter));
						BufferedImage tag = new BufferedImage(desWidth, desHeight,
								BufferedImage.TYPE_INT_RGB);
						Graphics g = tag.getGraphics();
						g.drawImage(img, 0, 0, null);
						g.dispose();
						String ext = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."))
								.toLowerCase();
		                imagename = UUID.randomUUID().toString() + ext;
						//输出文件
						String filename =savePath +File.separator  + imagename;
						
						ImageIO.write(tag, "JPEG", new File(filename));
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 圖片保存
	 * **/
	public static void saveimg(String savePath, MultipartFile multipartFile) throws Exception{
		// TODO Auto-generated method stub
		BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
		width = bi.getWidth();
		height=bi.getHeight();
		imagename = multipartFile.getOriginalFilename();
		String imageprefix=imagename.substring(imagename.lastIndexOf(".")+1);
		imagename = UUID.randomUUID().toString() + "."+imageprefix;
		String filename =savePath +File.separator  + imagename;
		ImageIO.write(bi, "JPEG", new File(filename));
	}

	/**
	 * 得到圖片保存寬度
	 * **/
	public static int getWidth() {
		return width;
	}

	/**
	 * 得到圖片保存長度
	 * **/
	public static int getHeight() {
		return height;
	}


}
