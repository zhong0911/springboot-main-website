import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // 获取错误状态码
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        // 根据状态码进行相应的处理
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "error/404"; // 返回自定义的404页面
        } else {
            return "error/404"; // 返回其他错误页面
        }
    }
}