package com.gewuyou.baseforge.common.exception;

import com.gewuyou.baseforge.common.enums.ResponseInformation;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private final ResponseInformation responseInformation;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BaseException(ResponseInformation responseInformation) {
        this.responseInformation = responseInformation;
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A {@code null} value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public BaseException( ResponseInformation responseInformation,Throwable cause) {
        super(cause);
        this.responseInformation = responseInformation;
    }

    public int getCode() {
        return responseInformation.getCode();
    }

    public String getMessage() {
        return responseInformation.getMessage();
    }

}
