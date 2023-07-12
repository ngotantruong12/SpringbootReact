package ntt.com.example.demo_SpringBoot_Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestProduct;
import ntt.com.example.demo_SpringBoot_Web.entity.ResponeObject;
import ntt.com.example.demo_SpringBoot_Web.service.impl.ProductServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping (path = "/product")
public class ProductController {

	@Autowired
	private ProductServiceImpl productService;
	
	
	@GetMapping("")
	ResponseEntity<ResponeObject> findAllCategory(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Query Product Sucess ", productService.getALl()));
	}
//	
//	
	@GetMapping("/{id}")
	ResponseEntity<ResponeObject> findOneCategoryById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Query Product Sucess ", productService.findOneByID(id)));
	}
//	
	// get product with category id
	@GetMapping("/{id}/category")
	ResponseEntity<ResponeObject> nuwll(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Query Product Sucess ", productService.findProductByCategory(id)));
	}
	
	
	@PostMapping()
	ResponseEntity<ResponeObject> createProduct(@Valid @RequestBody RequestProduct dto){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponeObject("OK", "Create Product Sucess ", productService.create(dto)));
	}
	
	@PutMapping("/{id}")
	ResponseEntity<ResponeObject> updateCategory(@Valid @RequestBody RequestProduct dto , @PathVariable("id") Long id){
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Update Product Sucess ", productService.updateProduct(dto, id)));
	}
//	
	@DeleteMapping("/{id}")
	ResponseEntity<ResponeObject> deleteCategory (@PathVariable("id") Long id){	
		productService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponeObject("OK", "Delete Product Sucess ", ""));
		}
}
