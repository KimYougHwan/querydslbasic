package com.swallaby.openrestapi.customer.userid.service.impl;

import org.springframework.stereotype.Service;
import com.swallaby.openrestapi.customer.userid.service.CustomerUserSaveService;
import com.swallaby.openrestapi.entity.CustomerUser;
import reactor.core.publisher.Mono;

@Service
public class CustomerUserSaveServiceImpl implements CustomerUserSaveService {

    @Override
    public Mono<CustomerUser> save(CustomerUser customerUser) {

        return null;
    }

}
