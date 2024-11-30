package com.rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rh.model.BackOffice.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    
}
