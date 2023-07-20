package ntt.com.example.demo_SpringBoot_Web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
public class RoleEntity extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERoleEntity name;

	public RoleEntity() {
		super();
	}

	public RoleEntity(ERoleEntity name) {
		super();
		this.name = name;
	}

	public ERoleEntity getName() {
		return name;
	}

	public void setName(ERoleEntity name) {
		this.name = name;
	}
	
	
	
	
}
