package com.ts.commons.videoRecorder;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

public class RecordExt extends ScreenRecorder{		
	private String fileName = null;
	private static GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();	
	private static Format fileFormat = new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey,MIME_AVI);
	private static Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),QualityKey, 1.0f,KeyFrameIntervalKey, (int) (15 * 60));
	private static Format mouseFormat = new Format(MediaTypeKey, MediaType.VIDEO,EncodingKey,"black",FrameRateKey, Rational.valueOf(30));
	private static Rectangle captureArea = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	private static Format audioFormat = null;
	
	public RecordExt(String fileName) throws IOException, AWTException {
		super(gc, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, new File("videos"));
		this.fileName = fileName;
	}		
	
	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {
		 if ( ! movieFolder.exists()) {
			 movieFolder.mkdirs();
		 } else if ( ! movieFolder.isDirectory()) {
			 throw new IOException("\"" + movieFolder + "\" is not a directory.");
		 }	
		 return new File(movieFolder, fileName + "." + Registry.getInstance().getExtension(fileFormat));
	}
}