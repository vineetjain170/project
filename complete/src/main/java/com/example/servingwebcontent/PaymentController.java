package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PaymentController {

	@GetMapping("/payment")
	public String payment(@ModelAttribute Amount amount, Model model) {
		model.addAttribute("amount", amount);
		return "payment";
	}

}
