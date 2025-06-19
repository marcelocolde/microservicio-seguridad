package com.marcelo.ms.gateway.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import reactor.core.publisher.Mono;

@Component
//public class SampleGlobalFilter implements GlobalFilter{
public class SampleGlobalFilter implements Filter, Ordered{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
	}

	@Override
	public int getOrder() {
		return 100;
	}
	
//	private final Logger logger = LoggerFactory.getLogger(SampleGlobalFilter.class);
//
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		logger.info("ejecutando el filtro antes del request - PRE");
//		System.out.println("Request Path: " + exchange.getRequest().getPath());
//		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//			logger.info("ejecutando filtro POST response");
//			
//			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color","red").build());
//			exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
//		}));
//	}

}
