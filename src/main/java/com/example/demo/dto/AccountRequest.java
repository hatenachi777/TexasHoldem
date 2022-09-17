package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ユーザー情報 リクエストデータ
 */
@Data
public class AccountRequest implements Serializable  {
	
	/**
	   * 名前
	   */
	  @NotEmpty(message = "名前を入力してください")
	  @Size(max = 10, message = "名前は10文字以内で入力してください")
	  private String name;
	  
	  /**
	   * パスワード
	   */
	  @Size(min = 4, message = "パスワードは最低でも4文字以上で入力してください")
	  @Size(max = 12, message = "4文字以上12文字以下で入力してください")
	  private String password;

}
