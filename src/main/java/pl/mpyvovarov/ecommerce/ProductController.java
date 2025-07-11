package pl.mpyvovarov.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mpyvovarov.ecommerce.catalog.Product;
import pl.mpyvovarov.ecommerce.catalog.ProductCatalog;

import java.util.List;

@RestController
public class ProductController {

    ProductCatalog catalog;

    public ProductController(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    @GetMapping("/api/version")
    String version() {
        return "0.0.1";
    }

    @GetMapping("/api/products")
    List<Product> allProducts() {
        return catalog.allProducts();
    }
}
