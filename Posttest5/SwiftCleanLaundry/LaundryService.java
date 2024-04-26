// Kelas abstrak untuk layanan laundry
public abstract class LaundryService {
    protected final String serviceName; // Kata kunci final untuk satu atribut variabel
    protected final double pricePerKg; // Kata kunci final untuk satu atribut variabel

    // Constructor untuk inisialisasi objek LaundryService
    protected LaundryService(String serviceName, double pricePerKg) {
        this.serviceName = serviceName;
        this.pricePerKg = pricePerKg;
    }

    // Getter untuk serviceName
    protected final String getServiceName() { // Kata kunci final  method
        return serviceName;
    }

    // Getter untuk pricePerKg
    protected final double getPricePerKg() { // Kata kunci final  method
        return pricePerKg;
    }

    // Method abstrak untuk mendapatkan harga total
    public abstract double getTotalPrice(double weight);
}