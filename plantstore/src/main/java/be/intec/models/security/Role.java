package be.intec.models.security;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roleId;

    String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();
}
