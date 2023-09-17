package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class CartController {

	@GetMapping("/cart")
	public String cart(Model model) {
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject(); 
            fi = new FileInputStream(new File("cartData.txt"));
            oi = new ObjectInputStream(fi);
			PurchasedProducts p = (PurchasedProducts) oi.readObject(); 
            System.out.println(p+" "+u);
			model.addAttribute("products",p.cart.get(u.getEmail()));
            for(int i=0;i<p.cart.get(u.getEmail()).size();i++)
            {
            	System.out.println(p.cart.get(u.getEmail()).get(i));
            }
			oi.close();
			fi.close();	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "cart";
	}

	@RequestMapping(value = "/remove" , method = RequestMethod.POST)
  	public String registerSubmit(@RequestParam(value = "productID", required = false) String productID,
								 Model model) {
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject();
			fi = new FileInputStream(new File("cartData.txt"));
			oi = new ObjectInputStream(fi);
			PurchasedProducts p = (PurchasedProducts) oi.readObject();
			if(productID!=null)p.removeFromCart(u.getEmail(), productID);
			FileOutputStream f = new FileOutputStream(new File("cartData.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(p);
			model.addAttribute("products",p.cart.get(u.getEmail()));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "cart";
  	}

	  @RequestMapping(value = "/payment" , method = RequestMethod.POST)
  	public String registerSubmit(Model model) {
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject();
			fi = new FileInputStream(new File("cartData.txt"));
			oi = new ObjectInputStream(fi);
			PurchasedProducts p = (PurchasedProducts) oi.readObject();
			float amount=0;
			for(int i=0;i<p.cart.get(u.getEmail()).size();i++)
			{
				if(p.cart.get(u.getEmail()).get(i)[0].equals("product1"))amount+=10;
				if(p.cart.get(u.getEmail()).get(i)[0].equals("product2"))amount+=20;
				if(p.cart.get(u.getEmail()).get(i)[0].equals("product3"))amount+=30;
			}
			Amount a=new Amount();
			a.setAmount(amount);
			model.addAttribute("amount",a);
			oi.close();
			fi.close();	
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "payment";
  	}

}
