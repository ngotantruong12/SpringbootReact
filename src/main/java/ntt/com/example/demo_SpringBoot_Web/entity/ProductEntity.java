package ntt.com.example.demo_SpringBoot_Web.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@Table(name = "Product")
public class ProductEntity  extends BaseEntity {

	
//	@JsonIgnoreProperties("category")
//    @JsonProperty("category_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable=false)
	private CategoryEntity category;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "description")
	private String description;
	
}
