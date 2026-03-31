package com.techcup_futbol.techcup_futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Asegúrate de que esta ruta coincida con donde tienes tu clase Invoice
import com.techcup_futbol.techcup_futbol.model.Invoice.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
