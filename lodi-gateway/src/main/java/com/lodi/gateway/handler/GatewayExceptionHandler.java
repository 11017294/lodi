package com.lodi.gateway.handler;

import com.lodi.common.core.enums.ErrorCode;
import com.lodi.gateway.utils.WebFluxUtils;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 网关统一异常处理
 *
 * @author MaybeBin
 * @createDate 2023-11-07
 */
@Order(-1)
@Slf4j
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }

        String msg = "内部服务器错误";
        Integer code = ErrorCode.SYSTEM_ERROR.getCode();

        if (ex instanceof NotFoundException) {
            msg = "服务未找到";
        } else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            msg = responseStatusException.getMessage();
        } else if (ex instanceof SignatureException) {
            code = ErrorCode.NOT_LOGIN_ERROR.getCode();
            msg = "凭证无效";
        }

        log.error("[网关异常处理]请求路径:{},异常信息:{}", exchange.getRequest().getPath(), ex.getMessage());
        return WebFluxUtils.webFluxResponseWriter(response, msg, code);
    }
}
