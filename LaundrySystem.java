import java.util.Scanner;

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
            System.out.println("3. Update Status Laundry");
            System.out.println("4. Hapus Data Laundry");
            System.out.println("5. Keluar");
            System.out.println("=================================================");
            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    // Tambah data laundry
                    System.out.println("                  CREATE DATA                    ");
                    System.out.println("=================================================");
                    addLaundryData();
                    break;

                case 2:
                    System.out.println("\n");
                    // Tampilkan data laundry
                    System.out.println("                   READ DATA                     ");
                    System.out.println("=================================================");
                    readLaundryData();
                    break;

                case 3:
                    System.out.println("\n");
                    // Update status laundry
                    System.out.println("                   UPDATE DATA                   ");
                    System.out.println("=================================================");
                    updateLaundryDataStatus();
                    break;

                case 4:
                    System.out.println("\n");
                    // Hapus data laundry
                    System.out.println("                   HAPUS DATA                    ");
                    System.out.println("=================================================");
                    deleteLaundryData();
                    break;

                case 5:
                    System.out.println("\n");
                    // Keluar dari Program
                    System.out.println("Terima kasih! Program selesai.");
                    break;

                default:
                    // Pilihan tidak valid
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }

        } while (choice != 5);

        scanner.close();
    }

    // Fungsi untuk menambahkan data laundry
    private static void addLaundryData() {
        // Membersihkan buffer input
        scanner.nextLine();
        System.out.print("Customer ID: ");
        String customerID = scanner.nextLine();
        System.out.print("Nama Pelanggan: ");
        String customerName = scanner.nextLine();

        // Menampilkan daftar layanan
        System.out.println("Jenis Layanan: ");
        for (int i = 0; i < laundryManager.getServiceList().size(); i++) {
            System.out.println((i + 1) + ". " + laundryManager.getServiceList().get(i).getServiceName());
        }
        System.out.print("Pilih Jenis Layanan (1-" + laundryManager.getServiceList().size() + "): ");
        int serviceChoice = scanner.nextInt();

        // Memilih layanan berdasarkan pilihan pengguna
        String serviceType = laundryManager.getServiceList().get(serviceChoice - 1).getServiceName();

        scanner.nextLine();
        System.out.print("Tanggal: ");
        String date = scanner.nextLine();
        System.out.print("Berat (kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Status (Ambil Sendiri/Antar Jemput): ");
        String status = scanner.nextLine();

        // Menambahkan data laundry
        laundryManager.addLaundryData(customerID, customerName, serviceType, date, weight, status);
    }

    // Fungsi untuk menampilkan data laundry
    private static void readLaundryData() {
        // Menampilkan menu baca data laundry
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

    // Fungsi untuk mengapdate status laundry
    private static void updateLaundryDataStatus() {
        System.out.print("Masukkan ID Pelanggan: ");
        String updateCustomerID = scanner.next();
        scanner.nextLine();
        System.out.print("Update Status (Selesai/Dalam Proses): ");
        String newStatus = scanner.nextLine();
        // Memperbarui status data laundry
        laundryManager.updateLaundryDataStatus(updateCustomerID, newStatus);
    }

    // Fungsi untuk menghapus data laundry
    private static void deleteLaundryData() {
        System.out.print("Masukkan ID Pelanggan: ");
        String deleteCustomerID = scanner.next();
        // Menghapus data laundry
        laundryManager.deleteLaundryData(deleteCustomerID);
    }
}
