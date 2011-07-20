package com.wisenut.probe.config;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.wisenut.probe.exception.CollectionNotFoundException;
import com.wisenut.probe.log.LogFactory;

public class ProbeConfigLoader {
	private static Logger logger = LogFactory.getLogger(ProbeConfigLoader.class);
	
	public static ProbeConfig getConfig(String configPath) throws CollectionNotFoundException {
		ProbeConfig probeConfig = new ProbeConfig();
		
		try {
			XMLConfiguration config = new XMLConfiguration(configPath);
			config.setDelimiterParsingDisabled(false);
			config.load();
			
			// 원격지 접속 설정 로드
			probeConfig.setHost(config.getString("Connections.Host"));
			probeConfig.setPort(config.getInt("Connections.Port"));
			probeConfig.setTimeout(config.getInt("Connections.Timeout"));
			
			// 기본 프로그램 설정
			probeConfig.setRunningTime(config.getInt("Configuration.RunningTime", 10));
			probeConfig.setSf1Config(config.getString("Configuration.SF1Config"));
			
			// 검색엔진 설정에서 컬렉션 정보를 로드
			List<CollectionInfo> collectionInfoList = CollectionInfoLoader.getCollectionInfos(probeConfig.getSf1Config());
			for(int idx=0; idx<collectionInfoList.size(); idx++) {
				probeConfig.addCollectionInfo(collectionInfoList.get(idx));
			}
		} catch(ConfigurationException ce) {
			logger.error("Exception caused by : ", ce);
		}

		logger.info("Load server config. " + probeConfig.toString());

		return probeConfig;
	}
}
