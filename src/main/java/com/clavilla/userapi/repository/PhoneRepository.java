package com.clavilla.userapi.repository;

import com.clavilla.userapi.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
