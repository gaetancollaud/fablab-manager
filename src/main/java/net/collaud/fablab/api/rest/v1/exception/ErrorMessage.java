package net.collaud.fablab.api.rest.v1.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	private boolean success;
	private String errorMessage;
}
