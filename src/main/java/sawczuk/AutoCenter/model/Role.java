package sawczuk.AutoCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "app_role")
public class Role implements GrantedAuthority {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "role_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_id_gen", sequenceName = "app_role_seq", initialValue = 1, allocationSize = 1)
    private Long id;
    @NotNull
    @Column(name = "role_name")
    private String name;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roles")
    private Set<User> users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}
