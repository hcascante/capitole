package com.capitole.infrastructure.repository;

import com.capitole.domain.repository.PriceRepositoryCustom;
import com.capitole.domain.model.Price;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PriceRepositoryImpl implements PriceRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    public List<Price> findApplicablePrices(Long brandId, Long productId, LocalDateTime date) {

        return entityManager.createQuery(
                        "SELECT p FROM PRICES p WHERE p.brandId = :brandId " +
                                "AND p.productId = :productId " +
                                "AND p.startDate <= :date " +
                                "AND p.endDate >= :date " +
                                "ORDER BY p.priority DESC", Price.class)
                .setParameter("brandId", brandId)
                .setParameter("productId", productId)
                .setParameter("date", date)
                .getResultList();
    }
}