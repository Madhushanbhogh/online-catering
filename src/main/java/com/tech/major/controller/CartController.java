package com.tech.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tech.major.global.GlobalDara;
import com.tech.major.global.Myoder;
import com.tech.major.model.Product;
import com.tech.major.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalDara.cart.add(productService.getProductById(id).get());
		Myoder.cart.add(productService.getProductById(id).get());
		return "redirect:/cart";
	}
	@GetMapping("cart")
	public String cartGet(Model model) {
		double gst;
		model.addAttribute("cartCount", GlobalDara.cart.size());
		model.addAttribute("bftotal",  GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum()-(GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum()*0.18));
		model.addAttribute("total", GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("gst", GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum()*0.18);
		model.addAttribute("cart",GlobalDara.cart);
		return "cart";
	}
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalDara.cart.remove(index);
		Myoder.cart.remove(index);
		return "redirect:/cart";
	
	}
	@GetMapping("checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";	
	}
	
	@PostMapping("payNow")
	public String payNow(Model model) {
		model.addAttribute("cartCount", GlobalDara.cart.size());
		model.addAttribute("total", GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalDara.cart);
		model.addAttribute("orderid",122);
		return "orderPlaced";
	}
	@GetMapping("/payNow/shopagin")
	public String shopagin() {
		GlobalDara.cart.clear();
		return "redirect:/shop";
	
	}
	


}
