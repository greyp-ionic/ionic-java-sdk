package com.ionic.sdk.error;

import com.ionic.sdk.agent.request.base.AgentResponseBase;

/**
 * An Exception class for representing Ionic server-side errors.
 * When an SDK call communicates with an Ionic server, it is possible that the
 * server incurs an error, rejects the data it receives, etc.  In that case, the SDK
 * throws a ServerException in order to indicate both the SDK-specific error data as
 * well as any server-specific error data.  The server-specific error data can be
 * helpful in determining why the communication failed.
 * <p>
 * When deprecated class {@link ServerException} is removed from code base, this class will be updated to
 * extend {@link IonicException}.
 * <p>
 * {@link ServerException} has been deprecated in favor of this class.
 */
@SuppressWarnings("deprecation")
public final class IonicServerException extends ServerException {

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this exception instance.
     */
    @Override
    public String getMessage() {
        return Integer.toString(super.getReturnCode()) + SPACER + super.getMessage();
    }

    /**
     * String formatting spacer.
     */
    private static final String SPACER = " - ";

// on removal of ServerException, this stuff needs to be uncommented
//    /**
//     * The HTTP response code.
//     */
//    private final int httpResponseCode;
//
//    /**
//     * The internal server error code.
//     */
//    private final int serverErrorCode;
//
//    /**
//     * The internal server error message.
//     */
//    private final String serverErrorMessage;
//
//    /**
//     * The internal server error data JSON.
//     */
//    private final String serverErrorDataJson;
//
//    /**
//     * The conversation ID generated by the client for use in the server request.
//     */
//    private final String conversationId;

    /**
     * Initializes the exception with an SDK error code.
     *
     * @param errorCode the SDK error code
     */
    public IonicServerException(final int errorCode) {
        super(errorCode);
    }

    /**
     * Initializes the exception with an SDK error code.
     *
     * @param errorCode the SDK error code
     * @param message   the text description of the error
     */
    public IonicServerException(final int errorCode, final String message) {
        super(errorCode, message);
    }

    /**
     * Initializes the exception with an SDK error code.
     *
     * @param errorCode           the SDK error code
     * @param httpResponseCode    the HTTP response code
     * @param serverErrorCode     the internal server error code
     * @param serverErrorMessage  the internal server error message
     * @param serverErrorDataJson the internal server error data JSON
     * @param conversationId      the conversation ID generated by the client for use in the server request
     */
    public IonicServerException(final int errorCode, final int httpResponseCode,
                                final int serverErrorCode, final String serverErrorMessage,
                                final String serverErrorDataJson, final String conversationId) {
        super(errorCode, httpResponseCode, serverErrorCode, serverErrorMessage, serverErrorDataJson, conversationId);
// on removal of ServerException, this stuff needs to be uncommented
//        super(errorCode);
//        this.httpResponseCode = httpResponseCode;
//        this.serverErrorCode = serverErrorCode;
//        this.serverErrorMessage = serverErrorMessage;
//        this.serverErrorDataJson = serverErrorDataJson;
//        this.conversationId = conversationId;
    }

    /**
     * Initializes the exception with an SDK error code.
     *
     * @param errorCode      the SDK error code
     * @param conversationId the conversation ID generated by the client for use in the server request
     * @param response       the Ionic representation of the server response
     */
    public IonicServerException(final int errorCode, final String conversationId, final AgentResponseBase response) {
        super(errorCode, SdkError.getErrorString(errorCode), response.getHttpResponseCode(),
                response.getServerErrorCode(), response.getServerErrorMessage(), response.getServerErrorDataJson(),
                conversationId, response);
    }

    /**
     * Initializes the exception with an SDK error code.
     *
     * @param errorCode           the SDK error code
     * @param message             the text description of the error
     * @param httpResponseCode    the HTTP response code
     * @param serverErrorCode     the internal server error code
     * @param serverErrorMessage  the internal server error message
     * @param serverErrorDataJson the internal server error data JSON
     * @param conversationId      the conversation ID generated by the client for use in the server request
     * @param response            the Ionic representation of the server response
     */
    @SuppressWarnings({"checkstyle:parameternumber"})  // ability to efficiently instantiate from server response
    public IonicServerException(final int errorCode, final String message, final int httpResponseCode,
                                final int serverErrorCode, final String serverErrorMessage,
                                final String serverErrorDataJson, final String conversationId,
                                final AgentResponseBase response) {
        super(errorCode, message, httpResponseCode, serverErrorCode,
                serverErrorMessage, serverErrorDataJson, conversationId, response);
// on removal of ServerException, this stuff needs to be uncommented
//        super(errorCode, message);
//        this.httpResponseCode = httpResponseCode;
//        this.serverErrorCode = serverErrorCode;
//        this.serverErrorMessage = serverErrorMessage;
//        this.serverErrorDataJson = serverErrorDataJson;
//        this.conversationId = conversationId;
    }

// on removal of ServerException, this stuff needs to be uncommented
//    /**
//     * @return the HTTP response code
//     */
//    public int getHttpResponseCode() {
//        return httpResponseCode;
//    }
//
//    /**
//     * @return the internal server error code
//     */
//    public int getServerErrorCode() {
//        return serverErrorCode;
//    }
//
//    /**
//     * @return the internal server error message
//     */
//    public String getServerErrorMessage() {
//        return serverErrorMessage;
//    }
//
//    /**
//     * @return the internal server error data JSON
//     */
//    public String getServerErrorDataJson() {
//        return serverErrorDataJson;
//    }
//
//    /**
//     * @return the conversation ID generated by the client for use in the server request
//     */
//    public String getConversationId() {
//        return conversationId;
//    }

    /** Value of serialVersionUID from maven coordinates "com.ionic:ionic-sdk:2.7.0". */
    private static final long serialVersionUID = 135378444826917769L;
}
