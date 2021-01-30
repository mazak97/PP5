package pl.kCzaja.voucherstore.sales.product;

public interface ProductDetailsProvider {
    ProductDetails getByProductId(String productId);
}
