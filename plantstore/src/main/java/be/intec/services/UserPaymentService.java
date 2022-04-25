package be.intec.services;

import be.intec.models.UserPayment;

public interface UserPaymentService {

    UserPayment findById(Long id);

    void deleteById(Long id);
}
