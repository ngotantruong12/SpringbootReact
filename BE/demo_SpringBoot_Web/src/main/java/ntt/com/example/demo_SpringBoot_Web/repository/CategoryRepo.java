package ntt.com.example.demo_SpringBoot_Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ntt.com.example.demo_SpringBoot_Web.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>{

}
