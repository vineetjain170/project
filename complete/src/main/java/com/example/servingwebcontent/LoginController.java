package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = "/login" , method = RequestMethod.POST)
  	public String registerSubmit(@RequestParam(value = "email", required = false) String email,
	  							 @RequestParam(value = "password", required = false) String password,
								 Model model) {
		boolean flag=false;
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject(); 
			if(u.getEmail().equals(email) && u.getPassword().equals(password))flag=true;
			model.addAttribute("user",u);
			oi.close();
			fi.close();		
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(flag)return "catalog";
		return "error1";
  	}

}
