package com.wisenut.probe.argument.model;

public enum Location {
	LOCAL,  //ISC를 통하지 않고 패턴에 의해 임의로 로그 파일을 생성.
	REMOTE  //ISC를 통해 직접 검색을 수행하여 쿼리로그를 생성.
}
