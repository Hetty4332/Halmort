package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.ChartOfPayments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartOfPaymentsRepository extends JpaRepository<ChartOfPayments, Long> {
}
