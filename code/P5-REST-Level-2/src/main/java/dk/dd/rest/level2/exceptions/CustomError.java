package dk.dd.rest.level2.exceptions;

// inspired by Java Guides

/*
 * Originally, Spring Boot provides the following response to errors:
 * {
 * "timestamp": 1512713804164,
 *  "status": 404,
 *  "error": "Not Found",
 *  "message": "No message available",
 *  "path": "/some-dummy-url"
 *  }
 *
 * We can customise it
 */

import java.util.Date;

public class CustomError {
    private Date timestamp;
    private String message;
    private String details;

    public CustomError(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}