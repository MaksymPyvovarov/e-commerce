package pl.mpyvovarov.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowsToListAllProduct() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assertTrue(products.isEmpty());
    }

    @Test
    void itAllowsToCreateProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.createProduct("Vova", "nice one");

        List<Product> products = catalog.allProducts();
        assertFalse(products.isEmpty());
    }

    @Test
    void createdProductAreUniquelyIdentifiable() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId1 = catalog.createProduct("Vova", "nice one");
        String productId2 = catalog.createProduct("Vova12", "nice one");

        assertNotEquals(productId1, productId2);
    }

    @Test
    void itLoadsProductById() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId1 = catalog.createProduct("Vova", "nice one");

        Product loaded = catalog.loadProductById(productId1);

        assertEquals(productId1, loaded.getId());
        assertEquals("Vova", loaded.getName());
        assertEquals("nice one", loaded.getDescription());
    }

    @Test
    void allowsToApplyPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Vova", "nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(100.10));

        Product loaded = catalog.loadProductById(productId);
        assertEquals(BigDecimal.valueOf(100.10), loaded.getPrice());
    }

    @Test
    void denyToApplyPriceThatViolateMinimumRange() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Vova", "nice one");

        assertThrows(
                InvalidPriceException.class,
                () -> catalog.changePrice(productId, BigDecimal.valueOf(-10))
        );
    }

    @Test
    void allowsToApplyImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.createProduct("Vova", "nice one");

        catalog.changeImage(productId, "some image");

        Product loaded = catalog.loadProductById(productId);
        assertEquals("some image", loaded.getImage());
    }

    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }
}
