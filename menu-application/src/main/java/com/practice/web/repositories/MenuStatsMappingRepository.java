package com.practice.web.repositories;

import com.practice.web.entities.MenuStatsMapping;
import com.practice.web.entities.keys.MenuStatsMappingPK;

import java.util.Collection;
import java.util.Optional;

public interface MenuStatsMappingRepository extends RepositoryBase<MenuStatsMapping, MenuStatsMappingPK> {


    Collection<MenuStatsMapping> findAllByOrgAndRequestAndActive(String org, String req, String active);

}
