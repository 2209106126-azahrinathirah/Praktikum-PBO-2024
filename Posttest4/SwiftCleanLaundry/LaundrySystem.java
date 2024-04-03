import java.util.Scanner;
import java.util.ArrayList;

public class LaundrySystem {
    // Membuat objek LaundryManager dan Scanner
    private static LaundryManager laundryManager = new LaundryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice; // Variabel untuk menyimpan pilihan menu
        // Menampilkan menu utama
        do {
            System.out.println("\n");
            System.out.println("=================================================");
            System.out.println("       Sistem Pendataan SwiftClean Laundry       ");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Tambah Data Laundry");
            System.out.println("2. Lihat Data Laundry");
            System.out.println("3. Update Data Laundry");
            System.out.println("4. Hapus Data Laundry");
            System.out.println("5. Tambah Layanan Baru");
            System.out.println("6. Keluar");
            System.out.println("=================================================");
            System.out.print("Pilih menu (1-6): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    // Tambah data laundry
                    System.out.println("                  TAMBAH DATA                    ");
                    System.out.println("=================================================");
                    addLaundryData();
                    break;

                case 2:
                    System.out.println("\n");
                    // Tampilkan data laundry
                    System.out.println("                   LIHAT DATA                     ");
                    System.out.println("=================================================");
                    readLaundryData();
                    break;

                case 3:
                    System.out.println("\n");
                    // Update data laundry
                    System.out.println("                  UPDATE DATA                    ");
                    System.out.println("=================================================");
                    updateLaundryData();
                    break;

                case 4:
                    System.out.println("\n");
                    // Hapus data laundry
                    System.out.println("                  HAPUS DATA                    ");
                    System.out.println("=================================================");
                    deleteLaundryData();
                    break;

                case 5:
                    System.out.println("\n");
                    System.out.println("              TAMBAH LAYANAN BARU                ");
                    System.out.println("=================================================");
                    addNewService();
                    break;

                case 6:
                    System.out.println("\n");
                    // Keluar dari Program
                    System.out.println(">> Terima kasih! Anda Keluar dari Program <<");
                    System.out.println("\n");
                    break;

                default:
                    // Pilihan tidak valid
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }

        } while (choice != 6);

        scanner.close();
    }


    // Method untuk menambahkan data laundry baru
    private static void addLaundryData() {
    // Membersihkan buffer input
    scanner.nextLine();
    String customerID;
    boolean isCustomerIDValid = false;
    
    // Meminta input ID pelanggan yang valid
    do {
        System.out.print("ID Pelanggan: ");
        customerID = scanner.nextLine();
        if (customerID.isEmpty()) {
            System.out.println("ID pelanggan tidak boleh kosong.");
        } else if (laundryManager.isCustomerIDExist(customerID)) {
            System.out.println("ID pelanggan sudah ada. Harap masukkan ID pelanggan yang berbeda.");
        } else {
            isCustomerIDValid = true;
        }
    } while (!isCustomerIDValid);

     // Memasukkan data pelanggan lainnya
    System.out.print("Nama Pelanggan: ");
    String customerName = scanner.nextLine();
    System.out.print("Nomor Telepon: ");
    String phoneNumber = scanner.nextLine();
    System.out.print("Jenis Layanan: ");
    System.out.println(); 
    displayServiceList();
    int serviceChoice = scanner.nextInt();

    // Memilih layanan berdasarkan pilihan pengguna
    String serviceType = laundryManager.getServiceList().get(serviceChoice - 1).getServiceName();

    System.out.print("Berat (kg): ");
    double weight = scanner.nextDouble();
    double total = weight * laundryManager.getServicePrice(serviceType);

    scanner.nextLine();
    String deliveryMethod = "";
    while (!deliveryMethod.equalsIgnoreCase("ambil sendiri") && !deliveryMethod.equalsIgnoreCase("diantar")) {
        System.out.print("Metode Pengiriman (ambil sendiri/diantar): ");
        deliveryMethod = scanner.nextLine();
        if (!deliveryMethod.equalsIgnoreCase("ambil sendiri") && !deliveryMethod.equalsIgnoreCase("diantar")) {
            System.out.println("Metode pengiriman tidak valid. Harap masukkan 'ambil sendiri' atau 'diantar'.");
        }
    }

    String deliveryAddress = "";
    if (deliveryMethod.equalsIgnoreCase("diantar")) {
        System.out.print("Alamat Pengiriman: ");
        deliveryAddress = scanner.nextLine();
    }

    String status = "";
    while (!status.equalsIgnoreCase("dalam proses") && !status.equalsIgnoreCase("selesai")) {
        System.out.print("Status (dalam proses/selesai): ");
        status = scanner.nextLine();
        if (!status.equalsIgnoreCase("dalam proses") && !status.equalsIgnoreCase("selesai")) {
            System.out.println("Status tidak valid. Harap masukkan 'dalam proses' atau 'selesai'.");
        }
    }

    // Menambahkan data laundry
    laundryManager.addLaundryData(customerID, customerName, phoneNumber, serviceType, weight, total, deliveryMethod, deliveryAddress, status);
}

    // Method untuk menampilkan data laundry
    private static void readLaundryData() {
        System.out.println("1. Lihat Semua Data Laundry");
        System.out.println("2. Lihat Data Laundry Berdasarkan ID Pelanggan");
        System.out.println("-------------------------------------------------");
        System.out.print("Pilih opsi (1-2): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                // Menampilkan semua data laundry
                laundryManager.viewLaundryData("0"); // Ganti dengan ID pelanggan yang sesuai
                break;

            case 2:
                // Menampilkan data laundry berdasarkan ID pelanggan
                System.out.print("Masukkan ID Pelanggan: ");
                String customerID = scanner.next();
                laundryManager.viewLaundryData(customerID);
                break;

            default:
                // Pilihan tidak valid
                System.out.println("Pilihan tidak valid.");
        }
    }

    // Method untuk mengupdate data laundry
private static void updateLaundryData() {
    System.out.print("Masukkan ID Pelanggan: ");
    String updateCustomerID = scanner.next();
    scanner.nextLine();

    // Mengecek apakah data laundry dengan ID pelanggan ditemukan
    if (laundryManager.isCustomerIDExist(updateCustomerID)) {
        String newDeliveryMethod = "";
        while (!newDeliveryMethod.equalsIgnoreCase("ambil sendiri") && !newDeliveryMethod.equalsIgnoreCase("diantar")) {
            System.out.print("Update Metode Pengiriman (Ambil Sendiri/Diantar): ");
            newDeliveryMethod = scanner.nextLine();
            if (!newDeliveryMethod.equalsIgnoreCase("ambil sendiri") && !newDeliveryMethod.equalsIgnoreCase("diantar")) {
                System.out.println("Metode pengiriman tidak valid. Harap masukkan 'ambil sendiri' atau 'diantar'.");
            }
        }

        String newDeliveryAddress = "";
        if (newDeliveryMethod.equalsIgnoreCase("diantar")) {
            System.out.print("Update Alamat Pengiriman: ");
            newDeliveryAddress = scanner.nextLine();
        }

        String newStatus = "";
        while (!newStatus.equalsIgnoreCase("dalam proses") && !newStatus.equalsIgnoreCase("selesai")) {
            System.out.print("Update Status (Dalam Proses/Selesai): ");
            newStatus = scanner.nextLine();
            if (!newStatus.equalsIgnoreCase("dalam proses") && !newStatus.equalsIgnoreCase("selesai")) {
                System.out.println("Status tidak valid. Harap masukkan 'dalam proses' atau 'selesai'.");
            }
        }

        // Memperbarui data laundry
        laundryManager.updateLaundryData(updateCustomerID, newDeliveryMethod, newDeliveryAddress, newStatus);
    } else {
        System.out.println("Data laundry dengan ID pelanggan '" + updateCustomerID + "' tidak ditemukan.");
    }
}

    // Method untuk menghapus data laundry berdasarkan ID pelanggan
    private static void deleteLaundryData() {
        System.out.print("Masukkan ID Pelanggan: ");
        String deleteCustomerID = scanner.next();
        
        // Mengecek apakah data laundry dengan ID pelanggan ditemukan
        boolean found = false;
        for (LaundryData data : laundryManager.getLaundryList()) {
            if (data.getCustomerID().equals(deleteCustomerID)) {
                found = true;
                // Menghapus data laundry berdasarkan ID pelanggan
                laundryManager.deleteLaundryData(deleteCustomerID);
                System.out.println("Data laundry berhasil dihapus.");
                break;
            }
        }
        
        // Menampilkan pesan jika data tidak ditemukan
        if (!found) {
            System.out.println("Data laundry dengan ID pelanggan '" + deleteCustomerID + "' tidak ditemukan.");
        }
    }


    // Method untuk menampilkan daftar layanan
    private static void displayServiceList() {
        ArrayList<LaundryService> services = laundryManager.getServiceList();
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).getServiceName());
        }
        System.out.print("Pilih Jenis Layanan (1-" + services.size() + "): ");
    }

    // Method untuk menambahkan layanan baru ke dalam sistem laundry.
    private static void addNewService() {
        scanner.nextLine();
        System.out.print("Nama Layanan Baru: ");
        String serviceName = scanner.nextLine();
        System.out.print("Harga per Kg: ");
        double pricePerKg = scanner.nextDouble();
    
        laundryManager.addService(serviceName, pricePerKg);
        
        // Menampilkan menu layanan setelah menambah layanan baru
        System.out.println("\nMenu Layanan :");
        displayServiceList();
    }
}


