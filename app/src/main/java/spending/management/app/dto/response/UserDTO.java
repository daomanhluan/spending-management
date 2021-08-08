package spending.management.app.dto.response;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import spending.management.app.model.Role;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long id;
    private String username;
    private double balance;
    private String avatar;
    private Date createDate;
    @Default
    private Set<Role> roles= new HashSet<>();
}
