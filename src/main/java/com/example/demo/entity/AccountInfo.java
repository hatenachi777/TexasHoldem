package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;
/**
 * ユーザー情報 Entity
 */

@Entity
@Data //ゲッターセッターを付与してる
@Component
@SessionScope
public class AccountInfo {
	
	@Id
    @GeneratedValue
    private long id;
	
    private String name;
    
    private String password;
	
    private int coin;

}
