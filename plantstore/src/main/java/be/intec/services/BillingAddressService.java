package be.intec.services;

import be.intec.models.BillingAddress;
import be.intec.models.UserBilling;

public interface BillingAddressService {

    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
