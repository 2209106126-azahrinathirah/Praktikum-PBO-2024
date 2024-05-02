// Kelas abstrak untuk layanan laundry
public abstract class LaundryService implements LaundryServiceInterface {
    protected static final double DEFAULT_ADDITIONAL_CHARGE = 1000;
    protected final String serviceName;
    protected final double pricePerKg;

    protected LaundryService(String serviceName, double pricePerKg) {
        this.serviceName = serviceName;
        this.pricePerKg = pricePerKg;
    }

    public final String getServiceName() {
        return serviceName;
    }

    public final double getPricePerKg() {
        return pricePerKg;
    }

    public abstract double calculateTotalPrice(double weight);
}
