package com.swallaby.openrestapi.customer.userid.service;

import com.swallaby.openrestapi.entity.CustomerUser;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface CustomerUserSaveService {

    public Mono<CustomerUser> save(CustomerUser customerUser);
}
