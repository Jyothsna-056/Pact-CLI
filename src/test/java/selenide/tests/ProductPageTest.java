package selenide.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenide.config.Config;
import selenide.pages.ProductPage;

public class ProductPageTest {

    @BeforeAll
    static void setup() {
       Config.apply();
    }

    @Test
    @DisplayName("Verify Product Name")
    void verifyProductName() {

        new ProductPage()
                .openProduct()
                .shouldDisplayProductName("Running Shoes");
    }

    @Test
    @DisplayName("Verify Product Price")
    void verifyProductPrice() {

        new ProductPage()
                .openProduct()
                .shouldDisplayPrice("Rs. 4,499");
    }

    @Test
    @DisplayName("Verify Shoe Size Dropdown")
    void verifyShoeSizeDropdown() {

        new ProductPage()
                .openProduct()
                .selectSize("UK 8");
    }

    @Test
    @DisplayName("Verify Add To Cart")
    void verifyAddToCart() {

        new ProductPage()
                .openProduct()
                .selectSize("UK 8")
                .enterQuantity("2")
                .clickAddToCart();
    }

}