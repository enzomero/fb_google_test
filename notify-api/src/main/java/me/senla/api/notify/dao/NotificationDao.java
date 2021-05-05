package me.senla.api.notify.dao;

import me.senla.api.notify.model.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationDao extends CrudRepository<Notification, Long> {
    List<Notification> findByPhone(Pageable pageable, String phone);
}
