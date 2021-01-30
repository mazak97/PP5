package pl.kCzaja.voucherstore.sales.payment;

import pl.kCzaja.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}
