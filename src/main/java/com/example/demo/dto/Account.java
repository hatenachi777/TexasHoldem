package com.example.demo.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "poker_account")
public class Account implements Serializable  {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;
	
	@Column(name = "name")
    private String name;
    
	@Column(name = "password")
    private String password;
	
	@Column(name = "coin")
    private int coin = 1000;
}
	

