package spending.management.app.dto.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessageDetail<T> {

	@Builder.Default
	private String message = "Thành công";
	
	@Builder.Default
	private HttpStatus status = HttpStatus.OK;
	
	@Builder.Default
	private int errorCode = 0;
	
	@Builder.Default
	private String errorMessage = ""; 
	
	private T data;
	
	
}