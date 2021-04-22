package com.kh.aboo.common.util.qrcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.kh.aboo.common.code.ConfigCode;

public class QRCodeUtil {

	public void makeQR(String url, String title) {
		QRCodeWriter writer = new QRCodeWriter();
		try {
			BitMatrix bm = writer.encode(url, BarcodeFormat.QR_CODE, 300, 300);
			// qr 경로에 저장한다.
			String filePath = ConfigCode.QRCODE_PATH.desc;
			File path = new File(filePath);
			if(!path.exists()) {
				path.mkdirs();
			}
			MatrixToImageWriter.writeToStream(bm, "jpg", new FileOutputStream(filePath + title + ".jpg"));
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
