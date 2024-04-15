package at.htlstp.dwh.repository;

import at.htlstp.dwh.model.Location;
import at.htlstp.dwh.model.Product;
import at.htlstp.dwh.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {

    @Query("SELECT l FROM Sale s JOIN s.location l GROUP BY l ORDER BY SUM(s.quantity * s.price) ASC")
    List<Location> findLeastProfitableLocation();


    @Query("SELECT p.category, SUM(s.quantity * s.price) as totalSales " +
            "FROM Sale s JOIN s.product p JOIN s.time t " +
            "WHERE t.date_time BETWEEN :seasonStart AND :seasonEnd " +
            "GROUP BY p.category ORDER BY totalSales DESC")
    List<Object[]> findTopCategoriesBySeason(String seasonStart, String seasonEnd);

    @Query("SELECT p FROM Sale s JOIN s.product p GROUP BY p " +
            "HAVING SUM(s.quantity * s.price)  < s.final_price " +
            "ORDER BY SUM(s.quantity * s.price)")
    List<Product> findNonProfitableProducts();

    @Query("SELECT p.category, SUM(s.quantity * s.price) - SUM(s.quantity * p.price) as totalProfit " +
            "FROM Sale s JOIN s.product p GROUP BY p.category ORDER BY totalProfit DESC")
    List<Object[]> findMostProfitableCategory();


    @Query("SELECT s.location.name, s.time.date_year as year, SUM(s.final_price) as totalSales " +
            "FROM Sale s " +
            "GROUP BY s.location.name, year " +
            "ORDER BY s.location.name, year")
    List<Object[]> salesDevelopmentByLocation();

    // Supermarktketten mit den besten Margen
    @Query("SELECT s.location.chain, SUM(s.final_price - s.quantity * p.price) / SUM(s.quantity * p.price) as margin " +
            "FROM Sale s JOIN s.product p " +
            "GROUP BY s.location.chain " +
            "ORDER BY margin DESC")
    List<Object[]> bestMarginsByChain();

    // Wochentage mit den meisten Einkäufen in den Ketten
    @Query("SELECT s.location.chain, FUNCTION('DAYOFWEEK', s.time.date_time) as dayOfWeek, COUNT(s.id) as totalPurchases " +
            "FROM Sale s " +
            "GROUP BY s.location.chain, dayOfWeek " +
            "ORDER BY s.location.chain, totalPurchases DESC")
    List<Object[]> mostPurchasesByDayOfWeek();

    // Umsatzvergleich zwischen Filialen mit hohen Rabatten und solchen mit defensiven Rabattstrategien
    @Query("SELECT " +
            "(SELECT SUM(s1.final_price) FROM Sale s1 WHERE s1.discount > 0.2) as highDiscountSales, " +
            "(SELECT SUM(s2.final_price) FROM Sale s2 WHERE s2.discount <= 0.2) as lowDiscountSales")
    List<Object[]> salesComparisonByDiscountStrategy();

}
