package selenide.pages;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideCatalogPage {

    // Locators
    private final String catalogTitle = "[data-test='catalog-title']";
    private final String searchInput = "[data-test='search-input']";
    private final String searchButton = "[data-test='search-button']";
    private final String productTitles = "[data-test='product-title']";

    // Open Catalog
    public SelenideCatalogPage openCatalog() {
        open("/catalog");
        return this;
    }

    // Verify Catalog Page
    public SelenideCatalogPage verifyCatalogPage() {

        $(catalogTitle)
                .shouldBe(visible)
                .shouldHave(text("Product Catalog"));

        return this;
    }

    // Search Product
    public SelenideCatalogPage searchFor(String product) {

        $(searchInput)
                .shouldBe(visible)
                .setValue(product);

        $(searchButton)
                .click();

        return this;
    }

    // Verify products exist
    public SelenideCatalogPage verifyProductsDisplayed() {

        $$(productTitles)
                .shouldHave(sizeGreaterThan(0));

        return this;
    }

    public SelenideCatalogPage verifySearchInputVisible() {

        $("[data-test='search-input']")
                .shouldBe(visible)
                .shouldBe(enabled);

        return this;
    }


    public SelenideCatalogPage verifyPlaceholder() {

        $("[data-test='search-input']")
                .shouldHave(attribute(
                        "placeholder",
                        "Search name, category, brand, SKU, or tag"));

        return this;
    }

}