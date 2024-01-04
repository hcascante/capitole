package com.capitole.domain.repository;

import com.capitole.domain.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long>  {
}
