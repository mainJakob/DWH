package at.htlstp.dwh.service;

import at.htlstp.dwh.model.Location;
import at.htlstp.dwh.model.Product;
import at.htlstp.dwh.model.Sale;
import at.htlstp.dwh.model.Time;
import at.htlstp.dwh.repository.LocationRepo;
import at.htlstp.dwh.repository.ProductRepo;
import at.htlstp.dwh.repository.SaleRepo;
import at.htlstp.dwh.repository.TimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepo saleRepo;
    private final TimeRepo timeRepo;
    private final LocationRepo locationRepo;
    private final ProductRepo productRepo;

    @Autowired
    public SaleService(SaleRepo saleRepo, TimeRepo timeRepo, LocationRepo locationRepo, ProductRepo productRepo) {
        this.saleRepo = saleRepo;
        this.timeRepo = timeRepo;
        this.locationRepo = locationRepo;
        this.productRepo = productRepo;
    }

    public List<Sale> findAllSales() {
        return saleRepo.findAll();
    }

    public List<Location> findLeastProfitableLocation() {
        return saleRepo.findLeastProfitableLocation();
    }

    public List<Object[]> findTopCategoriesBySeason(String seasonStart, String seasonEnd) {
        return saleRepo.findTopCategoriesBySeason(seasonStart, seasonEnd);
    }

    public List<Product> findNonProfitableProducts() {
        return saleRepo.findNonProfitableProducts();
    }

    public List<Object[]> findMostProfitableCategory() {
        return saleRepo.findMostProfitableCategory();
    }

    public List<Object[]> salesDevelopmentByLocation() {
        return saleRepo.salesDevelopmentByLocation();
    }

    public List<Object[]> bestMarginsByChain() {
        return saleRepo.bestMarginsByChain();
    }

    public List<Object[]> mostPurchasesByDayOfWeek() {
        return saleRepo.mostPurchasesByDayOfWeek();
    }

    public List<Object[]> salesComparisonByDiscountStrategy() {
        return saleRepo.salesComparisonByDiscountStrategy();
    }

    public Sale saveSale(Sale sale) {
        // Ensure dimensions exist or are created
        Time time = timeRepo.save(sale.getTime());
        Location location =locationRepo.save(sale.getLocation());
        Product product = productRepo.save(sale.getProduct());

        // Set the dimensions back to the sale
        sale.setTime(time);
        sale.setLocation(location);
        sale.setProduct(product);

        return saleRepo.save(sale);
    }
}
