package com.example.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    private String author;

    @Column(precision = 10, scale = 2, columnDefinition = "decimal(10, 2)")
    private Double price;

    @Column(precision = 10, scale = 2)
    private BigDecimal money;

    @Column(columnDefinition = "timestamp DEFAULT current_timestamp")
    private Date createTime;

    private Timestamp updateTime;
}
