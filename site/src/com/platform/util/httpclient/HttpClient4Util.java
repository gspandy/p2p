package com.platform.util.httpclient;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpClient4Util {

	private HttpClient httpclient;

	public static HttpClient4Util createDefault() {
		return new HttpClient4Util();
	}

	public static org.apache.http.client.HttpClient wrapClient(
			org.apache.http.client.HttpClient base) {
		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			X509TrustManager tm = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
				}
			};
			ctx.init(null, new TrustManager[] { tm }, null);
			SSLSocketFactory ssf = new SSLSocketFactory(ctx,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("https", 443, ssf));
			ThreadSafeClientConnManager mgr = new ThreadSafeClientConnManager(
					registry);
			return new DefaultHttpClient(mgr, base.getParams());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public HttpClient4Util(int timeout, boolean ssl) {
		if (ssl) {
			this.httpclient = new DefaultHttpClient();
			this.httpclient = HttpClient4Util.wrapClient(httpclient);
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, timeout);
		} else {
			httpclient = new DefaultHttpClient();
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, timeout);
		}
	}

	public HttpClient4Util() {
		this(30000, false);
	}

	public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public HttpClient getHttpclient() {
		return httpclient;
	}

	/**
	 * 简化的get
	 * 
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResp doGet(String url) throws ClientProtocolException,
			IOException {
		return this.doGet(url, null, null);
	}

	/**
	 * 详细get
	 * 
	 * @param url
	 * @param httpParameter
	 *            参考{@link HttpParameter}
	 * @param charset
	 *            参数编码
	 * @return 参考{@link HttpResp}
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResp doGet(String url, HttpParameter httpParameter,
			String charset) throws ClientProtocolException, IOException {
		StringBuilder sb = new StringBuilder(url);
		if (httpParameter != null && !httpParameter.isAllParameterEmpty()) {
			if (url.indexOf("?") == -1) {
				sb.append("?");
			}
			if (sb.charAt(sb.length() - 1) != ('?')) {
				sb.append("&");
			}
			for (BasicParameter o : httpParameter.getBasicParameters()) {
				sb.append(URLEncoder.encode(o.getName(), charset));
				sb.append("=");
				sb.append(URLEncoder.encode(o.getValue(), charset));
				sb.append("&");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		HttpGet httpGet = new HttpGet(sb.toString());
		if (httpParameter != null && !httpParameter.isEmptyHeader()) {
			Set<Entry<String, String>> set = httpParameter.getHeaderMap()
					.entrySet();
			for (Entry<String, String> e : set) {
				httpGet.addHeader(e.getKey(), e.getValue());
			}
		}
		return this.execute(httpGet);
	}

	/**
	 * post 字符数据
	 * 
	 * @param url
	 * @param value
	 *            post的字符串数据
	 * @param charset
	 *            参数编码
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResp doPostStringBody(String url, String string, String charset)
			throws ClientProtocolException, IOException {
		HttpEntity entity = new StringEntity(string, charset);
		return this.doPostBody(url, entity);
	}

	/**
	 * post 字节数据
	 * 
	 * @param url
	 * @param bytes
	 *            字节数据
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResp doPostBytesBody(String url, byte[] bytes)
			throws ClientProtocolException, IOException {
		HttpEntity entity = new ByteArrayEntity(bytes);
		return this.doPostBody(url, entity);
	}

	private HttpResp doPostBody(String url, HttpEntity entity)
			throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(entity);
		return this.execute(httpPost);
	}

	/**
	 * post
	 * 
	 * @param url
	 * @param httpParameter
	 *            参数信息 参考{@link HttpParameter}
	 * @param charset
	 *            参数编码
	 * @return 参考{@link HttpResp}
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public HttpResp doPost(String url, HttpParameter httpParameter,
			String charset) throws ClientProtocolException, IOException {
		HttpPost httpPost = new HttpPost(url);
		if (httpParameter.isFileParameterEmpty()) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			for (BasicParameter e : httpParameter.getBasicParameters()) {
				nameValuePairs.add(new BasicNameValuePair(e.getName(), e
						.getValue()));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
		} else {
			MultipartEntity reqEntity = new MultipartEntity();
			for (FileParameter e : httpParameter.getFileParameters()) {
				if (e.getFile() != null) {
					reqEntity.addPart(e.getName(), new FileBody(e.getFile()));
				} else {
					reqEntity.addPart(e.getName(),
							new ByteArrayBody(e.getData(), e.getFileName()));
				}
			}
			for (BasicParameter e : httpParameter.getBasicParameters()) {
				reqEntity.addPart(e.getName(), new StringBody(e.getValue(),
						Charset.forName(charset)));
			}
			httpPost.setEntity(reqEntity);
		}
		if (httpParameter != null && !httpParameter.isEmptyHeader()) {
			Set<Entry<String, String>> set = httpParameter.getHeaderMap()
					.entrySet();
			for (Entry<String, String> e : set) {
				httpPost.addHeader(e.getKey(), e.getValue());
			}
		}
		return this.execute(httpPost);
	}

	/**
	 * 具体执行过程
	 * 
	 * @param request
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private HttpResp execute(HttpRequestBase request)
			throws ClientProtocolException, IOException {
		HttpEntity entity = null;
		try {
			HttpResponse httpResponse = httpclient.execute(request);
			HttpResp httpResp = new HttpResp();
			httpResp.setStatusCode(httpResponse.getStatusLine().getStatusCode());
			entity = httpResponse.getEntity();
			httpResp.setBytes(EntityUtils.toByteArray(entity));
			return httpResp;
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		}
	}

	/**
	 * 最终可释放资源调用
	 */
	public void shutdown() {
		this.httpclient.getConnectionManager().shutdown();
	}
}