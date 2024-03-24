public class DeluxeLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Deluxe
    public DeluxeLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Override metode getPrice untuk menambahkan biaya tambahan untuk layanan deluxe
    @Override
    public double getPricePerKg() {
        double originalPrice = super.getPricePerKg();
        double additionalCharge = 0.10 * originalPrice; // Biaya tambahan 10% untuk layanan deluxe
        return originalPrice + additionalCharge;
    }
}