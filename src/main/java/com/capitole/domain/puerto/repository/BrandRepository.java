package com.capitole.domain.puerto.repository;

import com.capitole.infrastructure.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<BrandEntity, Long>  {
}
