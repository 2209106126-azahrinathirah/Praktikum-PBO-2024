// Kelas turunan untuk layanan laundry reguler
public class RegularLaundryService extends LaundryService {
    public RegularLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    @Override
    public double calculateTotalPrice(double weight) {
        return weight * getPricePerKg();
    }
}
