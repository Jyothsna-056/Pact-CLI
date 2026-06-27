package selenide.tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenide.config.Config;
import selenide.pages.SelenideCatalogPage;

public class SelenideCatalogTest {

    @BeforeAll
    static void setup() {

       Config.apply();
    }

    @Test
    @DisplayName("Verify Catalog Page")
    void verifyCatalogPage() {

        new SelenideCatalogPage()
                .openCatalog()
                .verifyCatalogPage();
    }

    @Test
    @DisplayName("Verify Search")
    void verifySearch() {

        new SelenideCatalogPage()
                .openCatalog()
                .searchFor("Headphones")
                .verifyProductsDisplayed();
    }

    @Test
    @DisplayName("Verify Product Catalog page is displayed")
    void verifyCatalogTitle() {

        new SelenideCatalogPage()
                .openCatalog()
                .verifyCatalogPage();
    }
    @Test
    @DisplayName("Verify Search Input is visible")
    void verifySearchInput() {

        new SelenideCatalogPage()
                .openCatalog()
                .verifySearchInputVisible();
    }

    @Test
    @DisplayName("Verify Search Placeholder")
    void verifyPlaceholder() {

        new SelenideCatalogPage()
                .openCatalog()
                .verifyPlaceholder();
    }

}