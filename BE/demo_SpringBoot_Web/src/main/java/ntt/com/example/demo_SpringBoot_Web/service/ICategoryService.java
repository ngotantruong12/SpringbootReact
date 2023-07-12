package ntt.com.example.demo_SpringBoot_Web.service;

import java.util.List;

import ntt.com.example.demo_SpringBoot_Web.dto.request.RequestCategory;
import ntt.com.example.demo_SpringBoot_Web.dto.respone.ResponseCategory;

public interface ICategoryService {
	List<ResponseCategory> getALl();
	ResponseCategory getOneByID(Long id);
	ResponseCategory create(RequestCategory request);
	ResponseCategory updateCategory(RequestCategory dto, Long id);
	void deleteCategory(Long id);

}
