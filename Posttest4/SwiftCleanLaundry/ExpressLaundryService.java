public class ExpressLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Express
    protected ExpressLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Override metode getPrice untuk menambahkan biaya tambahan
    @Override
    public double getPricePerKg() {
        double originalPrice = super.getPricePerKg();
        double additionalCharge = 0.05 * originalPrice; // Biaya tambahan 5% untuk layanan express
        return originalPrice + additionalCharge;
    }
}