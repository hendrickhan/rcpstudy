package com.wisenut.probe.config;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * com.wisenut.probe.config
 *   |_ CollectionInfo.java
 * </pre>
 *
 * 컬렉션 개별 정보가 할당되는 모델 클래스
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2011. 7. 20. 오전 10:45:47
 * @Version : 
 */
public class CollectionInfo {
	private String id;
	private List<String> searchFields = new ArrayList<String>();
	private List<String> documentFields = new ArrayList<String>();

	/**
	 * 컬렉션 ID를 반환
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 컬렉션 ID를 설정
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 검색 필드 리스트를 반환
	 * @return the searchField
	 */
	public List<String> getSearchField() {
		return searchFields;
	}

	/**
	 * 검색 필드 리스트를 할당
	 * @param searchField the searchField to set
	 */
	public void setSearchField(List<String> searchField) {
		this.searchFields = searchField;
	}

	/**
	 * 검색 필드 리스트에 필드명을 추가
	 * @Method Name : addSearchField
	 * @param fieldName
	 */
	public void addSearchField(String searchField) {
		if(!searchFields.contains(searchField)) {
			this.searchFields.add(searchField);
		}
	}

	/**
	 * 결과 필드 리스트를 반환
	 * @return the documentField
	 */
	public List<String> getDocumentField() {
		return documentFields;
	}

	/**
	 * 결과 필드 리스트를 할당
	 * @param documentField the documentField to set
	 */
	public void setDocumentField(List<String> documentField) {
		this.documentFields = documentField;
	}
	
	/**
	 * 결과 필드 리스트에 필드명 추가
	 * @Method Name : addDocumentField
	 * @param documentField
	 */
	public void addDocumentField(String documentField) {
		if(!documentFields.contains(documentField)) {
			this.documentFields.add(documentField);
		}
	}
	
}
