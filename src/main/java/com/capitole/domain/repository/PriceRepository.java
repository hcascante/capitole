package com.capitole.domain.repository;

import com.capitole.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, PriceRepositoryCustom {

}