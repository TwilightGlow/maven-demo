package com.example.controller.validate.service.impl;

import com.example.controller.validate.dto.ValidatedReqDTO;
import com.example.controller.validate.service.ValidatedService;
import org.springframework.stereotype.Service;

/**
 * @author Javen
 * @date 2024/5/19
 */
@Service
public class ValidatedServiceImpl implements ValidatedService {

    @Override
    public void validate(ValidatedReqDTO validatedReqDTO) {
        System.out.println("ValidatedReqDTO: " + validatedReqDTO);
    }
}
