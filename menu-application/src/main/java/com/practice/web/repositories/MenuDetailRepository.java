package com.practice.web.repositories;

import com.practice.web.entities.MenuDetail;
import com.practice.web.entities.keys.MenuDetailPK;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuDetailRepository extends RepositoryBase<MenuDetail, MenuDetailPK> {

    Optional<MenuDetail> findByOrgAndRequestAndLangAndActive(String org, String request, String lang, String active);

}
