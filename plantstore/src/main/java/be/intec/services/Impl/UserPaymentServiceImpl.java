package be.intec.services.Impl;

import be.intec.models.UserPayment;
import be.intec.repositories.UserPaymentRepository;
import be.intec.services.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService {


    private final UserPaymentRepository userPaymentRepository;

    @Autowired
    public UserPaymentServiceImpl(UserPaymentRepository userPaymentRepository) {
        this.userPaymentRepository = userPaymentRepository;
    }

    @Override
    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userPaymentRepository.deleteById(id);
    }
}
