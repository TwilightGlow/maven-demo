package com.example.controller.validate.dto;

import com.example.controller.validate.model.ValidatedGroups;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author Javen
 * @date 2024/5/19
 */
@Data
public class ValidatedReqDTO {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Max(value = 30, message = "年龄不能超过30岁")
    private Integer age;

    @NotBlank(message = "分组1不能为空", groups = {ValidatedGroups.Group1.class})
    private String group1;

    @NotBlank(message = "分组2不能为空", groups = {ValidatedGroups.Group2.class})
    private String group2;
}
