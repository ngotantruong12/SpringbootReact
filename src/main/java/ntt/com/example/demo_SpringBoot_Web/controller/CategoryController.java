package ntt.com.example.demo_SpringBoot_Web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestCategory;
import ntt.com.example.demo_SpringBoot_Web.entity.ResponeObject;
import ntt.com.example.demo_SpringBoot_Web.service.impl.CategoryServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping (path = "/category")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryService;
	
	@GetMapping("")
	ResponseEntity<ResponeObject> findAllCategory(){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Query Category Sucess ", categoryService.getALl()));
	}
//	
//	
	@GetMapping("/{id}")
	ResponseEntity<ResponeObject> findOneCategoryById(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Query Category Sucess ", categoryService.getOneByID(id)));
	}
	
	
	@PostMapping()
	@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<ResponeObject> createCategory(@Valid @RequestBody RequestCategory request){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponeObject("OK", "Create Category Sucess ", categoryService.create(request)));
	}
//	
	@PutMapping("/{id}")
	@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<ResponeObject> updateCategory(@Valid @RequestBody RequestCategory dto , @PathVariable("id") Long id){
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponeObject("OK", "Update Category Sucess ", categoryService.updateCategory(dto, id)));
	}
////	
	@DeleteMapping("/{id}")
	@PreAuthorize(" hasRole('MODERATOR') or hasRole('ADMIN')")
	ResponseEntity<ResponeObject> deleteCategory (@PathVariable("id") Long id){	
		categoryService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponeObject("OK", "Update Category Sucess ", ""));
		}
}
