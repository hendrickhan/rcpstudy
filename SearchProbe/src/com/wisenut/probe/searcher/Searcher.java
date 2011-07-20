package com.wisenut.probe.searcher;

import java.util.Hashtable;

import QueryAPI450.Search;

import com.wisenut.probe.config.ProbeConfig;

public class Searcher implements Runnable {
	private String keyword = "";
	private String[] collections = new String[] {"news", "newcms"};
	private ProbeConfig config;
	private Hashtable<String,Long> keywordTable;
	
	public Searcher(String keyword, Hashtable<String,Long> keywordTable, ProbeConfig config) {
		this.keyword = keyword;
		this.keywordTable = keywordTable;
		this.config = config;
	}

	public void run() {
		try {
			while(true) {
				Search search = new Search();
				search.w3SetCodePage("utf-8");
				search.w3SetCommonQuery(keyword);
				for(int i=0; i<collections.length; i++) {
					search.w3AddCollection(collections[i]);
					search.w3SetPageInfo(collections[i], 0, 3);
					search.w3AddSortField(collections[i], "RANK", 0);
					search.w3AddSearchField(collections[i], "TITLE");
				}
				
				search.w3ConnectServer("61.82.137.178", 20020, 50000);
				search.w3RecvResult(0);
				
				Thread.sleep(keywordTable.get(keyword));
			}
		} catch(InterruptedException ie) {
		}
	}

}
