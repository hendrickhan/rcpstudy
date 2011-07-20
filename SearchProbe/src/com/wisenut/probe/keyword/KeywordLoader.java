package com.wisenut.probe.keyword;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;


/**
 * <pre>
 * com.wisenut.agent.keyword
 *   |_ KeywordLoader.java
 * </pre>
 *
 * Desc : 
 * @Company : WISENut. Inc
 * @Author  : BeomJun Kim
 * @Date    : 2010. 9. 28. ���� 9:52:18
 * @Version : 
 */
public class KeywordLoader extends Thread {
	private volatile boolean shutdownRequested = false;
	private String keywordFilePath = "D:\\ApplicationDev\\SF1Managers\\SearchAgent\\config\\keywords.txt";
	private Long lastModifyDate = 0L;
	private Hashtable<String,Long> keywordTable;

	/**
	 * Desc : Constructor of KeywordLoader.java class
	 * @param keywordTable
	 */
	public KeywordLoader(Hashtable<String,Long> keywordTable) {
		this.keywordTable = keywordTable;
		this.lastModifyDate = getLastModified();
	}
	
	public final void run() {
		try {
			loadKeywordsFile();
			
			while(!isShutdownRequested()) {
				// 1.keywords.txt ���� ������ üũ �� ���� ���ε� 
				if(isModified()) {
					System.out.println("[KeywordLoader] Now reloading keywords file.");
					loadKeywordsFile();
				}
				Thread.sleep(1000);
			}
		} catch(InterruptedException ie) {
			//ie.printStackTrace();
		} finally {
			doShutdown();
		}
	}
	
	/**
	 * Desc : Thread ���� ��û
	 * @Method Name : shutdownRequest
	 */
	public void shutdownRequest() {
		this.shutdownRequested = true;
		interrupt();
	}
	
	/**
	 * Desc : Thread ���� ��û �÷��� ��ȯ
	 * @Method Name : isShutdownRequested
	 * @return
	 */
	public boolean isShutdownRequested() {
		return this.shutdownRequested;
	}
	
	/**
	 * Desc : Thread�� Shutdown �޼��� ���
	 * @Method Name : doShutdown
	 */
	private void doShutdown() {
		System.out.println("[KeywordLoader] Shutdown thread...");
	}

	/**
	 * Desc : Hashtable�� Key���� Keyword�� �߰�
	 * @Method Name : insertKeyword
	 * @param keyword
	 */
	private void insertKeyword(String keyword, Long term) {
		keywordTable.put(keyword, term);
	}
	
	private Long getLastModified() {
		Long modifiedDate = 0L;
		
		File keywordsFile = new File(keywordFilePath);
		if(keywordsFile.exists()) {
			modifiedDate = keywordsFile.lastModified();
		}
		
		return modifiedDate;
	}
	
	private boolean isModified() {
		boolean result = false;

		File keywordsFile = new File(keywordFilePath);
		if(!keywordsFile.exists()) {
			System.out.println("keywords.txt ������ ã�� �� ����ϴ�.");
			System.exit(-1);
		}

		Long lastModified = keywordsFile.lastModified();  //���� ��������
		if(lastModified > this.lastModifyDate) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
	private void loadKeywordsFile() {
		File keywordsFile = new File(keywordFilePath);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(keywordsFile));
			
			String line = "";
			while((line = bufferedReader.readLine()) != null) {
				String[] splitedLine = line.split("[|]");
				if(splitedLine.length < 2) {
					continue;
				} else {
					insertKeyword(splitedLine[0], new Long(splitedLine[1]));
				}
			}
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
