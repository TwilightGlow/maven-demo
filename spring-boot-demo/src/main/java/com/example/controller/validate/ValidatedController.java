package com.example.controller.validate;

import com.example.controller.validate.dto.ValidatedReqDTO;
import com.example.controller.validate.model.ValidList;
import com.example.controller.validate.model.ValidatedGroups;
import com.example.controller.validate.service.ValidatedService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Javen
 * @date 2024/5/19
 */
@Validated
@RestController
@RequestMapping("/validate")
@RequiredArgsConstructor
public class ValidatedController {

    private final ValidatedService validatedService;

    private final Validator validator;

    private final org.springframework.validation.Validator springValidator;

    private final ApplicationContext applicationContext;
    private final BeanFactory beanFactory;

    @GetMapping("/validator")
    public void validator(ValidatedReqDTO validatedReqDTO) {
        // 方式一：直接注入，此时的类型为LocalValidatorFactoryBean
        Set<ConstraintViolation<ValidatedReqDTO>> constraintViolations = validator.validate(validatedReqDTO);
        for (ConstraintViolation<ValidatedReqDTO> constraintViolation : constraintViolations) {
            System.out.println(constraintViolation.getMessage());
        }
        // 方式二：使用Hibernate构造，此时的类型为ValidatorImpl
        Validator validator1 = Validation.buildDefaultValidatorFactory().getValidator();
        for (ConstraintViolation<ValidatedReqDTO> constraintViolation : validator1.validate(validatedReqDTO)) {
            System.out.println(constraintViolation.getMessage());
        }
    }

    // 1. 类上不写@Valid/@Validated注解，则方法参数上：
    //      不写注解：抛出HandlerMethodValidationException(Spring MVC方法参数验证失败)异常，不能绑定BindingResult
    //      写@Validated：抛出HandlerMethodValidationException
    //      写@Valid：抛出HandlerMethodValidationException
    // 2. 在类上写@Validated注解，则方法参数上：
    //      不写注解：抛出ConstraintViolationException(JSR303, JSR280)异常，不能绑定BindingResult
    //      写@Valid：抛出ConstraintViolationException(JSR303, JSR280)异常，不能绑定BindingResult
    //      写@Validated：抛出ConstraintViolationException(JSR303, JSR280)异常，不能绑定BindingResult
    // 3. 在类上写@Valid注解，则方法参数上：
    //      不写注解：抛出HandlerMethodValidationException
    //      写@Valid：抛出HandlerMethodValidationException
    //      写@Validated：抛出HandlerMethodValidationException
    @GetMapping("/params")
    public void validateParams(@Length(min = 5, message = "name长度不能少于5") String name) {
        System.out.println(name);
    }

    // 即使类上加了@Validated/@Valid，对于实体类类型的参数，仍然需要加上@Validated或者@Valid注解才能校验生效
    // 此时对于@Validated和@Valid，抛出的都是MethodArgumentNotValidException异常，是BindingException的子类，可以被BindingResult接收
    @GetMapping("/entity")
    public void entity(@Valid ValidatedReqDTO validatedReqDTO) {
        System.out.println(validatedReqDTO);
    }

    // BindingResult前面必须跟实体类对象，对非实体类的方法参数不生效！！！
    @GetMapping("/bindingResult")
    public void validateParams(@Valid ValidatedReqDTO validatedReqDTO, BindingResult bindingResult) {
        System.out.println(validatedReqDTO);
        for (ObjectError allError : bindingResult.getAllErrors()) {
            System.out.println(allError.getDefaultMessage());
        }
    }

    @PostMapping("/postEntity")
    public void postEntity(@Validated(ValidatedGroups.Group1.class) @RequestBody ValidatedReqDTO validatedReqDTO) {
        System.out.println(validatedReqDTO);
    }

    // @Valid注解可以对List里的元素校验
    @PostMapping("/validList")
    public void validList(@Valid @RequestBody List<ValidatedReqDTO> validatedReqDTO) {
        System.out.println(validatedReqDTO);
    }

    // @Validated注解不能对List里的元素校验
    @PostMapping("/validatedList")
    public void validatedList(@Validated @RequestBody List<ValidatedReqDTO> validatedReqDTO) {
        System.out.println(validatedReqDTO);
    }

    // 通过定义ValidList类的方式，对内部List进行校验
    @PostMapping("/validatedList2")
    public void validatedList2(@Validated @RequestBody ValidList<ValidatedReqDTO> validatedReqDTO) {
        System.out.println(validatedReqDTO);
    }

    // @Validated支持分组校验，@Valid不支持分组
    @GetMapping("/groups")
    public void groups(@Validated(ValidatedGroups.Group1.class) ValidatedReqDTO validatedReqDTO, BindingResult bindingResult) {
        System.out.println(validatedReqDTO);
        for (ObjectError allError : bindingResult.getAllErrors()) {
            System.out.println(allError.getDefaultMessage());
        }
    }

    // 如果在service层需要开启校验，需要类上加@Validated注解，方法参数上需要加@Valid注解！！！
    // @Validated和@Valid都需要加在接口上才能生效
    @GetMapping("/service")
    public void validateService(ValidatedReqDTO validatedReqDTO) {
        validatedService.validate(validatedReqDTO);
    }

}
