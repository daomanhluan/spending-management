package spending.management.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Entity 
@Table(name = "role")
@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
    @GeneratedValue
    private int id;
	
	private String code;
	
	private String description;
	
	@Column(name = "create_date")
    private Date createDate;
	
	@ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//	@Default
    private Set<User> users  ;
}
