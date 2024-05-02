// Kelas turunan untuk layanan laundry express
public class PremiumLaundryService extends LaundryService {
    public PremiumLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    @Override
    public double calculateTotalPrice(double weight) {
        return weight * getPricePerKg() + DEFAULT_ADDITIONAL_CHARGE;
    }
}

