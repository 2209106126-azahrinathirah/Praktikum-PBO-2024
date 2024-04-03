public class PremiumLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Premium
    protected PremiumLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Override metode getPrice untuk memberikan diskon untuk anggota premium
    @Override
    public double getPricePerKg() {
        double originalPrice = super.getPricePerKg();
        double discount = 0.15 * originalPrice; // Diskon 15% untuk anggota premium
        return originalPrice - discount;
    }
}