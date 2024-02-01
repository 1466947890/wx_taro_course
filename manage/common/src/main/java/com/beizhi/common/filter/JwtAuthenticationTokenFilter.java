package com.beizhi.common.filter;

import com.beizhi.common.baseError.BaseErrorEnum;
import com.beizhi.common.baseError.BusinessException;
import com.beizhi.common.utils.JwtUtils;
import com.beizhi.common.utils.RedisCache;
import com.beizhi.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 14669
 * @date 2023/12/6 22:19
 * @describe
 */

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        log.info(String.format("当前访问域名为【%s】", request.getRequestURL()));
        if(!StringUtils.hasText(token)){
            filterChain.doFilter(request, response);
            return;
        }
        String userId = "";
        try {
            userId = String.valueOf(JwtUtils.getUserId(token));
        } catch (Exception e) {
            throw new BusinessException(BaseErrorEnum.TOKEN_ERROR);
        }

        LoginUser loginUser = redisCache.getCacheObject("login:" + userId);

        if(Objects.nonNull(loginUser)){
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);

    }
}
