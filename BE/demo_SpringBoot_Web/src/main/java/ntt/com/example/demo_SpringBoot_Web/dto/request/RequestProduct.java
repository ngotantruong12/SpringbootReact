package ntt.com.example.demo_SpringBoot_Web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestProduct {
	
	@NotNull( message = "category_id must not NULL !")
//	@NotBlank( message = "Category_id must not Blank !")
	private Long category_id;
	
	@NotNull( message = "Name must not NULL !")
	@NotBlank( message = "Name must not Blank !")
    @Size(min = 2, message = "Name should have at least 2 characters")
	private String name;
	
	@NotNull( message = "Price must not NULL !")
//	@NotBlank( message = "price must not Blank !")
	@Min(value = 1000, message = "Price should not be less than 1000")
	private Long price;
	
	@NotNull( message = "Image must not NULL !")
	@NotBlank( message = "Image must not Blank !")
	private String description;
	
}
