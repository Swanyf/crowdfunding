package com.swan.crowdfunding.component.resolve;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.google.gson.Gson;
import com.swan.crowdfunding.entity.ResultVO;

public class ExceptionResolve extends SimpleMappingExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        // 根据请求头判断是同步请求还是局部请求
        if (request.getHeader("accept").contains("applicaotion/json") || (request.getHeader("X-Requested-With") != null
                && request.getHeader("X-Requested-With").contains("XMLHttpRequest"))) {

            try {
                // 设置响应体的内容类型
                response.setContentType("application/json;charset=utf-8");
                // 封装响应的数据
                ResultVO<Object> resultVO = new ResultVO<>();
                // 设置异常错误信息
                resultVO.setMessage(ex.getMessage());
                // 设置没有数据
                resultVO.setResponseData(ResultVO.NO_DATA);
                // 设置请求失败
                resultVO.setResult(ResultVO.FAILED);

                // 将响应的数据装换成json格式
                Gson gson = new Gson();
                String json = gson.toJson(resultVO);
                
                // 将json的数据传递到浏览器
                response.getWriter().write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        
        // 不满足if条件，说明是同步请求，则调用父类的方法按常规处理 
        return super.doResolveException(request, response, handler, ex);
    }
}
