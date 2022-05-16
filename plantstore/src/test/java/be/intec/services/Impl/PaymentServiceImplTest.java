package be.intec.services.Impl;

import be.intec.models.Payment;
import be.intec.models.UserPayment;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceImplTest {
    PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Test
    public void testSetByUserPayment() {

        UserPayment userPayment = new UserPayment();
        Payment payment = new Payment();
        userPayment.setType("visa");
        userPayment.setHolderName("Sameerun");
        userPayment.setExpiryMonth(6);
        userPayment.setExpiryYear(2023);
        userPayment.setCvc(567);

        Payment updatedPayment = paymentService.setByUserPayment(userPayment, payment);

        assertThat(updatedPayment.getType()).isEqualTo("visa");
        assertThat(updatedPayment.getHolderName()).isEqualTo("Sameerun");
        assertThat(updatedPayment.getExpiryMonth()).isEqualTo(6);
        assertThat( updatedPayment.getExpiryYear()).isEqualTo(2023);
        assertThat( updatedPayment.getCvc()).isEqualTo(567);
    }
}