package be.intec.services.Impl;

import be.intec.models.UserShipping;
import be.intec.repositories.UserShippingRepository;
import be.intec.services.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(Long id) {
        return userShippingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {

        userShippingRepository.deleteById(id);
    }
}
