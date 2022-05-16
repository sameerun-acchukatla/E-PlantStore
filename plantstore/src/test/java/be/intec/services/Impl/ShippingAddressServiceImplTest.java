package be.intec.services.Impl;

import be.intec.models.ShippingAddress;
import be.intec.models.UserShipping;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


public class ShippingAddressServiceImplTest {

    ShippingAddressServiceImpl shippingAddressService = new ShippingAddressServiceImpl();
    UserShipping userShipping = new UserShipping();
    ShippingAddress shippingAddress = new ShippingAddress();

    @Test
    public void testSetByUserShipping() {

        userShipping.setUserShippingName("Sameerun");
        userShipping.setUserShippingStreet1("Boulevard Louis shmidt 35");
        userShipping.setUserShippingStreet2("Boulevard Midi");
        userShipping.setUserShippingCity("Etterbeek");
        userShipping.setUserShippingCountry("Belgium");
        userShipping.setUserShippingZipcode("1040");

        ShippingAddress updatedShippingAddress = shippingAddressService.setByUserShipping(userShipping, this.shippingAddress);

        assertThat(updatedShippingAddress.getShippingAddressName()).isSameAs("Sameerun");
        assertThat(updatedShippingAddress.getShippingAddressStreet1()).isEqualTo("Boulevard Louis shmidt 35");
        assertThat(updatedShippingAddress.getShippingAddressStreet2()).isEqualTo("Boulevard Midi");
        assertThat(updatedShippingAddress.getShippingAddressCity()).isEqualTo("Etterbeek");
        assertThat(updatedShippingAddress.getShippingAddressCountry()).isEqualTo("Belgium");
        assertThat(updatedShippingAddress.getShippingAddressZipcode()).isEqualTo("1040");

    }
}