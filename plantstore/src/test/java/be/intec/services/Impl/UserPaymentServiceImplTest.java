package be.intec.services.Impl;

import be.intec.models.User;
import be.intec.models.UserPayment;
import be.intec.repositories.UserPaymentRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserPaymentServiceImplTest {

    UserPaymentRepository userPaymentRepository = mock(UserPaymentRepository.class);
    private  UserPaymentServiceImpl userPaymentService = new UserPaymentServiceImpl(userPaymentRepository);

    @Test
    public void testFindByIdReturnsValidUserPayment() {
        UserPayment userPayment = new UserPayment();

        when(userPaymentRepository.findById(1L)).thenReturn(Optional.of(userPayment));

        UserPayment actualUserPayment = userPaymentService.findById(1L);

        assertEquals(userPayment,actualUserPayment);
    }

    @Test
    public void testFindByIdReturnsNull(){

        when(userPaymentRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        UserPayment actualUserPayment = userPaymentService.findById(1L);
        assertEquals(null,actualUserPayment);

    }
    @Test
    public void testDeleteById() {

        userPaymentService.deleteById(1L);
        verify(userPaymentRepository).deleteById(1L);

    }
}