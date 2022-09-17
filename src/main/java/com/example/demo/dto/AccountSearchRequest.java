package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data

public class AccountSearchRequest implements Serializable {
    /**
     * ユーザーID
     */
    private String name;
    /**
     * ユーザー名
     */
    private String password;
    
}
