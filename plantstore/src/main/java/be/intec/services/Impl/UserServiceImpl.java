package be.intec.services.Impl;

import be.intec.models.*;
import be.intec.models.security.UserRole;
import be.intec.repositories.RoleRepository;
import be.intec.repositories.UserPaymentRepository;
import be.intec.repositories.UserRepository;
import be.intec.repositories.UserShippingRepository;
import be.intec.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private UserShippingRepository userShippingRepository;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = userRepository.findByUsername(user.getUsername());

        if (localUser != null){
           LOG.info(" user {} already exists. Nothing will be done.",user.getUsername());
        }else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);

            user.setUserShippingList(new ArrayList<UserShipping>());
            user.setUserPaymentList(new ArrayList<UserPayment>());

            localUser = userRepository.save(user);
        }

        return localUser;

        }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {

        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        save(user);

    }

    @Override
    public void updateUserShipping(UserShipping userShipping, User user) {

        userShipping.setUser(user);
        userShipping.setUserShippingDefault(true);
        user.getUserShippingList().add(userShipping);
        save(user);

    }

    @Override
    public void setUserDefaultPayment(Long userPaymentId, User user) {
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();

        for (UserPayment userPayment:userPaymentList) {
            if (userPayment.getId() == userPayment.getId()){
                userPayment.setDefaultPayment(true);
            }else{
                userPayment.setDefaultPayment(false);
            }
            userPaymentRepository.save(userPayment);
        }
    }

    @Override
    public void setUserDefaultShipping(Long userShippingId, User user) {

        List<UserShipping> userShippingList = (List<UserShipping>) userShippingRepository.findAll();

        for (UserShipping userShipping : userShippingList) {
            if(userShipping.getId() == userShippingId) {
                userShipping.setUserShippingDefault(true);
            } else {
                userShipping.setUserShippingDefault(false);
            }
            userShippingRepository.save(userShipping);
        }
    }
}
