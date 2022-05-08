package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jinwei Zhang
 * @date 2022/5/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "person",createIndex = true)
public class Person implements Serializable {

    @Id
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text,index = true)
    private String name;

    @Field(type = FieldType.Integer,index = true)
    private Integer age;

    @Field(type = FieldType.Keyword,index = true)
    private String sex;

    @Field(type = FieldType.Keyword,index = true)
    private String tel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date, format = DateFormat.date_time, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}