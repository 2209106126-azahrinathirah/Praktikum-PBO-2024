import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LaundrySystem {
    private static LaundryManager laundryManager = new LaundryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n");
            System.out.println("=================================================");
            System.out.println("       Sistem Pendataan SwiftClean Laundry       ");
            System.out.println("-------------------------------------------------");
            System.out.println("1. Tambah Data Laundry");
            System.out.println("2. Lihat Data Laundry");
            System.out.println("3. Update Data Laundry");
            System.out.println("4. Hapus Data Laundry");
            System.out.println("5. Keluar");
            System.out.println("=================================================");
            System.out.print("Pilih menu (1-5): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    System.out.println("                  TAMBAH DATA                    ");
                    System.out.println("=================================================");
                    addLaundryData();
                    break;

                case 2:
                    System.out.println("\n");
                    System.out.println("                   LIHAT DATA                     ");
                    System.out.println("=================================================");
                    readLaundryData();
                    break;

                case 3:
                    System.out.println("\n");
                    System.out.println("                  UPDATE DATA                    ");
                    System.out.println("=================================================");
                    updateLaundryData();
                    break;

                case 4:
                    System.out.println("\n");
                    System.out.println("                  HAPUS DATA                    ");
                    System.out.println("=================================================");
                    deleteLaundryData();
                    break;

                case 5:
                    System.out.println("\n");
                    System.out.println(">> Terima kasih! Anda Keluar dari Program <<");
                    System.out.println("\n");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }

        } while (choice != 5);

        scanner.close();
    }

    private static void addLaundryData() {
        scanner.nextLine();
        String customerID;
        boolean isCustomerIDValid = false;
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

        System.out.print("Nama Pelanggan: ");
        String customerName = scanner.nextLine();
        System.out.print("Nomor Telepon: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Jenis Layanan: ");
        System.out.println();
        displayServiceList();
        int serviceChoice = scanner.nextInt();

        String serviceType = laundryManager.getServiceList().get(serviceChoice - 1).getServiceName();

        double weight = 0;
        boolean isWeightValid = false;
        do {
            try {
                System.out.print("Berat (kg): ");
                weight = scanner.nextDouble();
                if (weight <= 0) {
                    throw new IllegalArgumentException("Berat harus lebih besar dari 0.");
                }
                isWeightValid = true;
            } catch (InputMismatchException e) {
                showError("Masukkan berat dalam bentuk angka.");
                clearBuffer();
            } catch (IllegalArgumentException e) {
                showError(e.getMessage());
            }
        } while (!isWeightValid);

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
        
        double total = weight * laundryManager.getServicePrice(serviceType);
        laundryManager.addLaundryData(customerID, customerName, phoneNumber, serviceType, weight,  total, deliveryMethod, deliveryAddress, status);
    }

    private static void readLaundryData() {
        System.out.println("1. Lihat Semua Data Laundry");
        System.out.println("2. Lihat Data Laundry Berdasarkan ID Pelanggan");
        System.out.println("-------------------------------------------------");
        System.out.print("Pilih opsi (1-2): ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                laundryManager.viewLaundryData("0");
                break;

            case 2:
                System.out.print("Masukkan ID Pelanggan: ");
                String customerID = scanner.next();
                laundryManager.viewLaundryData(customerID);
                break;

            default:
                System.out.println("Pilihan tidak valid.");
        }
    }

    private static void updateLaundryData() {
        System.out.print("Masukkan ID Pelanggan: ");
        String updateCustomerID = scanner.next();
        scanner.nextLine();

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

            laundryManager.updateLaundryData(updateCustomerID, newDeliveryMethod, newDeliveryAddress, newStatus);
        } else {
            System.out.println("Data laundry dengan ID pelanggan '" + updateCustomerID + "' tidak ditemukan.");
        }
    }

    private static void deleteLaundryData() {
        System.out.print("Masukkan ID Pelanggan: ");
        String deleteCustomerID = scanner.next();

        boolean found = false;
        for (LaundryData data : laundryManager.getLaundryList()) {
            if (data.getCustomerID().equals(deleteCustomerID)) {
                found = true;
                laundryManager.deleteLaundryData(deleteCustomerID);
                System.out.println("Data laundry berhasil dihapus.");
                break;
            }
        }

        if (!found) {
            System.out.println("Data laundry dengan ID pelanggan '" + deleteCustomerID + "' tidak ditemukan.");
        }
    }

    private static void displayServiceList() {
        ArrayList<LaundryService> services = laundryManager.getServiceList();
        for (int i = 0; i < services.size(); i++) {
            System.out.println((i + 1) + ". " + services.get(i).getServiceName());
        }
        System.out.print("Pilih Jenis Layanan (1-" + services.size() + "): ");
    }

    public static void showError(String message) {
        System.out.println("Error: " + message);
    }

    public static void clearBuffer() {
        scanner.nextLine();
    }
}
