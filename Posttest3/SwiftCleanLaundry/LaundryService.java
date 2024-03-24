public class LaundryService {
    protected String serviceName;
    protected double pricePerKg;

    // Konstruktor untuk inisialisasi objek LaundryService
    protected LaundryService(String serviceName, double pricePerKg) {
        this.serviceName = serviceName;
        this.pricePerKg = pricePerKg;
    }

    // Getter untuk serviceName
    protected String getServiceName() {
        return serviceName;
    }

    // Getter untuk pricePerKg
    protected double getPricePerKg() {
        return pricePerKg;
    }
}


