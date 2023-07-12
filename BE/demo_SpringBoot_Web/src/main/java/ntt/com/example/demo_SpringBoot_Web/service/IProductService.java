package ntt.com.example.demo_SpringBoot_Web.service;

import java.util.List;

import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestProduct;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.ResponseProduct;

public interface IProductService {
	List<ResponseProduct> getALl();
	ResponseProduct findOneByID(Long id);
	ResponseProduct create(RequestProduct dto);
	ResponseProduct updateProduct(RequestProduct dto, Long id);
	void deleteProduct(Long id);
	List<ResponseProduct> findProductByCategory(Long id);
}

