package com.smart.starter.core.xss;


import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 *
 * @author guwenchang
 * @date 2019-04-29 17:34
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null || values.length <= 0) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = StringEscapeUtils.escapeHtml4(values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return StringEscapeUtils.escapeHtml4(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return StringEscapeUtils.escapeHtml4(value);
    }
}

