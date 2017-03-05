package com.brickly.kit.service.api.services.impl;

import com.google.common.collect.Iterables;
import com.brickly.kit.service.api.respositories.KitRepository;
import com.brickly.kit.service.api.services.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
@Service
@Transactional
public class KitServiceImpl implements KitService {
    @Autowired
    protected KitRepository repository;

    public com.brickly.kit.service.api.domain.Kit[] findAll() {
        return Iterables.toArray(repository.findAll(), com.brickly.kit.service.api.domain.Kit.class);
    }

    public com.brickly.kit.service.api.domain.Kit[] findBySku(String sku) {
        return repository.findBySku(sku);
    }

    public com.brickly.kit.service.api.domain.Kit findOne(Integer id) {
        return repository.findOne(id);
    }

    public com.brickly.kit.service.api.domain.Kit save(com.brickly.kit.service.api.domain.Kit kit) {
        return repository.save(kit);
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

}
