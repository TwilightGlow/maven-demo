package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Javen
 * @date 2022/4/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestFriend implements Serializable {

    private Integer age;
}
