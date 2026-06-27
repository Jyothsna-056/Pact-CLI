

package contract.oms;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;

import com.github.tomakehurst.wiremock.junit5.WireMockExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Provider("oms-provider")
@PactFolder("target/pacts")
public class OmsProviderVerification {

    @RegisterExtension
    static WireMockExtension wireMock =
            WireMockExtension.newInstance()
                    .options(wireMockConfig().port(4010))
                    .build();

    @BeforeEach
    void setup(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", 4010));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verify(PactVerificationContext context) {
        context.verifyInteraction();
    }

    // --------------------------------------------------
    // GET /order/123
    // --------------------------------------------------
    @State("Order 123 exists")
    void order123Exists() {

        wireMock.stubFor(get(urlEqualTo("/order/123"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        {
                          "orderId":123,
                          "status":"CONFIRMED",
                          "total":42.0
                        }
                        """)));
    }

    // --------------------------------------------------
    // POST /orders/
    // --------------------------------------------------
    @State("Creating a new order")
    void creatingNewOrder() {

        wireMock.stubFor(post(urlEqualTo("/orders/"))
                .withHeader("Content-Type",
                        matching("application/json(;.*)?"))
                .willReturn(aResponse()
                        .withStatus(201)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        {
                          "statusCode":201,
                          "orderId":101,
                          "status":"CREATED",
                          "total":2000
                        }
                        """)));
    }

    // --------------------------------------------------
    // GET /inventory/SKU-9
    // --------------------------------------------------
    @State("SKU-9 has Stock")
    void sku9HasStock() {

        wireMock.stubFor(get(urlEqualTo("/inventory/SKU-9"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type",
                                "application/json; charset=UTF-8")
                        .withBody("""
                        {
                          "sku":"SKU-9",
                          "qty":5
                        }
                        """)));
    }
}