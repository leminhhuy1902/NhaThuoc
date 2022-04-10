package com.clinic.manager.dto.enums;

/**
 * Enum for response type with code and message.
 */
public enum ResponseType {

    SUCCESS                                 (1,   "Success"),
    INTERNAL_SERVER_ERROR                   (500, "Internal Server Error"),
    UNAUTHORIZED                            (401, "Unauthorized"),
    BAD_REQUEST                             (400, "Bad Request"),
    NOT_FOUND                               (404, "Not Found"),

    REQUEST_BODY_MISSING                    (400, "Request Body Missing"),
    FORBIDDEN                               (403, "Forbidden"),
    METHOD_NOT_ALLOWED                      (405, "Method Not Allowed"),
    NOT_ACCEPTABLE                          (406, "Not Acceptable"),
    UNSUPPORTED_MEDIA_TYPE                  (415, "Unsupported Media Type");

    private int status;
    private String message;

    /**
     * Constructor that takes a http reponse status code and its corresponding message.
     * @param status The http status code.
     * @param message The corresponding message.
     */
    ResponseType(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Get the http status code from this enum's instance.
     * @return The http status code.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Get the message of this enum's instance.
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Build a {@link ResponseType} from a http status code.  
     * @param statusCode The status code
     * @return A {@link ResponseType} for this status code. 
     */
    public static ResponseType getUnhanldeType(int statusCode) {
        switch (statusCode) {
        case 1: return SUCCESS;
        case 400: return REQUEST_BODY_MISSING;
        case 401: return UNAUTHORIZED;
        case 403: return FORBIDDEN;
        case 404: return NOT_FOUND;
        case 405: return METHOD_NOT_ALLOWED;
        case 406: return NOT_ACCEPTABLE;
        case 415: return UNSUPPORTED_MEDIA_TYPE;
        default:
            return INTERNAL_SERVER_ERROR;
        }
    }
}
