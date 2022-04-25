package be.intec.models;

import be.intec.models.security.Authority;
import be.intec.models.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id", nullable = false, updatable = false)
     Long id;
     String username;
     String password;
     String firstName;
     String lastName;

    @Column(name="email", nullable = false, updatable = false)
     String email;
     String phone;
     boolean enabled=true;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private ShoppingCart shoppingCart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserShipping> userShippingList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserPayment> userPaymentList;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<UserRole> userRoles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
            Set<GrantedAuthority> authorites = new HashSet<>();
            userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));

            return authorites;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
