// LaundryService.java
public class LaundryService {
    private String serviceName;
    private double price;

    public LaundryService(String serviceName, double price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getPrice() {
        return price;
    }
}
