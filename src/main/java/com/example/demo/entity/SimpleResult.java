package com.example.demo.entity;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;


@Data
@Component
@SessionScope

public class SimpleResult {

		private String pref1,pref2,pref3,pref4,pref5;
		private String p_hand1, p_hand2;
		private String d_hand1, d_hand2;
		private String playerRole,dealerRole;

}
