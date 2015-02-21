package net.collaud.fablab.api.service.util.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@NoArgsConstructor
public class ReCaptchaCheckerReponse {
	@JsonProperty("success")
	private Boolean success;
	
	@JsonProperty("error-codes")
	private List<String> errorCodes;
}
