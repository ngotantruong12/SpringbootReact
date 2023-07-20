package ntt.com.example.demo_SpringBoot_Web.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "Users",
	uniqueConstraints = {
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email")
	}
)
public class UserEntity extends BaseEntity{
	
	private String username;
	
	private String email;
	
	@Size(max = 120)
	private String password;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable( name = "user_roles",
		joinColumns = @JoinColumn(name = "userId"),
		inverseJoinColumns =  @JoinColumn(name = "roleId")		
	)
	private Set<RoleEntity> roles = new HashSet<>();

	public UserEntity() {
	}
	
	public UserEntity(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
}
