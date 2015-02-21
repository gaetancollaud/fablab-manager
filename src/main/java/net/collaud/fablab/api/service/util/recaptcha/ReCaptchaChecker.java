package net.collaud.fablab.api.service.util.recaptcha;

import java.util.Map;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
abstract public class ReCaptchaChecker {
	public static final String RECAPTCHA_VERIF_URL = "https://www.google.com/recaptcha/api/siteverify";
	
	public static ReCaptchaCheckerReponse checkReCaptcha(String secret, String response) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("secret", secret);
		map.add("response", response);
		
        RestTemplate restTemplate = new RestTemplate();
		
		return restTemplate.postForObject(RECAPTCHA_VERIF_URL, map, ReCaptchaCheckerReponse.class);
	}
}
