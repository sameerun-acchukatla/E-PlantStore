package be.intec.models.security;

import be.intec.models.User;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;


@Data
@NoArgsConstructor(force = true, access = AccessLevel.PUBLIC)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long userRoleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
     User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(userRoleId, userRole.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "userRoleId=" + userRoleId +
                ", user=" + user +
                ", role=" + role +
                '}';
    }
}
