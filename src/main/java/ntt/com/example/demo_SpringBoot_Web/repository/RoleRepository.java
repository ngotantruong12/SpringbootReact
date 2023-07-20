package ntt.com.example.demo_SpringBoot_Web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ntt.com.example.demo_SpringBoot_Web.entity.ERoleEntity;
import ntt.com.example.demo_SpringBoot_Web.entity.RoleEntity;


public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

	 Optional<RoleEntity> findByName(ERoleEntity name);
}
