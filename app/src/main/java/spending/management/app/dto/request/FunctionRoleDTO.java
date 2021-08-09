package spending.management.app.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionRoleDTO {

	private int functionId;
	private List<Integer> ids;
}
