public class RegularLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Reguler
    protected RegularLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Override metode getPrice untuk menetapkan harga tetap
    @Override
    public double getPricePerKg() {
        // Harga tetap 5000 per kg untuk layanan reguler
        return 5000;
    }
}
