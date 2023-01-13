package com.practice.web.repositories;

import com.practice.web.entities.Menu;
import com.practice.web.entities.keys.MenuPK;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends RepositoryBase<Menu, MenuPK> {

    Optional<Menu> findByOrgAndRequestAndActive(String org, String request, String active);

}
