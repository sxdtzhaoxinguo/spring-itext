/**
 * Created by zhaoxinguo on 2016年11月17日 下午4:35:01.
 */
package com.micai.itext.util;

import com.itextpdf.awt.geom.Rectangle2D.Float;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by zhaoxinguo on 2016年11月17日 下午4:35:01.
 */
public class MicaiItextUtils {

	// 定义关键字
	private static String KEY_WORD = "甲";
	// 定义返回值
	private static float[] resu = null;
	// 定义返回页码
	private static int i = 0;
	
	/*
     * 返回关键字所在的坐标和页数 float[0] >> X float[1] >> Y float[2] >> page
     */
	public static float[] getKeyWords(String filePath) {
		try {
			PdfReader pdfReader = new PdfReader(filePath);
			int pageNum = pdfReader.getNumberOfPages();
			PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
			// 下标从1开始
			for (i = 1; i <= pageNum; i++) {
				pdfReaderContentParser.processContent(i, new RenderListener() {
					public void renderText(TextRenderInfo textRenderInfo) {
						String text = textRenderInfo.getText();
						System.out.println(i + ":" +text);
						if (null != text && text.contains(KEY_WORD)) {
							Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
							resu = new float[3];
							resu[0] = boundingRectange.x;
							resu[1] = boundingRectange.y;
							resu[2] = i;
						}
					}
					public void renderImage(ImageRenderInfo arg0) {
						
					}
					public void endTextBlock() {
						
					}
					public void beginTextBlock() {
						
					}
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resu;
	}
	
	/**
     * 
     * 【功能描述：添加图片和文字水印】 
     * @param srcFile 待加水印文件
     * @param destFile 加水印后存放地址
     * @param markImagePath 水印图片路径
     * @param textWidth 文字横坐标
     * @param textHeight 文字纵坐标
     * @param pageSize 添加水印关键字所在的页数
     */
	public static void addWaterMark(String srcFile, String destFile, String markImagePath, 
			float textWidth, float textHeight, float pageSize){
		try {
			// 待加水印的文件
			PdfReader reader = new PdfReader(srcFile);
			// 加完水印的文件
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(destFile));
			// 插入水印
			Image img = Image.getInstance(markImagePath);
			img.setAbsolutePosition(textWidth, textHeight);
			PdfContentByte under = stamper.getUnderContent((int)pageSize);  
			under.addImage(img);
			stamper.close();// 关闭   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		String filePath = "E:\\tmp\\fadada\\download\\个体劳动合同范本.pdf";
		String destPath = "E:\\tmp\\fadada\\download\\个体劳动合同范本_new.pdf";
		float[] words = MicaiItextUtils.getKeyWords(filePath);
		System.out.println("横坐标：" + words[0] + "纵坐标："+ words[1] +"所在页码："+ words[2]);
		String markImagePath = "E:\\tmp\\fadada\\download\\logo.png";
		MicaiItextUtils.addWaterMark(filePath, destPath, markImagePath, words[0], words[1], words[2]);
		System.out.println("添加水印成功");
	}


	
}
