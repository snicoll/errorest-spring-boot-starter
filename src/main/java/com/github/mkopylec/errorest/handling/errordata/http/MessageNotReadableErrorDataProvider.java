package com.github.mkopylec.errorest.handling.errordata.http;

import com.github.mkopylec.errorest.configuration.ErrorestProperties;
import com.github.mkopylec.errorest.handling.errordata.ErrorData;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.uncapitalize;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class MessageNotReadableErrorDataProvider extends HttpClientErrorDataProvider<HttpMessageNotReadableException> {

    public MessageNotReadableErrorDataProvider(ErrorestProperties errorestProperties) {
        super(errorestProperties);
    }

    @Override
    public ErrorData getErrorData(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return getErrorData(ex, request, BAD_REQUEST);
    }

    @Override
    public ErrorData getErrorData(HttpMessageNotReadableException ex, HttpStatus responseHttpStatus, ErrorAttributes errorAttributes, RequestAttributes requestAttributes) {
        return super.getErrorData(ex, BAD_REQUEST, errorAttributes, requestAttributes);
    }

    @Override
    protected String getErrorDescription(HttpMessageNotReadableException ex) {
        return BAD_REQUEST.getReasonPhrase() + ", " + uncapitalize(ex.getMessage());
    }
}
