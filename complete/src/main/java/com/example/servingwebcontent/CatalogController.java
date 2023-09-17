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
public class CatalogController {

	@GetMapping("/catalog")
	public String catalog(Model model) {
		try{
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject();
			model.addAttribute("user",u);
			oi.close();
			fi.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return "catalog";
	}

	@RequestMapping(value = "/catalog" , method = RequestMethod.POST)
  	public String registerSubmit(@RequestParam(value = "product1", required = false) String product1,
	  							 @RequestParam(value = "product2", required = false) String product2,
								 @RequestParam(value = "product3", required = false) String product3,
								 Model model) {
		try{
			FileOutputStream f = new FileOutputStream(new File("cartData.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			PurchasedProducts p=new PurchasedProducts();
			FileInputStream fi = new FileInputStream(new File("userData.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			User u = (User) oi.readObject();
			if(product1!=null && product1.equals("product1"))p.addtoCart(u.getEmail(),"product1","Description of Product 1","10");
			if(product2!=null && product2.equals("product2"))p.addtoCart(u.getEmail(),"product2","Description of Product 2","20");
			if(product3!=null && product3.equals("product3"))p.addtoCart(u.getEmail(),"product3","Description of Product 1","30");
			model.addAttribute("products",p.cart.get(u.getEmail()));
			o.writeObject(p);
			o.close();
			f.close();
			System.out.println("Data written to file");
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println(e.toString());
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return "cart";
  	}

}
