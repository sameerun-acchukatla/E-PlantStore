package be.intec.services.Impl;

import be.intec.models.BillingAddress;
import be.intec.models.UserBilling;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BillingAddressServiceImplTest {

    @Test
    public void testSetByUserBilling() {
        BillingAddressServiceImpl billingAddressService = new BillingAddressServiceImpl();


        UserBilling userBilling = new UserBilling();
        BillingAddress billingAddress = new BillingAddress();

        userBilling.setUserBillingName("Billing1");
        userBilling.setUserBillingStreet1("Boulevard Louis Schmidt Laan");
        userBilling.setUserBillingStreet2("boulevard du midi");
        userBilling.setUserBillingState("Brussels");
        userBilling.setUserBillingCountry("Belgium");
        userBilling.setUserBillingZipcode("1040");

        BillingAddress updatedBillingAddress = billingAddressService.setByUserBilling(userBilling, billingAddress);

        assertThat(updatedBillingAddress.getBillingAddressName()).isEqualTo("Billing1");
        assertThat(updatedBillingAddress.getBillingAddressStreet1()).isEqualTo("Boulevard Louis Schmidt Laan");
        assertThat(updatedBillingAddress.getBillingAddressStreet2()).isEqualTo("boulevard du midi");
        assertThat(updatedBillingAddress.getBillingAddressState()).isEqualTo("Brussels");
        assertThat(updatedBillingAddress.getBillingAddressCountry()).isEqualTo("Belgium");
        assertThat(updatedBillingAddress.getBillingAddressZipcode()).isEqualTo("1040");

    }
}