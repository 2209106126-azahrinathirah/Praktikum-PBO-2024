// Kelas turunan untuk layanan laundry deluxe
public class DeluxeLaundryService extends LaundryService {
    public DeluxeLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    @Override
    public double calculateTotalPrice(double weight) {
        double additionalCharge = 0.10 * getPricePerKg();
        return weight * getPricePerKg() + additionalCharge;
    }
}
