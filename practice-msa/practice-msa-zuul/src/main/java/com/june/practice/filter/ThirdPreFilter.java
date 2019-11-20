package com.june.practice.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.june.practice.config.MsaResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class ThirdPreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        Object logicStatus = context.get("logic-is-success");
        return logicStatus != null && (Boolean) logicStatus != false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String b = request.getParameter("b");
        if (null == b) {
            //对该请求禁止路由，直接跳到 postFilter
            context.setSendZuulResponse(false);
            MsaResult msaResult = new MsaResult();
            msaResult.setResult(0);
            msaResult.setMessage("参数B为空");
            ObjectMapper mapper = new ObjectMapper();
            try {
                String s = mapper.writeValueAsString(msaResult);
                context.setResponseBody(s);
            } catch (JsonProcessingException e) {
            }
            //下游执行的开关
            context.set("logic-is-success", false);
        }
        context.set("logic-is-success", true);
        return null;
    }
}
