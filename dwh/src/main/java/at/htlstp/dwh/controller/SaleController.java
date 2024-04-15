package at.htlstp.dwh.controller;

import at.htlstp.dwh.model.Location;
import at.htlstp.dwh.model.Product;
import at.htlstp.dwh.model.Sale;
import at.htlstp.dwh.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        List<Sale> sales = saleService.findAllSales();
        return ResponseEntity.ok(sales);
    }

    @PostMapping("/save")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale) {
        Sale newSale = saleService.saveSale(sale);
        return ResponseEntity.ok(newSale);
    }

    @GetMapping("/leastProfitableLocation")
    public ResponseEntity<Location> getLeastProfitableLocation() {
        Location location = (Location) saleService.findLeastProfitableLocation();
        return ResponseEntity.ok(location);
    }

    @GetMapping("/topCategoriesBySeason")
    public ResponseEntity<List<Object[]>> getTopCategoriesBySeason(@RequestParam String seasonStart, @RequestParam String seasonEnd) {
        List<Object[]> categories = saleService.findTopCategoriesBySeason(seasonStart, seasonEnd);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/nonProfitableProducts")
    public ResponseEntity<List<Product>> getNonProfitableProducts() {
        List<Product> products = saleService.findNonProfitableProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/mostProfitableCategory")
    public ResponseEntity<String> getMostProfitableCategory() {
        String category = saleService.findMostProfitableCategory().toString();
        return ResponseEntity.ok(category);
    }


    @GetMapping("/salesDevelopmentByLocation")
    public ResponseEntity<List<Object[]>> getSalesDevelopmentByLocation() {
        List<Object[]> development = saleService.salesDevelopmentByLocation();
        return ResponseEntity.ok(development);
    }

    @GetMapping("/bestMarginsByChain")
    public ResponseEntity<List<Object[]>> getBestMarginsByChain() {
        List<Object[]> margins = saleService.bestMarginsByChain();
        return ResponseEntity.ok(margins);
    }

    @GetMapping("/mostPurchasesByDayOfWeek")
    public ResponseEntity<List<Object[]>> getMostPurchasesByDayOfWeek() {
        List<Object[]> purchases = saleService.mostPurchasesByDayOfWeek();
        return ResponseEntity.ok(purchases);
    }

    @GetMapping("/salesComparisonByDiscountStrategy")
    public ResponseEntity<List<Object[]>> getSalesComparisonByDiscountStrategy() {
        List<Object[]> comparison = saleService.salesComparisonByDiscountStrategy();
        return ResponseEntity.ok(comparison);
    }
}
