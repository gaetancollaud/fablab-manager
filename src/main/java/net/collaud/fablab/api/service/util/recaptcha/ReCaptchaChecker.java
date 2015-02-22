package net.collaud.fablab.api.service.util.recaptcha;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Helper to for Recaptcha 2.0 from Google
 *
 * Based on https://developers.google.com/recaptcha/docs/verify
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
abstract public class ReCaptchaChecker {

	public static final String RECAPTCHA_VERIF_URL = "https://www.google.com/recaptcha/api/siteverify";

	/**
	 * Check the validity of a response. The remote addr is omitted since it's optional.
	 *
	 * @param secret your recaptcha secret
	 * @param response the response (from the recaptcha widget)
	 * @return the response with error details (if any)
	 */
	public static ReCaptchaCheckerReponse checkReCaptcha(String secret, String response) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret", secret);
		map.add("response", response);

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.postForObject(RECAPTCHA_VERIF_URL, map, ReCaptchaCheckerReponse.class);
	}
}
