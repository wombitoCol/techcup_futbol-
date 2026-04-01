package com.techcup_futbol.techcup_futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
