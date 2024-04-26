// Kelas turunan untuk layanan laundry express
public final class ExpressLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Express
    public ExpressLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Implementasi getTotalPrice untuk mendapatkan harga total
    @Override
    public double getTotalPrice(double weight) {
        // Harga total adalah harga per kg dikalikan berat
        return weight * getPricePerKg();
    }
}