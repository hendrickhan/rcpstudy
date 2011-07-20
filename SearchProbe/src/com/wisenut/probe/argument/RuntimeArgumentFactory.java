package com.wisenut.probe.argument;

import org.apache.log4j.Logger;

import com.wisenut.probe.argument.model.Location;
import com.wisenut.probe.exception.NoneRuntimeArgumentException;
import com.wisenut.probe.exception.NotAllowedLocationException;
import com.wisenut.probe.log.LogFactory;

/**
 * <pre>
 * com.wisenut.probe.argument
 *   |_ RuntimeArgumentFactory.java
 * </pre>
 *
 * 실행 인자 정보 클래스인 RuntimeArgument를 생성/설정하고 이를 반환한다.
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 19. 오후 6:11:17
 * @Version : 
 */
public class RuntimeArgumentFactory {
	private static Logger logger = LogFactory.getLogger(RuntimeArgumentFactory.class);

	/**
	 * 실행 인자 정보 클래스인 RuntimeArgument 반환
	 * @Method Name : runtimeArgument
	 * @param args
	 * @return
	 * @throws NoneRuntimeArgumentException 
	 * @throws NotAllowedLocationException 
	 */
	public static RuntimeArgument runtimeArgument(String[] args) throws NoneRuntimeArgumentException, NotAllowedLocationException {
		return analyzeArgument(args);
	}

	/**
	 * 실행 인자를 분석하여 RuntimeArgument 생성.<br/>
	 * 실행 인자가 없는 경우 NoneArgumentException 발생.
	 * @Method Name : analyzeArgument
	 * @param args
	 * @return
	 * @throws NoneRuntimeArgumentException 
	 * @throws NotAllowedLocationException 
	 */
	private static RuntimeArgument analyzeArgument(String[] args) throws NoneRuntimeArgumentException, NotAllowedLocationException {
		RuntimeArgument argument = new RuntimeArgument();

		if (args == null || args.length == 0) {
			logger.error("실행 인자 값이 존재하지 않습니다. 실행 인자를 확인 하십시오.");
			throw new NoneRuntimeArgumentException();
		} else {
			for (int idx = 0; idx < args.length; idx++) {
				if (args[idx].startsWith("-")) {
					if ("-conf".equals(args[idx])) {
						// 설정파일 위치 설정인 경우
						argument.setConfigPath(args[idx + 1]);
					} else if ("-location".equals(args[idx])) {
						// 쿼리로그 생성 방법 설정인 경우
						argument.setLocation(Location.valueOf(args[idx+1]));
					}
				}
			}
		}

		return argument;
	}
}
