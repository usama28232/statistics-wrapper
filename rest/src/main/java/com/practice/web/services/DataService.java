package com.practice.web.services;

import com.practice.annotations.Asynchronus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
public class DataService {

    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Asynchronus
    public CompletableFuture<BigDecimal> getSelectResult(String org, String req, String view, String query) {
        BigDecimal out = new BigDecimal(0);
        try {
            out = this.getNativeQueryResult(query);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return CompletableFuture.completedFuture(out);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
    BigDecimal getNativeQueryResult(String query) {
        BigDecimal out = (BigDecimal) this.entityManager.createNativeQuery(query).getSingleResult();
        this.entityManager.close();
        return out;
    }
}
