package com.wisenut.probe.config;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * com.wisenut.probe.config
 *   |_ ProbeConfig.java
 * </pre>
 *
 * Search-Probe 어플리케이션의 설정 저장 객체
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 20. 오전 10:49:10
 * @Version : 
 */
public class ProbeConfig {
	private String host = "127.0.0.1";
	private int port = 20020;
	private int timeout = 1000;
	private int runningTime = 10;
	private String keywordsPath;
	private String sf1Config;
	
	private Map<String, CollectionInfo> collectionInfo = new HashMap<String, CollectionInfo>();

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return the runningTime
	 */
	public int getRunningTime() {
		return runningTime;
	}

	/**
	 * @param runningTime the runningTime to set
	 */
	public void setRunningTime(int runningTime) {
		this.runningTime = runningTime;
	}
	
	/**
	 * 
	 * @Method Name : getKeywordsPath
	 * @return
	 */
	public String getKeywordsPath() {
		return keywordsPath;
	}
	
	/**
	 * 
	 * @Method Name : setKeywordsPath
	 * @param keywordsPath
	 */
	public void setKeywordsPath(String keywordsPath) {
		this.keywordsPath = keywordsPath;
	}

	/**
	 * @return the sf1Config
	 */
	public String getSf1Config() {
		return sf1Config;
	}

	/**
	 * @param sf1Config the sf1Config to set
	 */
	public void setSf1Config(String sf1Config) {
		this.sf1Config = sf1Config;
	}
	
	/**
	 * 컬렉션 설정 정보 Map객체 반환
	 * @Method Name : getCollectionInfoMap
	 * @return
	 */
	public Map<String, ? extends Object> getCollectionInfoMap() {
		return this.collectionInfo;
	}
	
	/**
	 * CollectionInfo 객체를 CollectionInfo Map에 추가
	 * @Method Name : addCollectionInfo
	 * @param collectionInfo
	 */
	public void addCollectionInfo(CollectionInfo collectionInfo) {
		this.collectionInfo.put(collectionInfo.getId(), collectionInfo);
	}

	public String toString() {
		return "[Host]" + this.host + "/ [Port]" + this.port + "/ [Timeout]" + this.timeout + "/ [RunningTime]" + this.runningTime
				+ "/ [SF1ConfigPath]" + this.sf1Config;
	}
}
