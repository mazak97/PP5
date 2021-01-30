package pl.kCzaja.voucherstore.sales.payment;

import pl.kCzaja.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails register(Reservation reservation);

    boolean isTrusted(PaymentUpdateStatusRequest paymentUpdateStatusRequest);
}
