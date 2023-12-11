package blacksmith.post.exceptions.handler;

import blacksmith.post.exceptions.UserException;
import blacksmith.post.exceptions.member.MemberException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try{
            if(ex instanceof UserException){
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(acceptHeader) || "*/*".equals(acceptHeader)){
                    log.info("ex: {}", ex.getMessage());
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("success", false);
                    errorResult.put("message", ex.getMessage());

                    String resultJson = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(resultJson);
                    return new ModelAndView();
                }else {
                    return new ModelAndView("error/500");
                }
            }
        } catch(IOException e){
            log.error("Member Exception Resolver Error",e);
        }


        return null;
    }
}
