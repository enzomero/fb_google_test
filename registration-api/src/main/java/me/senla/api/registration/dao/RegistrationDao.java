package me.senla.api.registration.dao;

import me.senla.api.registration.model.Registration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RegistrationDao extends CrudRepository<Registration, Long> {
    Optional<Registration> removeByToken(final String token);
    Set<Registration> findByPhone(final String token);
    Long countAllByAppVersion(final String appVersion);
    //Stat
    @Query(value = "SELECT DISTINCT r.app_version FROM registration r", nativeQuery = true)
    Set<String> findDistinctAppVersions();

    @Query(value = "SELECT  COUNT (DISTINCT r.phone) FROM registration r WHERE r.app_version = ?1", nativeQuery = true)
    Long findDistinctPhoneByAppVersion(final String version);
}
