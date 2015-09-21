package com.platform.util.httpclient;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParameter {

	private final List<BasicParameter> parameters = new ArrayList<BasicParameter>();

	private final List<FileParameter> fileParameters = new ArrayList<FileParameter>(
	        0);

	private final Map<String, String> headerMap = new HashMap<String, String>();

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public boolean isEmptyHeader() {
		return this.headerMap.isEmpty();
	}

	public void addHeader(String name, String value) {
		this.headerMap.put(name, value);
	}

	public void add(String name, String value) {
		this.parameters.add(new BasicParameter(name, value));
	}

	public void add(String name, List<String> values) {
		for (String s : values) {
			this.parameters.add(new BasicParameter(name, s));
		}
	}

	public void addFile(String name, File file) {
		fileParameters.add(new FileParameter(name, file));
	}

	public void addFile(String name, byte[] data, String fileName) {
		fileParameters.add(new FileParameter(name, data, fileName));
	}

	public List<FileParameter> getFileParameters() {
		return fileParameters;
	}

	public List<BasicParameter> getBasicParameters() {
		return parameters;
	}

	public boolean isBasicParameterEmpty() {
		return this.parameters.isEmpty();
	}

	public boolean isFileParameterEmpty() {
		return this.fileParameters.isEmpty();
	}

	public boolean isAllParameterEmpty() {
		return parameters.isEmpty() && fileParameters.isEmpty();
	}
}