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
public class ChangeController {

	@GetMapping("/change")
	public String forgot(Model model) {
		return "change";
	}

	@RequestMapping(value = "/change" , method = RequestMethod.POST)
  	public String registerSubmit(@RequestParam(value = "email", required = false) String email,
                                 @RequestParam(value = "oldpassword", required = false) String oldpassword,
                                 @RequestParam(value = "newpassword", required = false) String newpassword,
                                 @RequestParam(value = "repassword", required = false) String repassword,
								 Model model) {
		boolean flag=false;
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject(); 
			if(u.getEmail().equals(email) && u.getPassword().equals(oldpassword) && newpassword.equals(repassword))
			{
				u.setPassword(newpassword);
				flag=true;
			}
			model.addAttribute("user",u);
			oi.close();
			fi.close();		
            FileOutputStream f = new FileOutputStream(new File("userData.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(u);
			o.close();
			f.close();
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(flag)return "login";
		return "error2";
  	}

}
