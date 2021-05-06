package me.senla.api.registration.dao;

import me.senla.api.registration.model.Registration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RegistrationDao extends CrudRepository<Registration, Long> {
    Optional<Registration> removeByToken(final String token);
    Set<Registration> findByPhone(final String token);
}
