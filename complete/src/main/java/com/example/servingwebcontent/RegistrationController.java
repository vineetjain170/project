package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.regex.*;    
import java.util.*;    
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class RegistrationController {

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}

	@PostMapping("/register")
  	public String registerSubmit(@ModelAttribute User user, Model model) {
    	model.addAttribute("user", user);
		User u=(User)model.asMap().get("user");
		String name=u.getFullname();
		String email=u.getEmail();
		String password1=u.getPassword();
		String password2=u.getConfirm_password();
		int specialCount=0;
		if(name.length()>100 || email.length()>100 || !password1.equals(password2) || password1.length()<8)return "error";
		for(char ch:name.toCharArray())if(!((ch>='a' && ch<='z') || (ch>='A' && ch<='Z') || ch==' '))return "error";  
		if(!Pattern.compile("^(.+)@(.+)$").matcher(email).matches())return "error";
		for(char ch:password1.toCharArray())if(!Character.isLetterOrDigit(ch​))specialCount++;
		if(specialCount<1)return "error";
		try{
			FileOutputStream f = new FileOutputStream(new File("userData.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(u);
			o.close();
			f.close();
			System.out.println("Data written to file");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		} 
    	return "catalog";
  	}

}
