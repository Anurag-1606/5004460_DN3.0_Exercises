package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
    private Long id;

    private String title;

    private String author;

    @JsonProperty("cost")
    private Double price;

    private String isbn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @JsonIgnore
    private String internalCode;
}
