package com.mysbapp.demo.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component // with @ServletComponentScan not necessary
@WebFilter(urlPatterns = {"/products"})
public class SimpleFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterchain)
            throws IOException, ServletException {

        log.info("Remote Host:" + request.getRemoteHost());
        log.info("Remote Address:" + request.getRemoteAddr());
        filterchain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {
    }
}
