package be.intec.services.Impl;

import be.intec.models.UserPayment;
import be.intec.models.UserShipping;
import be.intec.repositories.UserShippingRepository;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserShippingServiceImplTest {

    UserShippingRepository userShippingRepository = mock(UserShippingRepository.class);
    private UserShippingServiceImpl userShippingService = new UserShippingServiceImpl(userShippingRepository);

    @Test
    public void testFindByIdReturnsValidUserShipping() {
        UserShipping userShipping = new UserShipping();
        when(userShippingRepository.findById(1L)).thenReturn(Optional.of(userShipping));

        UserShipping actualUserShipping = userShippingService.findById(1L);

        assertEquals(userShipping,actualUserShipping);

    }

    @Test
    public void testFindByIdReturnsNull(){
        when(userShippingRepository.findById(1L)).thenReturn(Optional.ofNullable(null));
        UserShipping actualUserShipping = userShippingService.findById(1L);
        assertEquals(null,actualUserShipping);
    }

    @Test
    public void testDeleteById() {
        userShippingService.deleteById(1L);
        verify(userShippingRepository).deleteById(1L);

    }
}