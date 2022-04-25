package be.intec.services;

import be.intec.models.Payment;
import be.intec.models.UserPayment;

public interface PaymentService {

    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
