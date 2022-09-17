package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.Account;
import com.example.demo.dto.AccountRequest;
import com.example.demo.entity.AccountInfo;
import com.example.demo.entity.SimpleResult;
import com.example.demo.logic.Judge;
import com.example.demo.logic.PokerLogic;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;

@Controller
public class PokerAccountController {

	Logger logger = LoggerFactory.getLogger(PokerAccountController.class);
	@Autowired AccountInfo accountInfo;
	@Autowired SimpleResult simpleResult;
	@Autowired AccountRepository repository;
	@Autowired
	private AccountService accountService;
	
	//topページ
	@RequestMapping("/")
	public String Start() {
		return "index.html";
	}
	//新規登録ページ
	@GetMapping("/newAccount")
	public String newAccount(Model model) {
		model.addAttribute("accountRequest", new AccountRequest());
		return "newAccount.html";
	}
	//登録完了ページ
	@PostMapping("/newAccountOK")
	public String create(@Validated @ModelAttribute AccountRequest accountRequest, BindingResult result, Model model) throws InterruptedException {
		
		Thread.sleep(1000);
		if (result.hasErrors()) {
			// 入力チェックエラーの場合
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "newAccount.html";
		}
		// ユーザー情報の登録
		Thread.sleep(1000);
		accountService.create(accountRequest);
		return "/newAccountOK";
	}
	
	//ログインページ
	@GetMapping("/login")
	public String loginStart(Model model) {
		model.addAttribute("accountRequest", new AccountRequest());
		return "login.html";
	}

	//ログイン完了ページ
	@PostMapping(value = "/loginOK")
	public String loginOK(@Validated @ModelAttribute AccountRequest accountRequest, BindingResult result, Model model) {
		
		//見つけきたデータをforで回して確認している
		//xmlでpsqlの操作を検討
		//現状は小規模で運用を想定しているので、計算量は間に合う
		List<Account> accountlist = accountService.searchAll();
		boolean hit = false;
		for ( Account account:accountlist) {
			if(account.getName().equals(accountRequest.getName()) && account.getPassword().equals(accountRequest.getPassword())){
				accountInfo.setId(account.getId());
				accountInfo.setName(account.getName());
				accountInfo.setPassword(account.getPassword());
				accountInfo.setCoin(account.getCoin());
				hit = true;
				break;
			}
				
			
		}
		logger.info( accountRequest.getName());
		logger.info( accountRequest.getPassword());
		logger.info( accountlist.toString());
		
		if(hit) {
			model.addAttribute(this.accountInfo);
				return "loginOK.html";
		}else {
			model.addAttribute("validationError", "登録情報がありません");
				return "login.html";
		}
		
	}


	//ゲームプレイページ
	@GetMapping("/table")
	public String pokerPlay(Model model) {
		//ここはJavaのロジックで運用　ゲームロジックをSpringBootでの代用が思いつかなかった
		PokerLogic pokerlogic = new PokerLogic();
		pokerlogic.play(this.simpleResult);
		//ＢＥＴ用のlistを作成
		List<Integer> coins = new ArrayList<Integer>();
		int range = accountInfo.getCoin()/100;

		for (int i = 1 ; i < range+1 ; i++) {
			coins.add(i*100);		}

		logger.info( simpleResult.getPlayerRole());
		logger.info( simpleResult.getDealerRole());

		model.addAttribute("coins" ,coins);
		model.addAttribute(simpleResult);
		model.addAttribute(accountInfo);
		

		return "table.html";
	}

	//ゲームプレイ結果ページ
	@PostMapping("/result")
	public String pokerResult(@RequestParam("betCoin") String betCoin, Model model) {
		//Long id = accountInfo.getId();
		//accountService.delete(id);
		
		logger.info( accountInfo.getName());

		int bet = Integer.parseInt(betCoin);
		int payCoin = accountInfo.getCoin() - bet;
		accountInfo.setCoin(payCoin);

		// ベット処理の分岐 
		Judge judge = new Judge();
		boolean res = judge.judgeGame(simpleResult.getPlayerRole(), simpleResult.getDealerRole());

		if(res) {//勝った場合
			payCoin = accountInfo.getCoin() + (bet * 2) ;
			accountInfo.setCoin(payCoin);
		}else {//負けた場合

		}
		
		model.addAttribute(simpleResult);
		model.addAttribute(accountInfo);
		
		accountService.update(accountInfo);

		return "result.html";
	}

}
