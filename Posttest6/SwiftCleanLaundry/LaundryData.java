public class LaundryData {
    private String customerID;
    private String customerName;
    private String phoneNumber;
    private String serviceType;
    private double weight;
    private double total;
    private String deliveryMethod;
    private String deliveryAddress;
    private String status;

    protected LaundryData(String customerID, String customerName, String phoneNumber, String serviceType, double weight, double total, String deliveryMethod, String deliveryAddress, String status) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.serviceType = serviceType;
        this.weight = weight;
        this.total = total;
        this.deliveryMethod = deliveryMethod;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }

    // Getter dan setter untuk setiap atribut

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
