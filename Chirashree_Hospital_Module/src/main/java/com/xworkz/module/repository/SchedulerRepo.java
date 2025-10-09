package com.xworkz.module.repository;

import java.time.LocalDateTime;

public interface SchedulerRepo {

    void clearExpiredOtps(LocalDateTime expiryTime);
}
