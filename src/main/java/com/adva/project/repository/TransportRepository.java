package com.adva.project.repository;

import com.adva.project.model.Announcement;
import com.adva.project.model.TransportImpl;
import com.adva.project.model.TypeOfTransport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TransportRepository extends JpaRepository<TransportImpl, Integer> {
       List<? extends Announcement> findByTypeInAndMileageGreaterThanEqualAndMileageLessThanEqualAndPriceGreaterThanEqualAndPriceLessThanEqual(
               Set<TypeOfTransport> type, double minMileage, double maxMileage, int minPrice, int maxPrice);
       List<? extends Announcement> findByEmail(String email);
}
