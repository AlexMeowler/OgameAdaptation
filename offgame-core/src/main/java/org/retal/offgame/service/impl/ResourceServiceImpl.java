package org.retal.offgame.service.impl;

import lombok.RequiredArgsConstructor;
import org.retal.offgame.entity.Resources;
import org.retal.offgame.repository.ResourcesRepository;
import org.retal.offgame.service.AbstractCrudService;
import org.retal.offgame.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ResourceServiceImpl extends AbstractCrudService<Resources, Long> implements ResourcesService {

    private final ResourcesRepository resourcesRepository;

    @Override
    protected CrudRepository<Resources, Long> getRepository() {
        return resourcesRepository;
    }
}
