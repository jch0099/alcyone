package com.xushi.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成工具类
 * @author root
 *
 */
public class ZxingQrcode {

	/**
	 * 快速生成
	 * @param content 内容
	 * @param outsrc 输出路径　使用jpg
	 * @throws Exception
	 */
	public static void encoderQRCode(String content, String outsrc) throws Exception {
		encoderQRCode(content, outsrc, "jpg", 300, 300, 0, 0, 2, null);
	}
	
	/**
	 * 完整参数生成二维码
	 * @param content 生成二维码内容
	 * @param outsrc　输入图片路径
	 * @param format　图片格式 png,jpg,gif
	 * @param width 图片宽
	 * @param height 图片高
	 * @param onColor 字体颜色
	 * @param offColor　背景色
	 * @param level 0 = M ~15%, 1 = L ~7%, 2 = H ~30% 3 = Q ~25%
	 * @param charset 字符集，需要小写
	 * @throws Exception
	 */
	public static void encoderQRCode(String content, String outsrc, String format, int width, int height, int onColor, int offColor, int level, String charset) throws Exception {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		if (charset == null || charset.trim().length() == 0) {
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		}
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.forBits(level));
		hints.put(EncodeHintType.MARGIN, 1);
		
		MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
		if (onColor == 0 && offColor == 0) {
			config = new MatrixToImageConfig();
		}
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		File outFile = new File(outsrc);
		outFile.mkdirs();
		MatrixToImageWriter.writeToFile(bitMatrix, format, new File(outsrc), config);
	}
	
	/**
	 * 快速生成
	 * @param content 内容
	 * @throws Exception
	 */
	public static byte[] encoderQRCode(String content) throws Exception {
		return encoderQRCode(content, "jpg", 300, 300, 0, 0, 2, null);
	}
	
	/**
	 * 完整参数生成二维码
	 * @param content 生成二维码内容
	 * @param format　图片格式 png,jpg,gif
	 * @param width 图片宽
	 * @param height 图片高
	 * @param onColor 字体颜色
	 * @param offColor　背景色
	 * @param level 0 = M ~15%, 1 = L ~7%, 2 = H ~30% 3 = Q ~25%
	 * @param charset 字符集，需要小写
	 * @throws Exception
	 */
	public static byte[] encoderQRCode(String content, String format, int width, int height, int onColor, int offColor, int level, String charset) throws Exception {
		Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		if (charset == null || charset.trim().length() == 0) {
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		}
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.forBits(level));
		hints.put(EncodeHintType.MARGIN, 1);
		
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix);
		ByteArrayOutputStream os=new ByteArrayOutputStream();//新建流。
		ImageIO.write(bi, "jpg", os);
		return os.toByteArray();
	}
	
	/**
	 * 解析二维码
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		try {
			MultiFormatReader formatReader = new MultiFormatReader();
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map hints = new Hashtable();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			Result result = formatReader.decode(binaryBitmap,hints);
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析二维码
	 * @throws Exception
	 */
	public static String decode(InputStream is) throws Exception {
		MultiFormatReader formatReader = new MultiFormatReader();
		BufferedImage image = ImageIO.read(is);
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		Result result = formatReader.decode(binaryBitmap,hints);
		return result.toString();
	}
	
	public static void main(String [] args) throws Exception {
		ZxingQrcode.decode("D:/work/workspace/xushi/webapp/qrcode"+"/92ccefc3-2129-41b3-bf2a-e561c364b2e5.jpg");
	}
}
