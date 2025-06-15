package com.nisum.jpa.phone.repository;


import com.nisum.jpa.phone.data.PhoneData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneData, Long> {
}
