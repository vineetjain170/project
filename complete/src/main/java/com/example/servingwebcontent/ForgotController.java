package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class ForgotController {

	@GetMapping("/forgot")
	public String forgot(Model model) {
		return "forgot";
	}

	@RequestMapping(value = "/forgot" , method = RequestMethod.POST)
  	public String registerSubmit(@RequestParam(value = "full_name", required = false) String full_name,
	  							 @RequestParam(value = "email", required = false) String email,
								 Model model) {
		boolean flag=false;
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject(); 
			if(u.getEmail().equals(email) && u.getFullname().equals(full_name))flag=true;
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
		if(flag)return "change";
		return "error2";
  	}

}
