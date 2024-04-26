// Kelas turunan untuk layanan laundry reguler
public class RegularLaundryService extends LaundryService {
    // Konstruktor untuk inisialisasi objek Layanan Laundry Reguler
    public RegularLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    // Implementasi getTotalPrice untuk mendapatkan harga total
    @Override
    public double getTotalPrice(double weight) {
        // Harga total adalah harga per kg dikalikan berat
        return weight * getPricePerKg();
    }
}