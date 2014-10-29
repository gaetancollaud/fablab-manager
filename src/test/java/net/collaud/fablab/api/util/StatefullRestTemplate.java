package net.collaud.fablab.api.util;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Provide a statefull implementation of a RestTemplate.
 *
 * @author gaetancollaud@gmail.com
 */
public class StatefullRestTemplate {

	private static final Logger LOG = Logger.getLogger(StatefullRestTemplate.class);

	private final RestTemplate template;
	private final Map<String, String> cookies;

	public StatefullRestTemplate() {
		template = new RestTemplate();
		cookies = new HashMap<>();
	}

	/**
	 * Get the all cookies.
	 *
	 * @return
	 */
	public Map<String, String> getCookies() {
		return cookies;
	}

	/**
	 * Remove all stored cookies
	 */
	public void clearAllCookies() {
		cookies.clear();
	}

	/**
	 * Set one cookie. Will overight if there is already one with the same key
	 *
	 * @param key
	 * @param value
	 */
	public void setCookie(String key, String value) {
		cookies.put(key, value);
	}

	/**
	 * Remove one cookie. Do nothing if the cookie does not exist.
	 *
	 * @param key
	 */
	public void removeCookie(String key) {
		cookies.remove(key);
	}

	/**
	 * call a POST call.
	 *
	 * @param <T> return type of this call
	 * @param url url to call
	 * @param data request data
	 * @param clazz return type class
	 * @return the returned object of this request
	 */
	public <T> T post(String url, Object data, Class<T> clazz) {
		HttpEntity request = new HttpEntity(data, getHeaders());
		HttpEntity<T> response = template.exchange(url, HttpMethod.POST, request, clazz);
		extractCookies(response.getHeaders());
		return response.getBody();
	}

	/**
	 * call a GET call.
	 *
	 * @param <T> return type of this call
	 * @param url url to call
	 * @param clazz return type class
	 * @return the returned object of this request
	 */
	public <T> T get(String url, Class<T> clazz) {
		HttpEntity<T> response = template.exchange(getRequestEntity(HttpMethod.GET, url), clazz);
		extractCookies(response.getHeaders());
		return response.getBody();
	}

	/**
	 * Extract cookies from a response header. Will overight previous cookies if new one arrive with
	 * the same key.
	 *
	 * @param headers response header
	 */
	protected void extractCookies(HttpHeaders headers) {
		List<String> cookiesList = headers.get("Set-Cookie");
		if (cookiesList != null && !cookiesList.isEmpty()) {
			String cookiesStr = cookiesList.get(0);
			if (LOG.isTraceEnabled()) {
				LOG.trace("Cookies read from response : " + cookiesStr);
			}
			String[] cookiesSplit = cookiesStr.split(";");
			for (String cookieStr : cookiesSplit) {
				String[] keyValueSplit = cookieStr.split("=");
				cookies.put(keyValueSplit[0], keyValueSplit[1]);
			}
		}
	}

	/**
	 * Get the cookies string to write in the request header.
	 *
	 * @return cookies as string
	 */
	protected String getCookiesString() {
		StringBuilder sb = new StringBuilder();
		if (!cookies.isEmpty()) {
			cookies.entrySet().forEach(entry -> {
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
				sb.append(";");
			});
			sb.deleteCharAt(sb.length() - 1);
		}
		if (LOG.isTraceEnabled()) {
			LOG.trace("Cookies added to request : " + sb.toString());
		}
		return sb.toString();
	}

	/**
	 * Create a request entity for call
	 *
	 * @param method method of the call
	 * @param url url of the call
	 * @return new request entity
	 */
	protected RequestEntity getRequestEntity(HttpMethod method, String url) {
		return new RequestEntity(getHeaders(), method, URI.create(url));
	}

	/**
	 * Create the header for the request.
	 *
	 * @return new header
	 */
	protected MultiValueMap getHeaders() {
		MultiValueMap headers = new HttpHeaders();
		headers.put("Cookie", Arrays.asList(new String[]{getCookiesString()}));
		return headers;
	}

}
