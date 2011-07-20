package com.wisenut.probe.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

import com.wisenut.probe.exception.CollectionNotFoundException;
import com.wisenut.probe.log.LogFactory;

/**
 * <pre>
 * com.wisenut.probe.config
 *   |_ CollectionInfoLoader.java
 * </pre>
 *
 * 검색엔진 설정파일을 로드하여 컬렉션 정보 리스트를 생성하는 로더 클래스
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 20. 오후 3:21:09
 * @Version : 
 */
public class CollectionInfoLoader {
	private static Logger logger = LogFactory.getLogger(CollectionInfoLoader.class);
	
	public static List<CollectionInfo> getCollectionInfos(String configPath) throws CollectionNotFoundException {
		List<CollectionInfo> collectionInfos = new ArrayList<CollectionInfo>();

		try {
			XMLConfiguration.setDefaultListDelimiter((char)0);
			XMLConfiguration config = new XMLConfiguration(configPath);
			config.setDelimiterParsingDisabled(false);
			config.load();
			
			String[] collectionIdList = config.getStringArray("Collection[@id]");
			if(collectionIdList == null || collectionIdList.length == 0) {
				// 처음의 설정 파일에 Collection 노드가 존재하지 않으면 Include 설정을 찾음.
				List<String> includeList = distinctList(config.getStringArray("Include[@xml]"));

				if(includeList == null || includeList.size() == 0) {
					// 설정파일 내에서 Collection 노드나 Include 노드를 찾을 수 없는 경우.
					throw new CollectionNotFoundException();
				} else {
					// Include 가 존재하는 경우
					for(int idx=0; idx<includeList.size(); idx++) {
						// XMLConfiguration 객체 재생성
						config = new XMLConfiguration(includeList.get(idx));
						config.setDelimiterParsingDisabled(false);
						config.load();
						
						List<CollectionInfo> includedCollectionInfos = getCollectionInfos(config);
						for(CollectionInfo info : includedCollectionInfos) {
							collectionInfos.add(info);
						}
					}
				}
			} else {
				collectionInfos = getCollectionInfos(config);
			}
		} catch (ConfigurationException ce) {
			logger.error("Exception caused by : ", ce);
		}
		
		return collectionInfos;
	}
	
	/**
	 * 리스트가 중복된 경우 Merge 처리. (XMLConfiguration의 버그로 인해..)
	 * @Method Name : distinctList
	 * @param targetList
	 * @return
	 */
	private static List<String> distinctList(String[] targetList) {
		List<String> result = new ArrayList<String>();
		
		for(int idx=0; idx<targetList.length; idx++) {
			if(!result.contains(targetList[idx])) {
				result.add(targetList[idx]);
			}
		}
		
		return result;
	}
	
	/**
	 * 리스트가 중복된 경우 Merge 처리. (XMLConfiguration의 버그로 인해..)
	 * @Method Name : distinctList
	 * @param targetList
	 * @return
	 */
	@SuppressWarnings("unused")
	private static List<String> distinctList(List<String> targetList) {
		List<String> result = new ArrayList<String>();
		
		for(int idx=0; idx<targetList.size(); idx++) {
			if(!result.contains(targetList.get(idx))) {
				result.add(targetList.get(idx));
			}
		}
		
		return result;
	}
	
	/**
	 * 검색엔진 설정파일을 분석하여 컬렉션 정보만을 추출하여 리스트로 반환
	 * @Method Name : getCollectionInfos
	 * @param config
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static List<CollectionInfo> getCollectionInfos(XMLConfiguration config) {
		List<CollectionInfo> result = new ArrayList<CollectionInfo>();

		List<HierarchicalConfiguration> collectionList = config.configurationsAt("Collection");
		for(Iterator<HierarchicalConfiguration> iter = (Iterator<HierarchicalConfiguration>) collectionList.iterator(); iter.hasNext();) {
			CollectionInfo collectionInfo = new CollectionInfo();  //컬렉션 정보 객체

			// 1.Collection ID 설정단계
			HierarchicalConfiguration collection = (HierarchicalConfiguration) iter.next();
			collectionInfo.setId(collection.getString("[@id]"));  //컬렉션ID 설정
			
			// 2.Search Field 설정단계
			List<? extends Object> indexList = collection.configurationsAt("Index.Field");
			for(Iterator<?> it = indexList.iterator(); it.hasNext();) {
				HierarchicalConfiguration field = (HierarchicalConfiguration) it.next();
				if(field.getString("[@prefix]") == null) {
					collectionInfo.addSearchField(field.getString("[@name]"));
				}
			}

			// 3.Document Field 설정단계
			List<? extends Object> documentList = collection.configurationsAt("Document.Field");
			for(Iterator<?> it = documentList.iterator(); it.hasNext();) {
				HierarchicalConfiguration field = (HierarchicalConfiguration) it.next();
				collectionInfo.addDocumentField(field.getString("[@name]"));
			}
			
			// 컬렉션 정보 리스트에 추가
			boolean isAdd = true;
			for(CollectionInfo info : result) {
				if(info.getId().equals(collectionInfo.getId())) {
					isAdd = false;
					break;
				}
			}
			if(isAdd) {
				result.add(collectionInfo);
			}
		}
		
		return result;
	}
}
