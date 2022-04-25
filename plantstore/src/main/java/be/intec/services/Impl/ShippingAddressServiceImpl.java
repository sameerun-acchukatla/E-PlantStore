package be.intec.services.Impl;

import be.intec.models.ShippingAddress;
import be.intec.models.UserShipping;
import be.intec.services.ShippingAddressService;
import org.springframework.stereotype.Service;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {

    @Override
    public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {

       shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
       shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
       shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
       shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
       shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
       shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
       shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());

        return shippingAddress;
    }
}
