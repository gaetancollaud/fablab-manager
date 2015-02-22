package net.collaud.fablab.api.service.util.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity for the ReCaptcha check API response
 * 
 * Based on https://developers.google.com/recaptcha/docs/verify#api-response
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
public class ReCaptchaCheckerReponse {

	/**
	 * Is the recapcha valid ?
	 */
	@JsonProperty("success")
	private Boolean success;

	/**
	 * Error codes. Will be null if no error codes retrieved.
	 */
	@JsonProperty("error-codes")
	private List<String> errorCodes;
}
