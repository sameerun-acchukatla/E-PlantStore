package be.intec.services;

import be.intec.models.UserShipping;

public interface UserShippingService {

    UserShipping findById(Long id);

    void deleteById(Long id);
}
