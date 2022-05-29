package com.tech.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tech.major.global.GlobalDara;
import com.tech.major.global.Myoder;
import com.tech.major.model.User;
import com.tech.major.service.CategoryService;
import com.tech.major.service.CustomUserDetailService;
import com.tech.major.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@GetMapping({"/","/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalDara.cart.size());
		//model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProduct());
		return "shop";
		}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProduct());
		model.addAttribute("cartCount", GlobalDara.cart.size());
		return "shop";
		}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount", GlobalDara.cart.size());
		return "shop";	
		}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id) {
		model.addAttribute("product",productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalDara.cart.size());
		return "viewProduct";	
		}
	@GetMapping("/myorder")
	public String vieworder(Model model) {
		model.addAttribute("cart",Myoder.cart);
		return "myorder";
	}
	
//	@GetMapping("/profile")
//	public String profile() {
//	
//		return "profile";
//	}
}
