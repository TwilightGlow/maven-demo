package com.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Javen
 * @date 2022/4/1
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String password;
}