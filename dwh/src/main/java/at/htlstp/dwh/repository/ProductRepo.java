package at.htlstp.dwh.repository;

import at.htlstp.dwh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    // Hier könnten benutzerdefinierte Abfragen stehen
}
