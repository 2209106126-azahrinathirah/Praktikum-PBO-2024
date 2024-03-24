import java.util.ArrayList;

public class LaundryManager {
    private ArrayList<LaundryData> laundryList;
    private ArrayList<LaundryService> serviceList;

    // Constructor untuk LaundryManager
    public LaundryManager() {
        // Inisialisasi ArrayList untuk data laundry dan layanan laundry
        laundryList = new ArrayList<>();
        serviceList = new ArrayList<>();
        // Memanggil metode untuk menginisialisasi layanan laundry
        initializeServices();
    }

    // Method untuk menginisialisasi layanan laundry
    protected void initializeServices() {
        // Menambahkan layanan laundry ke dalam serviceList
        serviceList.add(new LaundryService("Cuci Aja", 5000));
        serviceList.add(new LaundryService("Cuci Setrika", 7000));
        serviceList.add(new LaundryService("Setrika Aja", 5000));
        serviceList.add(new PremiumLaundryService("Cuci Premium", 10000)); 
    }

    // Method untuk menambahkan data laundry baru
    public void addLaundryData(String customerID, String customerName, String phoneNumber, String serviceType, double weight, double total, String deliveryMethod, String deliveryAddress, String status) {
        // Membuat objek LaundryData baru
        LaundryData laundryData = new LaundryData(customerID, customerName, phoneNumber, serviceType, weight, total, deliveryMethod, deliveryAddress, status);
        // Menambahkan objek LaundryData ke dalam laundryList
        laundryList.add(laundryData);
        // Menampilkan pesan sukses
        System.out.println("Data laundry berhasil ditambahkan.");
    }

    // Method untuk menampilkan data laundry berdasarkan ID pelanggan
    public void viewLaundryData(String customerID) {
        // Memeriksa apakah menampilkan semua data atau data berdasarkan ID pelanggan
        if (customerID.equals("0")) {
            // Menampilkan semua data laundry jika customerID == "0"
            viewAllLaundryData();
        } else {
            // Menampilkan data laundry berdasarkan ID pelanggan
            viewLaundryDataByCustomerID(customerID);
        }
    }

    // Method untuk menampilkan semua data laundry
    private void viewAllLaundryData() {
        // Memeriksa apakah laundryList kosong
        if (laundryList.isEmpty()) {
            // Menampilkan pesan jika data laundry tidak tersedia
            System.out.println("Data laundry tidak tersedia.");
            return;
        }

        // Menampilkan semua data laundry
        System.out.println("-------------------------------------------------");
        System.out.println("                 Data Laundry                    ");
        System.out.println("-------------------------------------------------");
        for (LaundryData data : laundryList) {
            printLaundryData(data);
        }
    }

    // Method untuk menampilkan data laundry berdasarkan ID pelanggan
    private void viewLaundryDataByCustomerID(String customerID) {
        // Mengecek apakah data laundry dengan ID pelanggan ditemukan
        boolean found = false;
        for (LaundryData data : laundryList) {
            if (data.getCustomerID().equals(customerID)) {
                found = true;
                // Menampilkan data laundry jika ditemukan
                System.out.println("\n");
                System.out.println("-------------------------------------------------");
                System.out.println("                 Data Laundry                    ");
                System.out.println("-------------------------------------------------");
                printLaundryData(data);
                break;
            }
        }
        // Menampilkan pesan jika data tidak ditemukan
        if (!found) {
            System.out.println("Data laundry dengan ID pelanggan '" + customerID + "' tidak ditemukan.");
        }
    }

    // Method untuk menampilkan detail data laundry
    private void printLaundryData(LaundryData data) {
        System.out.println("Nama Pelanggan: " + data.getCustomerName());
        System.out.println("Nomor Telepon: " + data.getPhoneNumber());
        System.out.println("Jenis Layanan: " + data.getServiceType());
        System.out.println("Berat: " + data.getWeight() + " kg");
        System.out.println("Total: Rp " + data.getTotal());
        System.out.println("Metode Pengiriman: " + data.getDeliveryMethod());
        // Jika metode pengiriman adalah "diantar", maka tampilkan alamat pengiriman
        if (data.getDeliveryMethod().equalsIgnoreCase("diantar")) {
            System.out.println("Alamat Pengiriman: " + data.getDeliveryAddress());
        }
        System.out.println("Status: " + data.getStatus());
        System.out.println("-------------------------------------------------");
    }

    // Method untuk mengupdate data laundry (metode pengiriman, alamat, dan status)
    public void updateLaundryData(String customerID, String newDeliveryMethod, String newDeliveryAddress, String newStatus) {
        // Mencari data laundry berdasarkan ID pelanggan
        for (LaundryData data : laundryList) {
            if (data.getCustomerID().equals(customerID)) {
                // Mengupdate metode pengiriman, alamat, dan status data laundry
                data.setDeliveryMethod(newDeliveryMethod);
                // Jika metode pengiriman adalah "diantar", maka set juga alamat pengiriman
                if (newDeliveryMethod.equalsIgnoreCase("diantar")) {
                    data.setDeliveryAddress(newDeliveryAddress);
                }
                data.setStatus(newStatus);
                // Menampilkan pesan sukses
                System.out.println("Data laundry berhasil diperbarui.");
                return;
            }
        }
        // Menampilkan pesan jika data laundry tidak ditemukan
        System.out.println("Data laundry tidak ditemukan.");
    }

    // Method untuk menghapus data laundry berdasarkan ID pelanggan
    public void deleteLaundryData(String customerID) {
        // Menghapus data laundry berdasarkan ID pelanggan
        laundryList.removeIf(data -> data.getCustomerID().equals(customerID));
        // Menampilkan pesan sukses
        System.out.println("Data laundry berhasil dihapus.");
    }

    // Method untuk mendapatkan harga per kg berdasarkan jenis layanan
    public double getServicePrice(String serviceType) {
        // Mendapatkan harga per kg dari serviceList
        for (LaundryService service : serviceList) {
            if (service.getServiceName().equalsIgnoreCase(serviceType)) {
                return service.getPricePerKg(); // Mengubah pemanggilan menjadi getPricePerKg()
            }
        }
        // Mengembalikan 0 jika layanan tidak ditemukan
        return 0;
    }

    // Getter untuk serviceList
    public ArrayList<LaundryService> getServiceList() {
        return serviceList;
    }

    // Method untuk mengecek keberadaan ID pelanggan dalam data laundry
    public boolean isCustomerIDExist(String customerID) {
        // Mengecek apakah ID pelanggan ada dalam data laundry
        for (LaundryData data : laundryList) {
            if (data.getCustomerID().equals(customerID)) {
                return true;
            }
        }
        return false;
    }
}