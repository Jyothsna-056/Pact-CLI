package selenide.pages;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {

    private final String productName = "[data-test='detail-name']";
    private final String productPrice = ".price";
    private final String shoeSize = "#shoe-size";
    private final String quantity = "#quantity";
    private final String addToCartButton = "[data-test='add-to-cart']";

    public ProductPage openProduct() {
        open("/product/running-shoes");
        return this;
    }

    public ProductPage shouldDisplayProductName(String expectedName) {

        $(productName)
                .shouldBe(visible)
                .shouldHave(text(expectedName));

        return this;
    }

    public ProductPage shouldDisplayPrice(String expectedPrice) {

        $(productPrice)
                .shouldBe(visible)
                .shouldHave(text(expectedPrice));

        return this;
    }

    public ProductPage selectSize(String size) {

        $(shoeSize)
                .shouldBe(enabled)
                .selectOption(size);

        return this;
    }

    public ProductPage enterQuantity(String qty) {

        $(quantity)
                .clear();

        $(quantity)
                .setValue(qty);

        return this;
    }

    public ProductPage clickAddToCart() {

        $(addToCartButton)
                .shouldBe(enabled)
                .click();

        return this;
    }
}