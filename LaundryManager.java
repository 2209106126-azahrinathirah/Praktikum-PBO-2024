import java.util.ArrayList;

public class LaundryManager {
    // ArrayList untuk menyimpan data laundry dan layanan laundry
    private ArrayList<LaundryData> laundryList;
    private ArrayList<LaundryService> serviceList;

    // Konstruktor LaundryManager, dipanggil saat objek dibuat
    public LaundryManager() {
        // Inisialisasi ArrayList
        laundryList = new ArrayList<>();
        serviceList = new ArrayList<>();
        
        // Memanggil metode untuk menginisialisasi layanan laundry
        initializeServices();
    }

    // Metode untuk menginisialisasi layanan laundry
    private void initializeServices() {
        // Menambahkan layanan laundry ke dalam serviceList
        serviceList.add(new LaundryService("Cuci Aja", 5000));
        serviceList.add(new LaundryService("Cuci Setrika", 7000)); 
        serviceList.add(new LaundryService("Setrika Aja", 5000));
    }
    
    // Metode untuk menambahkan data laundry ke dalam laundryList
    public void addLaundryData(String customerID, String customerName, String serviceType, String date, double weight, String status) {
        // Mendapatkan harga per kg berdasarkan jenis layanan
        double hargaPerKg = getServicePrice(serviceType);
        
        // Menghitung total biaya laundry
        double total = hargaPerKg * weight;
    
        // Membuat objek LaundryData
        LaundryData laundryData = new LaundryData(customerID, customerName, serviceType, date, weight, total, status);
        
        // Menambahkan objek LaundryData ke dalam laundryList
        laundryList.add(laundryData);
        
        // Menampilkan pesan sukses
        System.out.println("-------------------------------------------------");
        System.out.println("Data laundry berhasil ditambahkan.");
    }

    // Metode untuk menampilkan data laundry berdasarkan ID pelanggan
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

    // Metode untuk menampilkan semua data laundry
    private void viewAllLaundryData() {
        // Memeriksa apakah laundryList kosong
        if (laundryList.isEmpty()) {
            // Menampilkan pesan jika data laundry tidak tersedia
            System.out.println("Data laundry tidak tersedia.");
            return;
        }

        // Menampilkan semua data laundry
        System.out.println("\n");
        System.out.println("-------------------------------------------------");
        System.out.println("                 Data Laundry                    ");
        System.out.println("-------------------------------------------------");
        for (LaundryData data : laundryList) {
            printLaundryData(data);
        }
    }

    // Metode untuk menampilkan data laundry berdasarkan ID pelanggan
    private void viewLaundryDataByCustomerID(String customerID) {
        // Mengecek apakah data laundry dengan ID pelanggan ditemukan
        boolean found = false;
        for (LaundryData data : laundryList) {
            if (data.getCustomerID().equals(customerID)) {
                found = true;
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

    // Metode untuk menampilkan detail data laundry
    private void printLaundryData(LaundryData data) {
        System.out.println("Nama Pelanggan: " + data.getCustomerName());
        System.out.println("Jenis Layanan: " + data.getServiceType());
        System.out.println("Tanggal: " + data.getDate());
        System.out.println("Berat: " + data.getWeight() + " kg");
        System.out.println("Total: Rp " + data.getTotal());
        System.out.println("Status: " + data.getStatus());
        System.out.println("-------------------------------------------------");
    }

    // Metode untuk mengupdate status data laundry
    public void updateLaundryDataStatus(String customerID, String newStatus) {
        // Mencari data laundry berdasarkan ID pelanggan
        for (LaundryData data : laundryList) {
            if (data.getCustomerID().equals(customerID)) {
                // Mengupdate status data laundry
                data.setStatus(newStatus);
                System.out.println("-------------------------------------------------");
                System.out.println("Status data laundry berhasil diupdate.");
                return;
            }
        }
        // Menampilkan pesan jika data laundry tidak ditemukan
        System.out.println("Data laundry tidak ditemukan.");
    }
    
    // Metode untuk menghapus data laundry
    public void deleteLaundryData(String customerID) {
        // Menghapus data laundry berdasarkan ID pelanggan
        laundryList.removeIf(data -> data.getCustomerID().equals(customerID));
        // Menampilkan pesan sukses
        System.out.println("-------------------------------------------------");
        System.out.println("Data laundry berhasil dihapus.");
    }

    // Metode untuk mendapatkan daftar layanan laundry
    public ArrayList<LaundryService> getServiceList() {
        return serviceList;
    }

    // Metode untuk mendapatkan harga per kg berdasarkan jenis layanan
    private double getServicePrice(String serviceType) {
        for (LaundryService service : serviceList) {
            if (service.getServiceName().equals(serviceType)) {
                return service.getPrice();
            }
        }
        return 0.0;
    }
}
