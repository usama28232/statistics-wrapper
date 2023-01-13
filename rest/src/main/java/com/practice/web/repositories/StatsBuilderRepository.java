package com.practice.web.repositories;

import com.practice.web.entities.StatsBuilder;
import com.practice.web.entities.keys.StatsBuilderPK;

import java.util.Collection;

public interface StatsBuilderRepository extends RepositoryBase<StatsBuilder, StatsBuilderPK> {
    
    Collection<StatsBuilder> findAllByOrgAndViewAndUserIdAndActive(String org, String view, String userId, String active);
    
}
