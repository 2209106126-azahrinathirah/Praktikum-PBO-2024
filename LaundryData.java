// LaundryData.java
public class LaundryData {
    private String customerID;
    private String customerName;
    private String serviceType;
    private String date;
    private double weight;
    private double total;
    private String status;

    public LaundryData(String customerID, String customerName, String serviceType, String date, double weight, double total, String status) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.serviceType = serviceType;
        this.date = date;
        this.weight = weight;
        this.total = total;
        this.status = status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }


    public String getCustomerName() {
        return customerName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public double getTotal() {
        return total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
