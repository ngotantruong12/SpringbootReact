package ntt.com.example.demo_SpringBoot_Web.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "Category")
public class CategoryEntity  extends BaseEntity {
	
//	@JsonIgnore
	@OneToMany(mappedBy="category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ProductEntity> products;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
}
