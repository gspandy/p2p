package com.platform.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {
	static ExecutorService executor = null;
	
	public static ExecutorService getThread(){
		if(executor == null){
			executor = Executors.newCachedThreadPool();
		}
		return executor;
	}
}
