package com.wisenut.probe.argument;

import com.wisenut.probe.argument.model.Location;
import com.wisenut.probe.exception.NotAllowedLocationException;

/**
 * <pre>
 * com.wisenut.probe.argument
 *   |_ RuntimeArgument.java
 * </pre>
 *
 * 어플리케이션 실행 인자값을 가지는 모델 클래스
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 19. 오후 2:41:26
 * @Version : 
 */
public class RuntimeArgument {
	private String configPath;
	private Location location = Location.LOCAL;

	/**
	 * 설정파일 경로를 반환
	 * @return the configPath
	 */
	public String getConfigPath() {
		return configPath;
	}

	/**
	 * 설정파일 경로를 설정
	 * @param configPath the configPath to set
	 */
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
	
	/**
	 * 쿼리로그 생성 타입 반환
	 * @Method Name : getLocation
	 * @return
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * 쿼리로그 생성 타입 설정
	 * @Method Name : setLocation
	 * @param location
	 * @throws NotAllowedLocationException 
	 */
	public void setLocation(Location location) throws NotAllowedLocationException {
		if(!location.equals(Location.LOCAL) && !location.equals(Location.REMOTE)) {
			throw new NotAllowedLocationException();
		}
		this.location = location;
	}
}
