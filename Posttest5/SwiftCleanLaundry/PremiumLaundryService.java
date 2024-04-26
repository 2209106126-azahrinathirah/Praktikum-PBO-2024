// Kelas turunan untuk layanan laundry express
public class PremiumLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Express
    public PremiumLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Implementasi getTotalPrice untuk mendapatkan harga total
    @Override
    public double getTotalPrice(double weight) {
        // Harga total adalah harga per kg dikalikan berat, plus biaya tambahan untuk layanan express
        double additionalCharge = 1000; // Biaya tambahan 1000 untuk layanan express
        return weight * getPricePerKg() + additionalCharge;
    }
}
