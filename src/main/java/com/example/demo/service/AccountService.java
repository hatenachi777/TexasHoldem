package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.PokerAccountController;
import com.example.demo.dto.Account;
import com.example.demo.dto.AccountRequest;
import com.example.demo.entity.AccountInfo;
import com.example.demo.repository.AccountRepository;

/**
 * ユーザー情報 Service
 */
@Service
@Transactional
public class AccountService {

	 @Autowired
	  private AccountRepository accountRepository;
	 
	 //デバック用
	 Logger logger = LoggerFactory.getLogger(PokerAccountController.class);
	 
	  /**
	   * ユーザー情報 全検索
	   * @return 検索結果
	   */
	  public List<Account> searchAll() {
	    return accountRepository.findAll();
	  }
	 
	  /**
	   * ユーザー情報 主キー検索
	   * @return 検索結果
	   */
	  public Account findById(Long id) {
		  logger.info("findByIdが動いてる" + accountRepository.findById(id).get().toString());
	    return accountRepository.findById(id).get();
	  }
	  
	 
	 /**
	   * ユーザー情報 新規登録
	   * @param user ユーザー情報
	   */
	  public void create(AccountRequest accountRequest) {
	    Account account = new Account();
	    account.setName(accountRequest.getName());
	    account.setPassword(accountRequest.getPassword());
	    account.setCoin(1000);
	    accountRepository.save(account);
	  }
	  
	  //厳密にはアップデートではない
	  //idを消して改めてデータベースに保存してるので非効率
	  public void update(AccountInfo accountInfo) {
		  //処理ををまたぐとスコープの関係かデリートがうまく動かない
		    Account account = new Account();
		   
		    logger.info("updateが動いてる" + account.toString());
		    accountRepository.delete(account);
		    
		    if(accountInfo.getId()>0) {
		    	Account reaccount = new Account();
		    	reaccount.setId(accountInfo.getId());
			    reaccount.setName(accountInfo.getName());
			    reaccount.setPassword(accountInfo.getPassword());
			    reaccount.setCoin(accountInfo.getCoin());
			    accountRepository.save(reaccount);
		    }else {//idが0の時だけNullを受け付ける
		    account.setId(accountInfo.getId());
		    account.setName(accountInfo.getName());
		    account.setPassword(accountInfo.getPassword());
		    account.setCoin(accountInfo.getCoin());
		    accountRepository.save(account);
		    }
		  }
}
