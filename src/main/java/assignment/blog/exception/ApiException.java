package assignment.blog.exception;

import org.springframework.web.client.RestClientException;

public class ApiException extends RestClientException {
    public ApiException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
