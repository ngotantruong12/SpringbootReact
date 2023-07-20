package ntt.com.example.demo_SpringBoot_Web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ntt.com.example.demo_SpringBoot_Web.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByUsername(String name);
	
	Boolean existsByUsername (String name);
	
	Boolean existsByEmail (String name);

	

}
