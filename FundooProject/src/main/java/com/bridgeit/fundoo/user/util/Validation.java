package com.bridgeit.fundoo.user.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Validation {
	
	 private static Pattern emailNamePtrn = Pattern.compile(
			    "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			     
			     public  boolean validateEmailAddress(String email){
			         
			        Matcher mtch = emailNamePtrn.matcher(email);
			        if(mtch.matches()){
			            return true;
			        }
			        return false;
			    }
}
