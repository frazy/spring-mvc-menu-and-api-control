/**
 * 
 */
package com.github.frazy.spm.exception;

/**
 * @author hzyinglei
 *
 */
public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

}
