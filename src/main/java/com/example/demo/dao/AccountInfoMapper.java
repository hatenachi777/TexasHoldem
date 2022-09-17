package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Account;
import com.example.demo.entity.AccountInfo;

/**
 * ユーザー情報 Mapper
 */

@Mapper
public interface  AccountInfoMapper {

	List<AccountInfo> search(Account user);
}
