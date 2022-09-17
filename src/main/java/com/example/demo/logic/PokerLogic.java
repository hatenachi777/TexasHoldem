package com.example.demo.logic;

import com.example.demo.entity.SimpleResult;


public class PokerLogic {

	
	public static String trump(int card){
		 String string_card = "";
		 
		if(card < 100) {
			 if(card % 100 < 10) {
				 string_card  = "s0" + (card % 100);
			 }else {
				 string_card  = "s" + (card % 100);
			 }
		 }else if(card > 100 && card < 200) {
			 if(card % 100 < 10) {
				 string_card  = "h0" + (card % 100);
			 }else {
				 string_card  = "h" + (card % 100);
			 }
		 }else if(card > 200 && card < 300) {
			 if(card % 100 < 10) {
				 string_card  = "d0" + (card % 100);
			 }else {
				 string_card  = "d" + (card % 100);
			 }
		 }else if(card > 300) {
			 if(card % 100 < 10) {
				 string_card  = "c0" + (card % 100);
			 }else {
				 string_card  = "c" + (card % 100);
			 } 
		 }
		String pathCard = "/card/0ic_middle/"+ string_card +".png";
	    	return pathCard ;
	     }
	
	public static String role(int[] purif, int[] hand) {
		//ArrayList<Integer> aList = new ArrayList<Integer>();
		
		int[] int_role = new int[7];
		
		for (int i = 0 ; i < 5 ; i++) {
			int_role[i] = purif[i];
		}
		for (int i = 0 ; i < 2 ; i++) {
			int_role[i + 5] = hand[i];
		}
		
		
		
		String role_name = "";
		
		
		int s_count = 0;
		int h_count = 0;
		int d_count = 0;
		int c_count = 0;
		int[] role = new int[7];
		
		for(int i = 0; i < int_role.length; i++) {
			//System.out.println(i);
			if(int_role[i] < 100) {
				s_count++;
				role[i] = int_role[i];
			}else if(int_role[i] > 100 && int_role[i] < 200) {
				h_count++;
				role[i] = (int_role[i] % 100);
			}else if(int_role[i] > 200 && int_role[i] < 300) {
				d_count++;
				role[i] = (int_role[i] % 100);
			}else if(int_role[i] > 300) {
				h_count++;
				role[i] = (int_role[i] % 100);
			}
			
			
			
		}
		//確認
		//for(int i :role) {
		//	System.out.println(i);
		//}
		
		int num_1 = 0;
		int num_2 = 0;
		int num_3 = 0;
		int num_4 = 0;
		int num_5 = 0;
		int num_6 = 0;
		int num_7 = 0;
		int num_8 = 0;
		int num_9 = 0;
		int num_10 = 0;
		int num_11 = 0;
		int num_12 = 0;
		int num_13 = 0;
		
		for(int i :role) {
			switch(i) {
			case 1:
				num_1++;
				break;
			case 2:
				num_2++;
				break;
			case 3:
				num_3++;
				break;
			case 4:
				num_4++;
				break;
			case 5:
				num_5++;
				break;
			case 6:
				num_6++;
				break;
			case 7:
				num_7++;
				break;
			case 8:
				num_8++;
				break;	
			case 9:
				num_9++;
				break;	
			case 10:
				num_10++;
				break;	
			case 11:
				num_11++;
				break;	
			case 12:
				num_12++;
				break;	
			case 13:
				num_13++;
				break;	
			}
		} 
		int per = 0;
		int card_4 = 0;
		int card_3 = 0;
		int kaidan = 0;
		
		boolean stre = false;
		
		int[] num = {
				num_1,num_2,num_3,num_4,num_5,num_6,num_7,num_8,num_9,num_10,num_11,num_12,num_13,
				};
		
		for(int i = 1 ; i < num.length; i++) {
			if(num[i-1] == 2) {
				per++;
			}else if (num[i-1] == 3) {
				card_3++;
			}else if(num[i-1] == 4) {
				card_4++;
			}
			if(num[i-1] >= 1 && num[i] >= 1) {
				kaidan++;
				if(kaidan >= 5) {
					stre = true;
				}
			}else if (kaidan >= 1 && num[i] == 0) {
				kaidan = 0;
			}
		}
		
		if(stre && s_count >= 5 || h_count >= 5 || d_count >= 5 || c_count >= 5 ) {
			role_name = "ストレートフラッシュ";
		}else if(stre) {
			role_name = "ストレート";
		}else if (per == 1 && card_3 == 1 || card_3 == 2 ) {
			role_name = "フルハウス";
		}else if (card_4 == 1) {
			role_name = "フォーカード";
		}else if (s_count >= 5 || h_count >= 5 || d_count >= 5 || c_count >= 5) {
			role_name = "フラッシュ";
		}else if (card_3 == 1) {
			role_name = "スリーカード";
		}else if(per == 2) {
			role_name = "ツーペア";
		}else if (per == 1) {
			role_name = "ワンペア";
		}else {
			role_name = "ハイカード";
		}
		
		return role_name ;
	}

	  public void play(SimpleResult simR) {
		  
		  //デッキづくり
		  int[] deck = new int[52];
		  int count = 0;
		  
		  for(int i = 0; i < 4; i++) {
			  for(int j = 1; j <14; j++) {
				  switch(i) {
				  case 0:
					  deck[count] = j;
					  break;
				  case 1:
					  deck[count] = j + 100*i;
					  break;
				  case 2:
					  deck[count] = j + 100*i;
					  break;
				  case 3:
					  deck[count] = j + 100*i;
					  break; 
				  }
				  count++;
			  }
		  }
		

			  // デッキのシャッフル
		      int len = deck.length;
		      for(int i = 0; i < len; i++){
		          int rand = new java.util.Random().nextInt(len);
		          int card = deck[rand];
		          deck[rand] = deck[i];
		          deck[i] = card;
	    	  }
		      
		      //カードを配る
		      
		    //プリフロップ
		      String pre_tehuda1 = null;
		      String pre_tehuda2 = null;
		      String pre_tehuda3 = null;
		      String pre_tehuda4 = null;
		      String pre_tehuda5 = null;
		      
	    	  for(int i = 0; i < 5; i++) {
	    		  switch(i) {
				  case 0:
					  pre_tehuda1 = trump(deck[i]);
					  break;
				  case 1:
					  pre_tehuda2 = trump(deck[i]);
					  break;
				  case 2:
					  pre_tehuda3 = trump(deck[i]);
					  break;
				  case 3:
					  pre_tehuda4 = trump(deck[i]);
					  break;
				  case 4:
					  pre_tehuda5 = trump(deck[i]);
					  break; 
				  }
	    	 }
	    	  
	    	  
	    	  simR.setPref1(pre_tehuda1);
	    	  simR.setPref2(pre_tehuda2);
	    	  simR.setPref3(pre_tehuda3);
	    	  simR.setPref4(pre_tehuda4);
	    	  simR.setPref5(pre_tehuda5);
	    	  
	    	  //プレイヤー
	    	  String p_tehuda1 = null;
		      String p_tehuda2 = null;
	    	  
	    	  for(int i = 0; i < 2; i++) {
	    		  switch(i) {
				  case 0:
					  p_tehuda1 = trump(deck[i + 5]);
					  break;
				  case 1:
					  p_tehuda2 = trump(deck[i + 5]);
					  break;
	    		  }
	    	 }
	    	  simR.setP_hand1(p_tehuda1);
	    	  simR.setP_hand2(p_tehuda2);
	    	  
	    	  
	    	//ディーラー
	    	  String d_tehuda1 = null;
		      String d_tehuda2 = null;
		      for(int i = 0; i < 2; i++) {
	    		  switch(i) {
				  case 0:
					  d_tehuda1 = trump(deck[i + 7]);
					  break;
				  case 1:
					  d_tehuda2 = trump(deck[i + 7]);
					  break;
	    		  }
	    	 }
		     simR.setD_hand1(d_tehuda1);
		     simR.setD_hand2(d_tehuda2);
		      
		     
		     //ポーカー役判定
		     
		     int[] purif = new int[5];
		     int[] player = new int[2];
		     int[] ai = new int[2];
		     
		      //カードを配る
		      //プリフロップ
	    	  for(int i = 0; i < 5; i++) {
	    		  purif[i] = deck[i];
	    		  }
	    	  //プレイヤー
	    	  for(int i = 0; i < 2; i++) {
	    		  player[i] = deck[i + 5];
	    		  }
	    	  //ディーラー
	    	  for(int i = 0; i < 2; i++) {
	    		  ai[i] = deck[i + 7];
	    	  }
	    	 //プレーヤーの役
	    	 String ple = role(purif,player);
	    	 simR.setPlayerRole(ple);
	    	 
	    	 //ディーラーの役
	    	 //System.out.println("------");
		     String cpu = role(purif,ai);
		     simR.setDealerRole(cpu);
		     
		    

	      
	  }

}
