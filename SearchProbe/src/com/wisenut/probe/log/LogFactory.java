package com.wisenut.probe.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * <pre>
 * com.wisenut.probe.log
 *   |_ LogFactory.java
 * </pre>
 *
 * SearchProbe 어플리케이션용 Logger를 반환하는 Factory
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 19. 오후 6:03:08
 * @Version : 
 */
public class LogFactory {

	/**
	 * Logger 반환
	 * @Method Name : getLogger
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		Logger logger = Logger.getLogger(clazz);
		
		try {
			// 로그 레이아웃 설정
			String layout = "[%d{yyyy-MM-dd HH:mm:ss}] [%c{1}] [%L] [%p] %m %n";
			PatternLayout patternLayout = new PatternLayout(layout);

			// 파일명 포맷 설정
			String baseDirectory = System.getProperty("probe_home") + File.separator + "log" + File.separator;
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
			String filename = baseDirectory + "SearchProbe-" + formatter.format(new Date()) + ".log";

			// 날짜 패턴 설정
			String datePattern = "'.'yyyy-MM-dd";
			
			
			DailyRollingFileAppender appender;
			appender = new DailyRollingFileAppender(patternLayout, filename, datePattern);
			logger.addAppender(appender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return logger;
	}
}
