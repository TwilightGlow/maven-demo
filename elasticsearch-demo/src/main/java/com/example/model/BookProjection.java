package com.example.model;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author Javen
 * @date 2022/5/8
 */
public interface BookProjection {

    String getTitle();

    String getAuthor();

    @Value("#{target.title + ' ' + target.author}")
    String getDescription();
}
