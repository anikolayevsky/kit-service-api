package com.blckly.kit.service.api.respositories;

import com.blckly.kit.service.api.domain.Kit;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by alexnikolayevsky on 5/24/16.
 */
public interface KitRepository extends CrudRepository<com.blckly.kit.service.api.domain.Kit, Integer> {
    Kit[] findBySku(String sku);

}
