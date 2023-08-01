package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Jinwei Zhang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Foo2 {

    private String foo;

    @Override
    public String toString() {
        return "Foo1 [foo=" + this.foo + "]";
    }
}
