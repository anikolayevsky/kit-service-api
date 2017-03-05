package com.brickly.kit.service.api.services;

import com.brickly.kit.service.api.domain.Kit;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
public interface KitService {
    Kit[] findAll();
    Kit[] findBySku(String sku);
    Kit findOne(Integer id);
    Kit save(com.brickly.kit.service.api.domain.Kit kit);
    void delete(Integer id);
}
