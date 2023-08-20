package com.example.controller.session;

import com.example.model.Student;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Javen
 * @date 2023/8/5
 */
@RestController
@SessionAttributes(value = {"user"})
public class TestSessionAttributesController {

    // 如果同时使用@SessionAttributes 和@ResponseBody注解，就会出现
    //“java.lang.IllegalStateException: Cannot create a session after the response has been committed ”这个错误。
    // 这是为什么呢，因为我们在创建session前，已经将response进行了提交。
    // 因为@SessionAttributes中定义了ModelMap中哪些属性将要存入session中。
    // 因为这个存入session的动作是在method1方法结束后发生的，而return 语句一结束，response就会立即被返回/
    @GetMapping("/v1.0/session_attributes_error")
    public String sessionAttributesError(Model model) {
        Student gallen = Student.builder()
                .name("Gallen")
                .age(29).build();
        model.addAttribute("user", gallen);
        return model.toString();
    }

    // 解决方法, 在return之前，创建自己的session，即将上面的变成如下：
    @GetMapping("/v1.0/session_attributes")
    public String sessionAttributes(Model model, HttpSession httpSession) {
        Student gallen = Student.builder()
                .name("Gallen")
                .age(29).build();
        model.addAttribute("user", gallen);
        return model.toString();
    }

    @GetMapping("/v1.0/get_servlet_request")
    public String sessionAttributes(HttpServletRequest request) {
        Object user = request.getAttribute("user");
        System.out.println(user);
        Object user1 = request.getSession().getAttribute("user");
        System.out.println(user1);
        return request.toString();
    }

    // 参数上声明HttpSession会自动创建Session，相当于request.getSession()
    @GetMapping("/v1.0/get_session")
    public String getSession(HttpSession httpSession, HttpServletRequest request) {
        Object user = httpSession.getAttribute("user");
        System.out.println(user);
        return httpSession.toString();
    }

    // sessionStatus.setComplete()方法用于清除@SessionAttributes绑定的值
    @GetMapping("/v1.0/session_complete")
    public String getSession(ModelMap modelMap, SessionStatus sessionStatus) {
        System.out.println(modelMap.toString());
        sessionStatus.setComplete();
        return modelMap.toString();
    }

    @GetMapping("/v1.0/get_session_attribute")
    public String getSessionAttribute(@SessionAttribute(value = "user", required = false) Student student) {
        System.out.println(student);
        return "123";
    }

    @GetMapping("/v1.0/get_request_attribute")
    public String getRequestAttribute(@RequestAttribute(value = "user", required = false) Student student) {
        System.out.println(student);
        return "123";
    }

}
