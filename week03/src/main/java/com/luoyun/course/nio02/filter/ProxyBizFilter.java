package com.luoyun.course.nio02.filter;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ProxyBizFilter
 *
 * @author luoyun
 * @data 2021/10/8
 */
public class ProxyBizFilter implements HttpRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(ProxyBizFilter.class);

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String requestUri = fullRequest.getUri();
        if (!requestUri.contains("/hello")){
            throw new RuntimeException("非法URL：" + requestUri);
        }
        HttpHeaders headers = fullRequest.headers();
        if (headers == null){
            throw new RuntimeException("非法用户");
        }
        String cookie = headers.get("Cookie");
        logger.info("cookie:{}",cookie);
    }
}
