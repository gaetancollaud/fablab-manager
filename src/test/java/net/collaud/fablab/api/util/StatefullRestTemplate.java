package net.collaud.fablab.api.util;

import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaétan
 */
public class StatefullRestTemplate {

	private final RestTemplate template;
	private final Map<String, String> cookies;

	public StatefullRestTemplate() {
		template = new RestTemplate();
		cookies = new HashMap<>();
	}

	public <T> T post(String url, Object data, Class<T> clazz) {
		HttpEntity request = new HttpEntity(data, getHeaders());
		HttpEntity<T> response = template.exchange(url, HttpMethod.POST, request, clazz);
		extractCookies(response.getHeaders());
		return response.getBody();
	}

	public <T> T get(String url, Class<T> clazz) {
		HttpEntity<T> response = template.exchange(getRequestEntity(HttpMethod.GET, url), clazz);
		extractCookies(response.getHeaders());
		return response.getBody();
	}

	protected void extractCookies(HttpHeaders headers) {
		List<String> cookiesList = headers.get("Set-Cookie");
		if (!cookiesList.isEmpty()) {
			String cookiesStr = cookiesList.get(0);
			String[] cookiesSplit = cookiesStr.split(";");
			for (String cookieStr : cookiesSplit) {
				String[] keyValueSplit = cookieStr.split("=");
				cookies.put(keyValueSplit[0], keyValueSplit[1]);
			}
		}
	}

	protected String getCookies() {
		StringBuilder sb = new StringBuilder();
		if (!cookies.isEmpty()) {
			cookies.entrySet().forEach(entry -> {
				sb.append(entry.getKey());
				sb.append("=");
				sb.append(entry.getValue());
			});
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}

	protected RequestEntity getRequestEntity(HttpMethod method, String url) {
		return new RequestEntity(getHeaders(), method, URI.create(url));
	}

	protected MultiValueMap getHeaders() {
		MultiValueMap headers = new HttpHeaders();
		headers.put("Cookie", Arrays.asList(new String[]{getCookies()}));
		return headers;
	}

}
