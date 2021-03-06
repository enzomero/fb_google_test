package me.senla.api.registration.service;

import me.senla.api.registration.dao.RegistrationDao;
import me.senla.api.registration.dto.StatRowDto;
import me.senla.api.registration.model.Registration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private final RegistrationDao registrationDao;

    public RegistrationServiceImpl(final RegistrationDao registrationDao) {
        this.registrationDao = registrationDao;
    }

    @Override
    public List<Registration> getRegistrations() {
        return StreamSupport.stream(registrationDao.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Set<String> isRegistered(final long phone) {
        String phoneAsString = String.valueOf(phone);
        return registrationDao.findByPhone(phoneAsString)
                .stream()
                .map(Registration::getToken)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<StatRowDto> getVersionsApps() {
        return registrationDao.findDistinctAppVersions()
                .stream()
                .map(s -> new StatRowDto().setVersion(s))
                .map(statRowDto -> statRowDto
                        .setNumberOfRegistration(
                                registrationDao.countAllByAppVersion(statRowDto.getVersion())))
                .map(statRowDto -> statRowDto.setNumberOfUniqPhones(
                        registrationDao.findDistinctPhoneByAppVersion(statRowDto.getVersion())))
                .collect(Collectors.toSet());
    }
}
