package com.ttmy.awm.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import java.util.Enumeration;


/**
 * 第二层（zuul路由拦截）
 * 做单点登陆，验证token合法性
 */
@Component
public class AuthFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /**
         * pre：路由之前
         * routing：路由之时
         * post： 路由之后
         * error：发送错误调用
         */

        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //使过滤器生效
        return true;
    }

    /**先走oauth的校验再走拦截
     * 这里的拦截器校验安全性的意义不大，可以用来解析令牌信息
     * 校验token合法性的主要方法
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Enumeration<String> headerNames = requestContext.getRequest().getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();//调用nextElement方法获得元素
            System.out.print(header);
            if(header.equals("authorization")){
                //找到令牌，放行
                return null;
            }
        }
        requestContext.setSendZuulResponse(false);  //false  不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
        requestContext.setResponseBody("You have no token!!!");
        requestContext.setResponseStatusCode(401);
//        System.out.println("接到了令牌-->"+requestContext.getRequest().getHeader("authorization"));
        return null;
    }
}