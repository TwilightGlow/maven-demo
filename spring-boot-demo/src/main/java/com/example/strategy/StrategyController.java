package com.example.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javen
 * @date 2023/8/6
 */
@RestController
@RequiredArgsConstructor
public class StrategyController {

    private final MyContext myContext;

    @GetMapping("/v1.0/strategy")
    public Integer strategy(String mode) {
        return myContext.exec(mode);
    }
}
