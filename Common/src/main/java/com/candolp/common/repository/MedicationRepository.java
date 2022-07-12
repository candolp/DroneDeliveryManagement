package com.candolp.common.repository;

import com.candolp.common.models.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Medication findByCode(String code);
    List<Medication> findByWeight(long weight);
    Medication findByName(String name);
    List<Medication> findAllByCodeContaining(String code);
    List<Medication> findAllByNameContaining(String code);
    List<Medication> findAllByWeightGreaterThan(long weight);
    List<Medication> findAllByWeightLessThan(long weight);
    List<Medication> findAllByIdIn(List<Long> ids);
    List<Medication> findAllByCodeIn(List<String> codes);
}