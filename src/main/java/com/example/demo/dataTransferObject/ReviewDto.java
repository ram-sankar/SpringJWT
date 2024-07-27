package com.example.demo.dataTransferObject;

import lombok.Data;

@Data
public class ReviewDto {
    private int id;
    private String title;
    private String content;
    private int stars;
}
