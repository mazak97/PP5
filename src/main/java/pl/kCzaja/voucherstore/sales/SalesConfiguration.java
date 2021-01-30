package pl.kCzaja.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kCzaja.payu.http.JavaHttpPayUApiClient;
import pl.kCzaja.payu.PayU;
import pl.kCzaja.payu.PayUCredentials;
import pl.kCzaja.voucherstore.productcatalog.ProductCatalogFacade;
import pl.kCzaja.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.kCzaja.voucherstore.sales.offer.OfferMaker;
import pl.kCzaja.voucherstore.sales.ordering.ReservationRepository;
import pl.kCzaja.voucherstore.sales.payment.PayUPaymentGateway;
import pl.kCzaja.voucherstore.sales.payment.PaymentGateway;
import pl.kCzaja.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.kCzaja.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        return new SalesFacade(
                productCatalogFacade,
                new InMemoryBasketStorage(),
                () -> "customer_1",
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository);
    }

    @Bean
    PaymentGateway payUPaymentGateway() {
        return new PayUPaymentGateway(new PayU(
                PayUCredentials.productionOfEnv(),
                new JavaHttpPayUApiClient()
        ));
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }
}
