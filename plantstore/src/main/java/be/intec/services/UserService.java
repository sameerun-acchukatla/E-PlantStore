package be.intec.services;

import be.intec.models.User;
import be.intec.models.UserBilling;
import be.intec.models.UserPayment;
import be.intec.models.UserShipping;
import be.intec.models.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    User findByUsername(String username);

    User findByEmail (String email);

    User findById(Long id);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

    void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void setUserDefaultShipping(Long userShippingId, User user);

}
