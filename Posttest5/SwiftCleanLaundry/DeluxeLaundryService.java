// Kelas turunan untuk layanan laundry deluxe
public class DeluxeLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Deluxe
    public DeluxeLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Implementasi getTotalPrice untuk mendapatkan harga total
    @Override
    public double getTotalPrice(double weight) {
        // Harga total adalah harga per kg dikalikan berat, plus biaya tambahan 10%
        double additionalCharge = 0.10 * getPricePerKg(); // Biaya tambahan 10% untuk layanan deluxe
        return weight * getPricePerKg() + additionalCharge;
    }
}