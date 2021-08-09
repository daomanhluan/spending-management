package spending.management.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
	@Default
    private Set<User> users = new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER )
    @EqualsAndHashCode.Exclude 
    @ToString.Exclude 
    @JoinTable(name = "functions_role", 
            joinColumns = @JoinColumn(name = "role_id"),  
            inverseJoinColumns = @JoinColumn(name = "functions_id") 
    )
    private Set<Role> functions;
}
