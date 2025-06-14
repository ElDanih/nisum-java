package com.nisum.jpa.repository;


import com.nisum.jpa.entities.PhoneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneEntityRepository extends CrudRepository<PhoneEntity, Long> {
}
