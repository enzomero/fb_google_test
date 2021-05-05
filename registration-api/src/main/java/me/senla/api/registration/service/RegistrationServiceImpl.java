package me.senla.api.registration.service;

import me.senla.api.registration.dao.RegistrationDao;
import me.senla.api.registration.model.Registration;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Boolean isRegistered(final String phone) {
        return registrationDao.findByPhone(phone)
                .map(registration -> registration.getPhone().equals(phone))
                .orElse(Boolean.FALSE);
    }
}
