package me.senla.api.registration.dao;

import me.senla.api.registration.model.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationDao extends CrudRepository<Registration, Long> {
}
