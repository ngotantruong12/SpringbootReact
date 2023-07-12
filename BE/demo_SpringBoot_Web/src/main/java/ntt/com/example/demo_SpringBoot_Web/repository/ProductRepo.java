package ntt.com.example.demo_SpringBoot_Web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntt.com.example.demo_SpringBoot_Web.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Long>{

	List<ProductEntity> findByCategoryId(Long id);
}
