package ntt.com.example.demo_SpringBoot_Web.mapper;

import java.util.ArrayList;
import java.util.List;

import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestProduct;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.ResponseProduct;
import ntt.com.example.demo_SpringBoot_Web.entity.CategoryEntity;
import ntt.com.example.demo_SpringBoot_Web.entity.ProductEntity;

public class ProductMapper {

//private Long id;
	
//	private Long category_id;
//	
//	private String name;
//	
//	private Long price;
//	
//	private String Description;
	
	
	public static ResponseProduct ToProductDto(ProductEntity entity) {
		ResponseProduct dto = new ResponseProduct();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setCategory_id(entity.getCategory().getId());
		dto.setPrice(entity.getPrice());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setCreatedDate(entity.getCreatedDate());
		return dto;
		
	}
	
	// create
	public static ProductEntity ToProductEntity(RequestProduct dto , CategoryEntity entityCaregory) {
		ProductEntity entity = new ProductEntity();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setCategory(entityCaregory);
		entity.setPrice(dto.getPrice());
		return entity;
		
	}
	 // findAll
	public static List<ResponseProduct> ToProductDto(List<ProductEntity> entitys ) {
		List<ResponseProduct> listProduct =  new ArrayList<>();
		for (ProductEntity entity: entitys) {
			listProduct.add(ToProductDto(entity));
		}
		return listProduct;
		
	}
	
	// Update
	
	public static ProductEntity ToCategoryEntity(ProductEntity oldEntity, RequestProduct dto , CategoryEntity oldCategory) {
//		ProductEntity newEntity = new ProductEntity();
		oldEntity.setName(dto.getName());
		oldEntity.setDescription(dto.getDescription());
		oldEntity.setPrice(dto.getPrice());
		oldEntity.setCategory(oldCategory);
		return oldEntity;
		
	}
}
