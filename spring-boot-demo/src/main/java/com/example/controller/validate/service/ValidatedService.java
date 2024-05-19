package com.example.controller.validate.service;

import com.example.controller.validate.dto.ValidatedReqDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * @author Javen
 * @date 2024/5/19
 */
@Validated
public interface ValidatedService {

    void validate(@Valid ValidatedReqDTO validatedReqDTO);
}
