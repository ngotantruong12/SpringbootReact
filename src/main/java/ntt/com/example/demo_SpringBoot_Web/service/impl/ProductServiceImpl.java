package ntt.com.example.demo_SpringBoot_Web.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestProduct;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.ResponseProduct;
import ntt.com.example.demo_SpringBoot_Web.entity.CategoryEntity;
import ntt.com.example.demo_SpringBoot_Web.entity.ProductEntity;
import ntt.com.example.demo_SpringBoot_Web.exception.NotFoundException;
import ntt.com.example.demo_SpringBoot_Web.mapper.ProductMapper;
import ntt.com.example.demo_SpringBoot_Web.repository.CategoryRepo;
import ntt.com.example.demo_SpringBoot_Web.repository.ProductRepo;
import ntt.com.example.demo_SpringBoot_Web.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	
	@Override
	public List<ResponseProduct> getALl() {
		return ProductMapper.ToProductDto(productRepo.findAll());
	}
//
	@Override
	public ResponseProduct findOneByID(Long id) {
		Optional<ProductEntity> product = productRepo.findById(id);
		if(!product.isPresent()) {
			throw new NotFoundException("Not found Product with id: " + id);
		}
		return ProductMapper.ToProductDto(product.get());
	}

	@Override
	public ResponseProduct create(RequestProduct dto) { // trong RequestProduct có chứa categoryId
		// check xem categoryId có tồn tại k
		Optional<CategoryEntity> categoryEntity = categoryRepo.findById(dto.getCategory_id());
		if(!categoryEntity.isPresent()) {
			throw new NotFoundException("Not found Category with id: " + dto.getCategory_id());
		}
		//categoryId tồn tại thì mapper trong funtion "ToProductEntity" trong file ProductMapper
		// khi lưu thành công thì nó sẽ trả ra entity, nhie
		return ProductMapper.ToProductDto(productRepo.save(ProductMapper.ToProductEntity(dto, categoryEntity.get())));
	}

	@Override
	public ResponseProduct updateProduct(RequestProduct dto, Long id) {
		Optional<ProductEntity> oldProductEntity = productRepo.findById(id);
		if(!oldProductEntity.isPresent()) {
			throw new NotFoundException("Not found Product with id: " + id);
		}
		
		Optional<CategoryEntity> categoryEntity = categoryRepo.findById(dto.getCategory_id());
		if(!categoryEntity.isPresent()) {
			throw new NotFoundException("Not found Category with id: " + dto.getCategory_id());
		}
		
		return ProductMapper.ToProductDto(productRepo.save(ProductMapper.ToCategoryEntity(oldProductEntity.get(), dto, categoryEntity.get())));
	}
//
	@Override
	public void deleteProduct(Long id) {
		Optional<ProductEntity> oldProductEntity = productRepo.findById(id);
		if(!oldProductEntity.isPresent()) {
			throw new NotFoundException("Not found Product with id: " + id );
		}
		
		productRepo.deleteById(id);
		
	}
//
	@Override
	public List<ResponseProduct> findProductByCategory(Long id) {
		List<ProductEntity> oldProductEntity = productRepo.findByCategoryId(id);
		if(oldProductEntity.isEmpty()) {
			throw new NotFoundException("Not found Product with categoryId: " + id );
		}
				
		return ProductMapper.ToProductDto(productRepo.findByCategoryId(id));
	}

}
