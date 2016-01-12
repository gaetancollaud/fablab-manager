package net.collaud.fablab.manager.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 *
 * @author Gaetan Collaud
 */
public class FileTypeNotAllowedException extends NestedRuntimeException{
	
	public FileTypeNotAllowedException(String msg) {
		super(msg);
	}
	
}
