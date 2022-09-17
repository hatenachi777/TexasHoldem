package com.example.demo.logic;

public class Judge {
public boolean judgeGame(String p, String d) {
		
		//勝ちならture、負けはfalse
		int r_p = 0;
		int r_d = 0; 
		
		switch(p) {
		 
			case "ストレートフラッシュ":
				r_p = 9;
				break;
			case "フォーカード":
				r_p = 8;
				break;
			case "フルハウス":
				r_p = 7;
				break;
			case "フラッシュ":
				r_p = 6;
				break;
			case "ストレート":
				r_p = 5;
				break;
			case "スリーカード":
				r_p = 4;
				break;
			case "ツーペア":
				r_p = 3;
				break;
			case "ワンペア":
				r_p = 2;
				break;
			case "ハイカード":
				r_p = 1;
				break;
			
		
		}
		
		switch(d) {
		 
		case "ストレートフラッシュ":
			r_d = 9;
			break;
		case "フォーカード":
			r_d = 8;
			break;
		case "フルハウス":
			r_d = 7;
			break;
		case "フラッシュ":
			r_d = 6;
			break;
		case "ストレート":
			r_d = 5;
			break;
		case "スリーカード":
			r_d = 4;
			break;
		case "ツーペア":
			r_d = 3;
			break;
		case "ワンペア":
			r_d = 2;
			break;
		case "ハイカード":
			r_d = 1;
			break;
	
	}
		
		if(r_p >= r_d) {
			
			return true;
			
		}else {
			return false;
		}
			
		
	}

}
