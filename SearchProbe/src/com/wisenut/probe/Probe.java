package com.wisenut.probe;

import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.log4j.Logger;

import com.wisenut.probe.argument.RuntimeArgument;
import com.wisenut.probe.argument.RuntimeArgumentFactory;
import com.wisenut.probe.config.ProbeConfig;
import com.wisenut.probe.config.ProbeConfigLoader;
import com.wisenut.probe.exception.CollectionNotFoundException;
import com.wisenut.probe.exception.NoneRuntimeArgumentException;
import com.wisenut.probe.exception.NotAllowedLocationException;
import com.wisenut.probe.log.LogFactory;

public class Probe {
	private static Logger logger = LogFactory.getLogger(Probe.class);

	public static void main(String[] args) {
		logger.info("Start Search-Probe to voluntariness generate query-log.");

		try {
			// 실행 인자를 분석하여 RuntimeArgument 객체를 얻음.
			RuntimeArgument runtimeArgument = RuntimeArgumentFactory.runtimeArgument(args);
			
			// 설정파일을 로드하여 설정파일 객체를 얻음.
			ProbeConfig probeConfig = ProbeConfigLoader.getConfig(runtimeArgument.getConfigPath());
			
			// Location 설정에 따라 쿼리로그 직접 생성 및 검색을 통한 생성 작업을 실행
			ExecutorService executorService = Executors.newCachedThreadPool();
			Hashtable<String,Long> keywordTable = new Hashtable<String, Long>();
		} catch(NoneRuntimeArgumentException nrae) {
			logger.error("실행 인자가 존재하지 않음. 실행 인자를 확인하십시오.", nrae);
			applicationTerminate(nrae);
		} catch (NotAllowedLocationException nale) {
			logger.error("허용되지 않은 인자값 입니다. (-location 'local' or 'remote')", nale);
			applicationTerminate(nale);
		} catch (CollectionNotFoundException cnfe) {
			logger.error("컬렉션 설정 정보를 찾을 수 없습니다. SF-1 설정파일을 점검하십시오.", cnfe);
			applicationTerminate(cnfe);
		}

		/*

		
		KeywordLoader keywordLoader = new KeywordLoader(keywordTable);
		keywordLoader.start();

		try {
			Enumeration<String> enu = keywordTable.keys();
			while(enu.hasMoreElements()) {
				Searcher searcher = new Searcher(enu.nextElement(), keywordTable, config);
				executorService.execute(searcher);
				Thread.sleep(50);
			}
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			try {
				keywordLoader.shutdownRequest();
				
				while(keywordLoader.isAlive()) {
					Thread.sleep(500);
				}
				System.out.println("[AgentMain] Search agent application is terminate.");
			} catch(InterruptedException ie) {
			}
		}
		*/
		
		logger.info("Finish Search-Probe...");
	}
	
	protected static void applicationFinish() {
		logger.info("작업이 완료되었습니다. Search-Probe를 종료합니다.");
		System.exit(0);
	}
	
	protected static void applicationTerminate() {
		logger.error("실행중 문제가 발생하여 Search-Probe를 종료합니다.");
		System.exit(-1);
	}
	
	protected static void applicationTerminate(Exception e) {
		logger.error("실행중 문제가 발생하여 Search-Probe를 종료합니다.\r\n"
				+ "Message : " + e.getMessage() + "\r\n"
				+ "Cause : " + e.getCause() + "\r\n");
		System.exit(-1);
	}

}
