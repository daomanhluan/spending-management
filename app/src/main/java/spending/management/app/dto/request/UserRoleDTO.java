package spending.management.app.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleDTO {

	private Long userId;
	private List<Integer> roleId;
}
