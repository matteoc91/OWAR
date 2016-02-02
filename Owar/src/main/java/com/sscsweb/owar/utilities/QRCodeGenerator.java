package com.sscsweb.owar.utilities;

import java.io.ByteArrayOutputStream;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

public class QRCodeGenerator {

	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	
	public static ByteArrayOutputStream generate(String text) {
		return QRCode.from(text).to(ImageType.PNG).withSize(WIDTH, HEIGHT).stream();
	}
	
}
