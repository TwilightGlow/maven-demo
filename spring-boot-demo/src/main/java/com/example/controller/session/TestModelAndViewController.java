package com.example.controller.session;

import com.example.model.Student;
import com.example.model.Teacher;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2023/8/5
 */
@RestController
public class TestModelAndViewController {

    @ModelAttribute("student1")
    public Student initStudent() {
        Student javen = Student.builder()
                .name("Javen")
                .age(30).build();
        return javen;
    }

    @ModelAttribute("student")
    public Student initStudent1() {
        Student sophia = Student.builder()
                .name("Sophia")
                .age(30).build();
        return sophia;
    }

    @GetMapping("/v1.0/model")
    public String model(@ModelAttribute("student")Student student, @ModelAttribute("student1")Student student1) {
        System.out.println(123);
        System.out.println(student);
        System.out.println(student1);
        return student.toString();
    }

    @GetMapping("/v1.0/model_without_annotation")
    public String modelWithoutAnnotation(Student student) {
        System.out.println(123);
        System.out.println(student);
        return student.toString();
    }

    @GetMapping("/v1.0/model_view")
    public String modelView(Model modelAndView) {
        System.out.println(123);
        System.out.println(modelAndView);
        return modelAndView.toString();
    }

    @GetMapping("/v1.0/bind")
    public String modelView(@RequestParam Integer age,@ModelAttribute("student") Student student, Model modelAndView) {
        System.out.println(student);
        System.out.println(modelAndView);
        return student.toString();
    }

    // 标注在方法参数上的@ModelAttribute说明了该方法参数的值将由model中取得。
    // 如果model中找不到，那么该参数会先被实例化，然后被添加到model中。在model中存在以后，请求中所有名称匹配的参数都会填充到该参数中。
    // 这在Spring MVC中被称为数据绑定，一个非常有用的特性，我们不用每次都手动从表格数据中转换这些字段数据。
    // 以上面的代码为例，这个Teacher类型的实例可能来自哪里呢？有几种可能:
    //  它可能因为@SessionAttributes标注的使用已经存在于model中
    //  它可能因为在同个控制器中使用了@ModelAttribute方法已经存在于model中——正如上一小节所叙述的
    //  它可能是由URI模板变量和类型转换中取得的（下面会详细讲解）
    //  它可能是调用了自身的默认构造器被实例化出来的
    @GetMapping("/v1.0/bind_new")
    public String modelView(@RequestParam Integer age, @ModelAttribute Teacher teacher) {
        System.out.println(age);
        System.out.println(teacher);
        return teacher.toString();
    }
}
