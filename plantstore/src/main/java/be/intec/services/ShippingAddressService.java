package be.intec.services;

import be.intec.models.ShippingAddress;
import be.intec.models.UserShipping;

public interface ShippingAddressService {

    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
