package com.design.framework.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.design.framework.api.ApiResponse;
import com.design.framework.api.enums.ApiCodeEnum;
import com.design.framework.bean.TokenNotFilterPathList;
import com.design.framework.cache.CachePool;
import com.design.framework.cache.service.RedisServiceImpl;
import com.design.framework.utils.DateUtil;
import com.design.framework.utils.MemoryUtils;

/**
 * Token拦截器
 * @author JohnDeng
 * @date 2018年8月8日下午3:36:49
 */
public class TokenInterceptor implements HandlerInterceptor {

    protected Logger logger = Logger.getLogger(getClass());
    @Resource
    protected RedisServiceImpl redisServiceImpl;
    @Resource
    protected TokenNotFilterPathList tokenNotFilterPathList;

    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
     * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，
     * 而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，
     * 这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("\n===================tokenInterceptor start===================");
        List<String> paths = tokenNotFilterPathList.getPath();
        String token = request.getHeader("Token");
        for (String url : paths) {
            if (request.getRequestURI().contains(url)) {
                logger.debug("\n===================tokenInterceptor pass===================");
                return true;
            }
        }

        //检验token
        if (StringUtils.isNotEmpty(token)) {
            if (redisServiceImpl != null) {
                if (redisServiceImpl.exists(token)) {// 是否有这个token
                    String tokenValue = (String) redisServiceImpl.get(token);
                    if (StringUtils.isEmpty(tokenValue)) {
                        isEmptyToken(response);
                    } else {
                        //8小时
                        redisServiceImpl.set(token, tokenValue, 60 * 60 * 8);// 刷新token
                        logger.debug("\n===================tokenInterceptor pass===================");
                        return true;
                    }
                } else {
                    // 检验token是否有效
                    Object tokenValue = CachePool.getInstance().getCacheItem(
                            token);
                    if (tokenValue==null) {
                        isEmptyToken(response);
                    } else {
                        // 刷新token
                        CachePool.getInstance().putCacheItem(token, tokenValue,
                                DateUtil.getHalfAnHourTime());
                        logger.debug("\n===================tokenInterceptor pass===================");
                        return true;
                    }
                }
            } else if (CachePool.getInstance().containKey(token)) {
                // 检验token是否有效
                Object tokenValue = CachePool.getInstance().getCacheItem(token);
                if (tokenValue==null) {
                    isEmptyToken(response);
                } else {
                    // 刷新token
                    CachePool.getInstance().putCacheItem(token, tokenValue,
                            DateUtil.getHalfAnHourTime());
                    logger.debug("\n===================tokenInterceptor pass===================");
                    return true;
                }
            } else {
                tokenConnectionError(response);
            }
        } else {
            returnTokenError(response);
        }

        return false;
    }

    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
     * 它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，
     * 也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，
     * 这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，
     * Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，
     * 要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
     * 也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 打印JVM信息。
        if (logger.isInfoEnabled()) {
            logger.info("\n===================" + MemoryUtils.getMemory() + "===================");
        }
    }

    /**
     * token 为空
     *
     * @param httpResponse
     * @throws IOException
     * @author John
     * @datatime 2017年11月27日下午3:18:59
     */
    private void isEmptyToken(HttpServletResponse httpResponse)
            throws IOException {
        PrintWriter out = httpResponse.getWriter();
        out.println(JSONObject.toJSON(ApiResponse.returnTokenError()));
        logger.debug("\n===================filter token error===================");
    }


    private void tokenConnectionError(HttpServletResponse httpResponse)
            throws IOException {
        PrintWriter out = httpResponse.getWriter();
        out.println(JSONObject.toJSON(ApiResponse.returnCustomCodeMsg(ApiCodeEnum.TOKEN_CONNECTION_ERROR.getCode(), ApiCodeEnum.TOKEN_CONNECTION_ERROR.getMessage())));
        logger.debug("\n===================filter token error===================");
    }

    /**
     * token异常
     *
     * @param httpResponse
     * @throws IOException
     * @author John
     * @datatime 2017年11月27日下午3:17:47
     */
    private void returnTokenError(HttpServletResponse httpResponse)
            throws IOException {
    	isEmptyToken(httpResponse);
    }


}
