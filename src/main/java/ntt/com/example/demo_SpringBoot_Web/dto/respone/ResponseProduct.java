package ntt.com.example.demo_SpringBoot_Web.dto.respone;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ResponseProduct  extends BaseResponse {

	private Long id;
	
	private Long category_id;
	
	private String name;
	
	private Long price;
	
	private String description;
	
}
