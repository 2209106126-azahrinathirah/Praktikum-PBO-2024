// Kelas turunan untuk layanan laundry express
public final class ExpressLaundryService extends LaundryService {
    public ExpressLaundryService(String serviceName, double pricePerKg) {
        super(serviceName, pricePerKg);
    }

    @Override
    public double calculateTotalPrice(double weight) {
        return weight * getPricePerKg();
    }
}
