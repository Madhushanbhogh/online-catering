package com.tech.major.controller;
import java.util.Optional;
import java.io.IOException; 
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tech.major.dto.ProductDTO;
import com.tech.major.global.DeletedProductData;
import com.tech.major.model.Category;
import com.tech.major.model.CustomUserDetail;
import com.tech.major.model.DeletedProdect;
import com.tech.major.model.Product;
import com.tech.major.repository.UserRepository;
import com.tech.major.service.CategoryService;
import com.tech.major.service.CustomUserDetailService;
import com.tech.major.service.DeletedProdectService;
import com.tech.major.service.ProductService;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService; // we auto wire category servie so that we can store data in this oject
	@Autowired
	ProductService productService;
	@Autowired
	DeletedProdectService deletedProdectService;
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());// it will return a list ,,, we are running loop in list
		
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		
		model.addAttribute("category", new Category()); // 1st category is a key to show data into frontend and secong a new Category with will be added to the key category
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {// this category will take data from frontend and store in category object.
		categoryService.addCategory(category); // here we store data from frontend
		
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	} 
	//solve this error in video 6
    @GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
	Optional<Category> category =  categoryService.getCategoryById(id);
	if(category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
					}else
			return"404";
		
	}
	
	//product section
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products",productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";
	}
	@PostMapping("admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName) throws IOException{
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}
		else
		{
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
	
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id,Model model) {
		DeletedProductData.deleletedProducts.add(productService.getProductById(id).get());
		System.out.println(DeletedProductData.deleletedProducts);
		//Product product = productService.getProductById(id).get();
		//DeletedProdect deletedProdect= new DeletedProdect();
		//deletedProdectService.addProduct(product);
		productService.removeProductById(id);
		return "redirect:/admin/products";
	} 
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId((product.getCategory().getId()));
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight((product.getWeight()));
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
		
		return "productsAdd";
	}
	@GetMapping("/admin/deletedproducts")
	public String deletedprodect() {
		return "deletedprodect";
	}
//	@GetMapping("/admin/userdata")
//	public String userdata( Model model,CustomUserDetail cu)
//	{
//		
//		//System.out.print(customUserDetailService.getAllUsers());
//		//model.addAttribute("userdata",);
//		return "userdata";
//	}
}



























