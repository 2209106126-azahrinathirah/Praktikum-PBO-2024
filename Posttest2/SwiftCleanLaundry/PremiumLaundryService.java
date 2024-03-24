public class PremiumLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Premium
    protected PremiumLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Override metode getPrice untuk memberikan diskon 10%
    @Override
    public double getPricePerKg() {
        double originalPrice = super.getPricePerKg();
        double discount = originalPrice * 0.10;
        return originalPrice - discount;
    }
}