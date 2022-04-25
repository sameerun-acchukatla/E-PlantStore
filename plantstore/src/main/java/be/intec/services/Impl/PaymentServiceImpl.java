package be.intec.services.Impl;

import be.intec.models.Payment;
import be.intec.models.UserPayment;
import be.intec.services.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public Payment setByUserPayment(UserPayment userPayment, Payment payment) {

       payment.setType(userPayment.getType());
       payment.setHolderName(userPayment.getHolderName());
       payment.setCardName(userPayment.getHolderName());
       payment.setExpiryMonth(userPayment.getExpiryMonth());
       payment.setExpiryYear(userPayment.getExpiryYear());
       payment.setCvc(userPayment.getCvc());

        return payment;
    }
}
