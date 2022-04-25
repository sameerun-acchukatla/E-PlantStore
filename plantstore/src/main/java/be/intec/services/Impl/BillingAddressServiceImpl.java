package be.intec.services.Impl;

import be.intec.models.BillingAddress;
import be.intec.models.UserBilling;
import be.intec.services.BillingAddressService;
import org.springframework.stereotype.Service;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

    @Override
    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {

        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
        billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipcode(userBilling.getUserBillingZipcode());

        return billingAddress;
    }
}
