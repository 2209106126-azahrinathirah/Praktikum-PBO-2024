#include <iostream>
// Library untuk operasi input dan output standar, seperti cin dan cout.

#include <string>
// Library string untuk mendukung manipulasi dan penanganan string

#include <cmath>
// Library cmath berisi fungsi-fungsi matematika, seperti sqrt (akar kuadrat) dan pow (pangkat).

#include <thread>
// Library thread mendukung pemrograman multithreading, memungkinkan eksekusi program secara bersamaan.

#include <algorithm>
// Library algorithm seperti pengurutan (sort) dan pencarian (find).

#include <queue>
// library yang digunakan untuk implementasi struktur data antrian (queue).

#include <iomanip> 
// Library untuk std::setw

using namespace std;

// Struct User
struct UserData {
    string username;                                       
    string password;   
};

// Struct Mobil
struct Mobil {
    int id_mobil;
    string tipe;
    double harga;
    string warna;
    
    // Pointer Mobil
    Mobil* next;
    Mobil* prev;

    Mobil(int id, const string& t, double h, const string& s) :
        id_mobil(id), tipe(t), harga(h), warna(s), next(nullptr), prev(nullptr) {}
};

// Struct Data Mobil
struct dataMobil {
    string kode;
    string merk;

    // Pointer Data Mobil
    Mobil* daftarMobil;
    dataMobil* tailMobil = nullptr;
    dataMobil* next;
    dataMobil* prev;
    

    dataMobil(const string& k, const string& m) :
        kode(k), merk(m), next(nullptr), prev(nullptr), daftarMobil(nullptr) {}
};

// Struct Data Penyewa
struct dataPenyewa {
    int id_penyewa;
    string nama;
    string alamat;
    string no_ktp;
    int no_hp;

    // Pointer Data Penyewa
    dataMobil* mobil;
    dataPenyewa* next;
    dataPenyewa* prev;
};

struct dataSewa {
    int idSewa;
    int idMobil;
    int idPenyewa;
    string namaPenyewa; 
    string tipe;
    int durasiSewa;
    double biayaTotal;
    dataSewa* next;

    dataSewa(int idSewa, int idMobil, int idPenyewa, const string& namaPenyewa, int durasiSewa, double biayaTotal)
        : idSewa(idSewa), idMobil(idMobil), idPenyewa(idPenyewa), namaPenyewa(namaPenyewa), durasiSewa(durasiSewa), biayaTotal(biayaTotal), next(nullptr) {}
};


// Deklarasi variabel pointer Data Penyewa, Data Mobil, Data Sewa
dataSewa* headSewa = nullptr;
dataSewa* tailSewa = nullptr;

dataPenyewa* head = NULL;
dataPenyewa* tail = NULL;

dataPenyewa* headPenyewa = NULL;
dataPenyewa* tailPenyewa = NULL;

dataMobil* headMobil = nullptr;
dataMobil* tailMobil = nullptr;

dataPenyewa* dataPenyewa_head = nullptr;
dataMobil* dataMobil_head = nullptr;


// Fungsi untuk membersihkan layar
void cls() {
    std::this_thread::sleep_for(std::chrono::seconds(1));
    std::cout << "Tekan Enter untuk membersihkan layar...";
    std::cin.get();

    #ifdef _WIN32 //windows
        system("cls");
    #else //linux
        system("clear");
    #endif
}

// Fungsi untuk menambah data mobil
void tambahDataMobil(dataMobil*& headMobil, dataMobil*& tailMobil) {
    cout << endl;
    cout << "-------------------------------------------------" << endl;
    cout << "           >>> Tambah Data Mobil <<<             " << endl;
    cout << "-------------------------------------------------" << endl;
    
    //Menu Tambah
    int pilihan;
    cout << "1. Tambah data mobil baru\n";
    cout << "2. Tambah data mobil pada kode yang sudah ada\n" << endl;
    cout << "Masukan Pilihan (1-2) >> ";
    cin >> pilihan;

    // Jika Pilihan 1 
    if (pilihan == 1) {
        string kode, merk;
        cout << "-------------------------------------------------" << endl;
        cout << "Masukkan Kode Mobil : ";
        cin.ignore();
        getline(cin, kode);
        cout << "Masukkan Merk Mobil : ";
        getline(cin, merk);

        dataMobil* newNode = new dataMobil(kode, merk);

        // Tambahkan newNode ke dalam linked list dataMobil
        if (headMobil == nullptr) {
            headMobil = newNode;
            tailMobil = newNode;
        } else {
            tailMobil->next = newNode;
            newNode->prev = tailMobil;
            tailMobil = newNode;
        }

        // Tambahkan daftar mobil ke dalam dataMobil
        int BanyakData;
        cout << "-------------------------------------------------" << endl;
        cout << "Banyak data mobil yang ingin ditambahkan >> ";
        cin >> BanyakData;
        cout << "-------------------------------------------------" << endl;

        if (BanyakData > 0) {
            // Memulai iterasi untuk memasukkan data mobil sebanyak BanyakData
            for (int i = 0; i < BanyakData; i++) {
                int id_mobil;
                string tipe;
                double harga;
                string warna;

                cout << "Masukkan ID Mobil     : ";
                cin >> id_mobil;
                cout << "Masukkan Tipe Mobil   : ";
                cin.ignore();
                getline(cin, tipe);
                cout << "Masukkan Harga Sewa   : ";
                cin >> harga;
                cout << "Masukkan warna Mobil  : ";
                cin.ignore();
                getline(cin, warna);

                // Periksa apakah linked list sudah memiliki node Mobil
                Mobil* mobilNode = new Mobil{id_mobil, tipe, harga, warna};
                if (i == 0 && newNode->daftarMobil == nullptr) {
                    newNode->daftarMobil = mobilNode;
                } else {
                    // Gunakan kembali node Mobil yang sudah ada
                    Mobil* currentMobilNode = newNode->daftarMobil;
                    while (currentMobilNode->next != nullptr) {
                        currentMobilNode = currentMobilNode->next;
                    }
                    currentMobilNode->next = mobilNode;
                    mobilNode->prev = currentMobilNode;
                }
                cout << "-------------------------------------------------" << endl;
            }
        }

        cout << ">>> DATA BERHASIL DITAMBAHKAN <<<\n" << endl;
    

    // Jika Pilihan 2 Tambah data mobil pada kode yang sudah ada
    }  else if (pilihan == 2) {
    string kodeMobil;
    cout << "-------------------------------------------------" << endl;
    cout << "Masukkan Kode Mobil yang ingin ditambahkan: ";
    cin.ignore();
    getline(cin, kodeMobil);

    // Cari mobil dengan kode yang sudah ada
    dataMobil* currentMobil = headMobil;
    bool found = false;
    while (currentMobil != nullptr) {
        if (currentMobil->kode == kodeMobil) {
            found = true;
            break;
        }
        currentMobil = currentMobil->next;
    }

    if (found) {
        // Tanyakan berapa banyak data mobil yang ingin ditambahkan
        int banyakData;
        cout << "-------------------------------------------------" << endl;
        cout << "Banyak data mobil yang ingin ditambahkan >> ";
        cin >> banyakData;

        // Loop untuk memasukkan data mobil sebanyak yang diminta
        for (int i = 0; i < banyakData; i++) {
            int id_mobil;
            string tipe;
            double harga;
            string warna;

            cout << "-------------------------------------------------" << endl;
            cout << "Masukkan ID Mobil     : ";
            cin >> id_mobil;
            cout << "Masukkan Tipe Mobil   : ";
            cin.ignore();
            getline(cin, tipe);
            cout << "Masukkan Harga Sewa   : ";
            cin >> harga;
            cout << "Masukkan warna Mobil  : ";
            cin.ignore();
            getline(cin, warna);

            // Buat objek Mobil baru
            Mobil* mobilNode = new Mobil(id_mobil, tipe, harga, warna);

            // Tambahkan mobilNode ke dalam linked list daftarMobil pada mobil yang sudah ada
            if (currentMobil->daftarMobil == nullptr) {
                currentMobil->daftarMobil = mobilNode;
            } else {
                Mobil* currentMobilNode = currentMobil->daftarMobil;
                while (currentMobilNode->next != nullptr) {
                    currentMobilNode = currentMobilNode->next;
                }
                currentMobilNode->next = mobilNode;
                mobilNode->prev = currentMobilNode;
            }

            cout << "-------------------------------------------------" << endl;
        }

        cout << ">>> DATA BERHASIL DITAMBAHKAN <<<\n" << endl;
    } else {
        cout << "Mobil dengan kode " << kodeMobil << " tidak ditemukan." << endl;
    }
} else {
    cout << ">>> PILIHAN TIDAK VALID <<<\n" << endl;
}

    //inputan dari admin/user sblm membersihkan layar
    cls();

}


// Prosedur untuk Melihat Detail Mobil
void lihatDataMobil(dataMobil* headMobil) {
    cout << endl;
    cout << "                >>> Data Mobil <<<               " << endl;
    cout << "-------------------------------------------------" << endl;
    cout << "      Kode Mobil     |         Merk Mobil        " << endl;
    cout << "-------------------------------------------------" << endl;

    dataMobil* currentMobil = headMobil;

    // Melakukan iterasi melalui linked list dataMobil
    while (currentMobil != nullptr) {
        cout << setw(18) << currentMobil->kode << " | " << setw(25) << currentMobil->merk << endl;
        currentMobil = currentMobil->next;
    }
    
    string kode;
    cout << "-------------------------------------------------" << endl;
    cout << "Masukkan Kode Mobil yang ingin dicari : ";
    getline(cin, kode);

    // Menghapus spasi ekstra dari kode
    kode.erase(remove(kode.begin(), kode.end(), ' '), kode.end());

    // Cari mobil dengan kode yang sesuai
    currentMobil = headMobil;
    bool found = false;

    while (currentMobil != nullptr) {
        if (currentMobil->kode == kode) {
            found = true;
            break;
        }
        currentMobil = currentMobil->next;
    }

    if (found) {
        cout << endl;
        cout << "        >>> Data Mobil (Kode: " << kode << ") <<<" << endl;
        cout << "-------------------------------------------------" << endl;
        Mobil* mobilNode = currentMobil->daftarMobil;

        // Iterasi melalui linked list dataMobil untuk menampilkan informasi mobil yang sesuai dengan kode
        while (mobilNode != nullptr) {
            cout << "ID Mobil              : " << mobilNode->id_mobil << endl;
            cout << "Tipe Mobil            : " << mobilNode->tipe << endl;
            cout << "Harga Sewa (per hari) : " << fixed << setprecision(2) << mobilNode->harga << endl;
            cout << "warna                 : " << mobilNode->warna << endl;
            cout << "-------------------------------------------------" << endl;
            mobilNode = mobilNode->next;
            }
        } else {
            cout << "Mobil dengan kode " << kode << " tidak ditemukan." << endl;
        }

    //inputan dari admin/user sblm membersihkan layar
    cls();
}


// Prosedur untuk Mengubah data mobil
void ubahDataMobil(dataMobil* headMobil) {

    // Tampilkan data mobil
     cout << endl;
    cout << "           >>> Data Mobil <<<          " << endl;
    cout << "---------------------------------------" << endl;
    cout << "   Kode Mobil   |      Merk Mobil      " << endl;
    cout << "---------------------------------------" << endl;

    dataMobil* currentMobil = headMobil;

    while (currentMobil != nullptr) {
        cout << setw(11) << currentMobil->kode << " | " << setw(11) << currentMobil->merk << endl;
        currentMobil = currentMobil->next;
    }

    // Meminta pengguna memilih kode mobil
    cout << "---------------------------------------" << endl;
    cout << "kode mobil yang akan diubah >> ";
    string kodePilihan;
    getline(cin, kodePilihan);

    // Mencari mobil dengan kode yang dipilih
    currentMobil = headMobil;

    while (currentMobil != nullptr) {
        if (currentMobil->kode == kodePilihan) {
            // Tampilkan detail mobil
            cout << endl;
            cout << "      >>> Data Mobil (Kode: " << kodePilihan << ") <<<" << endl;
            cout << "---------------------------------------" << endl;
            Mobil* mobilNode = currentMobil->daftarMobil;

            while (mobilNode != nullptr) {
                cout << "ID Mobil              : " << mobilNode->id_mobil << endl;
                cout << "Tipe Mobil            : " << mobilNode->tipe << endl;
                cout << "Harga Sewa (per hari) : " << fixed << setprecision(2) << mobilNode->harga << endl;
                cout << "warna                 : " << mobilNode->warna << endl;
                cout << "---------------------------------------" << endl;
                mobilNode = mobilNode->next;
            }

            // Minta ID Mobil yang ingin diubah
            cout << "Masukkan ID Mobil yang akan diubah >> ";
            int idMobil;
            cin >> idMobil;

            mobilNode = currentMobil->daftarMobil;
            while (mobilNode != nullptr) {
                if (mobilNode->id_mobil == idMobil) {
                    // Lakukan perubahan
                    cout << "---------------------------------------" << endl;
                    cout << "Informasi yang ingin diubah : " << endl;
                    cout << "[1]. Tipe Mobil" << endl;
                    cout << "[2]. Harga" << endl;
                    cout << "[3]. warna" << endl;
                    cout << "Masukan Pilihan (1-3) >> ";
                    int opsi;
                    cin >> opsi;
                    cout << "---------------------------------------" << endl;

                    switch (opsi) {
                        case 1:
                            cout << "Masukkan tipe mobil yang baru   : ";
                            cin.ignore();
                            getline(cin, mobilNode->tipe);
                            break;

                        case 2:
                            cout << "Masukkan harga mobil yang baru  : ";
                            cin >> mobilNode->harga;
                            break;

                        case 3:
                            cout << "Masukkan warna mobil yang baru  : ";
                            cin.ignore();
                            getline(cin, mobilNode->warna);
                            break;

                        default:
                            cout << "Pilihan tidak valid." << endl;
                            break;
                    }
                    cout << "---------------------------------------" << endl;

                    cout << endl;
                    cout << ">>> DATA MOBIL BERHASIL DIUBAH <<<\n" << endl;

                    //inputan dari admin/user sblm membersihkan layar
                    cls();

                    return;
                }
                mobilNode = mobilNode->next;
            }
            cout << ">>> ID MOBIL TIDAK DITEMUKAN <<<\n" << endl;
            return;
        }
        currentMobil = currentMobil->next;
    }
    cout << "Mobil dengan kode " << kodePilihan << " tidak ditemukan." << endl;

    //inputan dari admin/user sblm membersihkan layar
    cls();
}

// Prosedur untuk Menghapus data mobil
void hapusDataMobil(dataMobil* headMobil) {
    cout << endl;
    cout << "---------------------------------------" << endl;
    cout << "        >>> Hapus Data Mobil <<<       " << endl;
    cout << "---------------------------------------" << endl;

    int pilihan;
    cout << "Pilihan Hapus Data Mobil: " << endl;
    cout << "1. Hapus Semua Data Mobil" << endl;
    cout << "2. Hapus Salah Satu Data Mobil (byID)" << endl;
    cout << "---------------------------------------" << endl;
    cout << "Masukkan pilihan (1-2) >> ";
    cin >> pilihan;

    // Tampilkan data mobil
    cout << endl;
    cout << "           >>> Data Mobil <<<          " << endl;
    cout << "---------------------------------------" << endl;
    cout << "   Kode Mobil   |      Merk Mobil      " << endl;
    cout << "---------------------------------------" << endl;

    dataMobil* currentMobil = headMobil;

    while (currentMobil != nullptr) {
        cout << setw(16) << currentMobil->kode << " | " << setw(16) << currentMobil->merk << endl;
        currentMobil = currentMobil->next;
    }

    string kodeMobil;
    cout << "---------------------------------------" << endl;
    cout << "Masukkan Kode Mobil yang ingin dihapus " ;
    cin.ignore();
    getline(cin, kodeMobil);

    currentMobil = headMobil;

    while (currentMobil != nullptr) {
        if (currentMobil->kode == kodeMobil) {
            Mobil* mobilNode = currentMobil->daftarMobil;

            // Tampilkan data mobil sebelum dihapus
            cout << " >> Data Mobil sebelum dihapus << " << endl;
            cout << "---------------------------------------" << endl;
            while (mobilNode != nullptr) {
                cout << "ID Mobil              : " << mobilNode->id_mobil << endl;
                cout << "Tipe Mobil            : " << mobilNode->tipe << endl;
                cout << "Harga Sewa (per hari) : " << fixed << setprecision(2) << mobilNode->harga << endl;
                cout << "warna                 : " << mobilNode->warna << endl;
                cout << "---------------------------------------" << endl;
                mobilNode = mobilNode->next;
            }

            if (pilihan == 1) {
                // Hapus semua data mobil pada kode tertentu
                mobilNode = currentMobil->daftarMobil;
                while (mobilNode != nullptr) {
                    Mobil* nextNode = mobilNode->next;
                    delete mobilNode;
                    mobilNode = nextNode;
                }

                currentMobil->daftarMobil = nullptr;
                cout << endl;
                cout << " >>> SEMUA DATA KODE " << kodeMobil << " BERHASIL DIHAPUS <<<" << endl;
                cout << endl;

            } else if (pilihan == 2) {
                // Hapus data mobil pada kode tertentu berdasarkan ID
                int idMobilPilihan;
                cout << "---------------------------------------" << endl;
                cout << "Masukkan ID mobil yang ingin dihapus : ";
                cin >> idMobilPilihan;

                mobilNode = currentMobil->daftarMobil;
                Mobil* prevMobilNode = nullptr;

                while (mobilNode != nullptr) {
                    if (mobilNode->id_mobil == idMobilPilihan) {
                        if (prevMobilNode == nullptr) {
                            currentMobil->daftarMobil = mobilNode->next;
                        } else {
                            prevMobilNode->next = mobilNode->next;
                        }
                        // Menghapus elemen yang ditemukan dari linked list
                        delete mobilNode;

                        cout << endl;
                        cout << " >>> DATA MOBIL DENGAN ID " << idMobilPilihan << " PADA KODE " << kodeMobil << " BERHASIL DIHAPUS" << endl;
                        cout << endl;
                        break;
                    }
                    prevMobilNode = mobilNode;
                    mobilNode = mobilNode->next;
                }

                if (mobilNode == nullptr) {
                    cout << "Mobil dengan ID " << idMobilPilihan << " pada kode " << kodeMobil << " tidak ditemukan." << endl;
                }
            }
            break;
        }

        currentMobil = currentMobil->next;
    }

    if (currentMobil == nullptr) {
        cout << "Mobil dengan kode " << kodeMobil << " tidak ditemukan." << endl;
    }

    // Wait for user input before clearing the screen
    cls();
}

// Fungsi untuk merge dua linked list secara ascending atau descending
Mobil* merge(Mobil* left, Mobil* right, bool ascending) {
    if (left == nullptr) {
        return right;
    }
    if (right == nullptr) {
        return left;
    }

    Mobil* result = nullptr;

    if ((left->id_mobil < right->id_mobil && ascending) || (left->id_mobil > right->id_mobil && !ascending)) {
        result = left;
        result->next = merge(left->next, right, ascending);
    } else {
        result = right;
        result->next = merge(left, right->next, ascending);
    }

    return result;
}

// Fungsi untuk mendapatkan pertengahan linked list
Mobil* getMiddle(Mobil* head) {
    if (head == nullptr) {
        return nullptr;
    }

    Mobil* slow = head;
    Mobil* fast = head->next;

    while (fast != nullptr) {
        fast = fast->next;
        if (fast != nullptr) {
            slow = slow->next;
            fast = fast->next;
        }
    }

    return slow;
}

// Fungsi untuk melakukan merge sort pada linked list
Mobil* mergeSort(Mobil* head, bool ascending) {
    if (head == nullptr || head->next == nullptr) {
        return head;
    }

    Mobil* middle = getMiddle(head);
    Mobil* nextToMiddle = middle->next;
    middle->next = nullptr;

    Mobil* left = mergeSort(head, ascending);
    Mobil* right = mergeSort(nextToMiddle, ascending);

    return merge(left, right, ascending);
}

// Fungsi untuk sorting dataMobil berdasarkan ID Mobil
void sortDataMobil(dataMobil* headMobil, bool ascending) {
    dataMobil* currentMobil = headMobil;

    while (currentMobil != nullptr) {
        currentMobil->daftarMobil = mergeSort(currentMobil->daftarMobil, ascending);
        currentMobil = currentMobil->next;
    }
}

// Fungsi untuk menampilkan data mobil setelah sorting
void sortingDataMobil(dataMobil*& headMobil) {
    cout << endl;
    cout << "-------------------------------------------------" << endl;
    cout << "           >>> Sorting Data Mobil <<<             " << endl;
    cout << "-------------------------------------------------" << endl;

    // Menampilkan pilihan ascending atau descending
    char pilihanUrutan;
    cout << "Pilih urutan sorting " << endl;
    cout << "A. Ascending" << endl;
    cout << "D. Descending" << endl;
    cout << "-------------------------------------------------" << endl;
    cout << "Masukkan pilihan (A/D) >> ";
    cin >> pilihanUrutan;

    bool ascending = (pilihanUrutan == 'A' || pilihanUrutan == 'a');

    // Menampilkan pilihan kode mobil yang ingin di-sort
    string kodeMobil;
    cout << "-------------------------------------------------" << endl;
    cout << " Masukkan Kode Mobil yang ingin disorting : " ;
    cin.ignore();
    getline(cin, kodeMobil);

    // Menghapus spasi ekstra dari kode
    kodeMobil.erase(remove(kodeMobil.begin(), kodeMobil.end(), ' '), kodeMobil.end());

    dataMobil* currentMobil = headMobil;

    while (currentMobil != nullptr) {
        if (currentMobil->kode == kodeMobil) {
            currentMobil->daftarMobil = mergeSort(currentMobil->daftarMobil, ascending);

            Mobil* mobilNode = currentMobil->daftarMobil;
            while (mobilNode != nullptr) {
                cout << "-------------------------------------------------" << endl;
                cout << "ID Mobil              : " << mobilNode->id_mobil << endl;
                cout << "Tipe Mobil            : " << mobilNode->tipe << endl;
                cout << "Harga Sewa (per hari) : " << fixed << setprecision(2) << mobilNode->harga << endl;
                cout << "warna                : " << mobilNode->warna << endl;
                cout << "-------------------------------------------------" << endl;
                mobilNode = mobilNode->next;
            }

            break;
        }

        currentMobil = currentMobil->next;
    }

    // Wait for user input before clearing the screen
    cls();

    if (currentMobil == nullptr) {
        cout << "Mobil dengan kode " << kodeMobil << " tidak ditemukan." << endl;
    }
}


// Fungsi untuk menyewa mobil untuk user
void sewaMobil(dataMobil* headMobil, dataPenyewa* headPenyewa, dataPenyewa* tailPenyewa) {
    cout << "-------------------------------------------------" << endl;
    cout << "              >>> Sewa mobil <<<                 " << endl;
    cout << "-------------------------------------------------" << endl;

    // Tampilkan data mobil
    lihatDataMobil(headMobil);

    cout << "Pilih ID mobil yang ingin disewa: ";
    int idMobilPilihan;
    cin >> idMobilPilihan;

    // Temukan mobil berdasarkan ID yang dipilih
    dataMobil* currentMobil = headMobil;
    Mobil* mobilDipilih = nullptr;

    while (currentMobil != nullptr) {
        Mobil* mobilNode = currentMobil->daftarMobil;
        while (mobilNode != nullptr) {
            if (mobilNode->id_mobil == idMobilPilihan) {
                mobilDipilih = mobilNode;
                break;
            }
            mobilNode = mobilNode->next;
        }
        if (mobilDipilih != nullptr) {
            break;
        }
        currentMobil = currentMobil->next;
    }

    if (mobilDipilih != nullptr) {
        // Menampilkan detail mobil yang dipilih
        cout << "-------------------------------------------------" << endl;
        cout << "            >>> Detail Mobil (ID: " << idMobilPilihan << ") <<<" << endl;
        cout << "-------------------------------------------------" << endl;
        cout << "Tipe Mobil       : " << mobilDipilih->tipe << endl;
        cout << "Harga (per hari) : " << fixed << setprecision(2) << mobilDipilih->harga << endl;
        cout << "Warna            : " << mobilDipilih->warna << endl;
        cout << "-------------------------------------------------" << endl;

        // Minta data penyewa
        dataPenyewa* newPenyewa = new dataPenyewa();
        cout << "Masukkan ID Penyewa       : ";
        cin >> newPenyewa->id_penyewa;
        cout << "Masukkan Nama Penyewa     : ";
        cin.ignore();
        getline(cin, newPenyewa->nama);
        cout << "Masukkan Alamat Penyewa   : ";
        getline(cin, newPenyewa->alamat);
        cout << "Masukkan No. KTP Penyewa  : ";
        getline(cin, newPenyewa->no_ktp);
        cout << "Masukkan No. HP Penyewa   : ";
        cin >> newPenyewa->no_hp;

        // Minta durasi sewa
        int durasiSewa;
        cout << "Masukkan durasi sewa (dalam hari): ";
        cin >> durasiSewa;

        cout << "-------------------------------------------------" << endl;

        // Hitung biaya total
        double biayaTotal = mobilDipilih->harga * durasiSewa;

        // Deklarasikan dan inisialisasikan variabel idSewaBaru
        int idSewaBaru = 1;

        // Kemudian, cari ID sewa yang belum digunakan
        dataSewa* currentSewa = headSewa;
        while (currentSewa != nullptr) {
            if (currentSewa->idSewa >= idSewaBaru) {
                idSewaBaru = currentSewa->idSewa + 1;
            }
            currentSewa = currentSewa->next;
        }

        // Masukkan data sewa ke dalam daftar headSewa
        dataSewa* newSewa = new dataSewa(idSewaBaru, idMobilPilihan, newPenyewa->id_penyewa, newPenyewa->nama, durasiSewa, biayaTotal);
        newSewa->next = headSewa;
        headSewa = newSewa;


        // Tampilkan informasi sewa
        cout << "-------------------------------------------------" << endl;
        cout << "                >>> Informasi Sewa <<<              " << endl;
        cout << "-------------------------------------------------" << endl;
        cout << "Mobil yang Disewa : " << mobilDipilih->tipe << " (ID: " << idMobilPilihan << ")" << endl;
        cout << "Durasi Sewa       : " << durasiSewa << " hari" << endl;
        cout << "Biaya Total       : " << fixed << setprecision(2) << biayaTotal << " USD" << endl;
        cout << "Penyewa (ID)      : " << newPenyewa->nama << " (" << newPenyewa->id_penyewa << ")" << endl;
        cout << "Penyewa           : " << newPenyewa->nama << endl;
        cout << "Alamat Penyewa    : " << newPenyewa->alamat << endl;
        cout << "No. KTP Penyewa   : " << newPenyewa->no_ktp << endl;
        cout << "No. HP Penyewa    : " << newPenyewa->no_hp << endl;
        cout << "-------------------------------------------------" << endl;
    } else {
        cout << "Mobil dengan ID " << idMobilPilihan << " tidak ditemukan." << endl;
    }

    //inputan dari admin/user sblm membersihkan layar
        std::this_thread::sleep_for(std::chrono::seconds(6));
        std::cin.get();

        #ifdef _WIN32 //windows
            system("cls");
        #else //linux
            system("clear");
        #endif
}


// Fungsi untuk Melihat salah satu Data Sewa 
void lihatDataSewa(dataSewa* headSewa, int idPenyewa) {
    cout << "-------------------------------------------------" << endl;
    cout << "     >>> Informasi Sewa (ID Penyewa: " << idPenyewa << ") <<<" << endl;
    cout << "-------------------------------------------------" << endl;

    dataSewa* currentSewa = headSewa;

         while (currentSewa != nullptr) {
            if (currentSewa->idPenyewa == idPenyewa) {
                cout << "ID Sewa               : " << currentSewa->idSewa << endl;
                cout << "Nama Penyewa          : " << currentSewa->namaPenyewa << endl;
                cout << "ID Mobil yang Disewa  : " << currentSewa->idMobil << endl;
                cout << "Durasi Sewa (hari)    : " << currentSewa->durasiSewa << endl;
                cout << "Biaya Total (Rp)      : " << fixed << setprecision(2) << currentSewa->biayaTotal << endl;
        }
        currentSewa = currentSewa->next;
    }
    cout << "-------------------------------------------------" << endl;

    //inputan dari admin/user sblm membersihkan layar
        std::this_thread::sleep_for(std::chrono::seconds(6));
        std::cin.get();

        #ifdef _WIN32 //windows
            system("cls");
        #else //linux
            system("clear");
        #endif
}


// Fungsi untuk login admin
void loginAdmin() {
    string usernameAdmin = "admin";
    string passAdmin = "172635";

    bool loginBerhasil = false;

    while (!loginBerhasil) {
        cout << endl;
        cout << "Silahkan Login sebagai Admin" << endl;
        cout << "----------------------------" << endl;

        string username_Admin, pass_Admin;
        cout << "Username (admin)  : ";
        cin >> username_Admin;
        cout << "Password (172635) : ";
        cin >> pass_Admin;

        if (usernameAdmin == username_Admin && passAdmin == pass_Admin) {
            cout << endl;
            cout << ">>> ANDA BERHASIL LOGIN SEBAGAI ADMIN <<<" << endl;
            loginBerhasil = true;

        //inputan dari admin/user sblm membersihkan layar
        std::this_thread::sleep_for(std::chrono::seconds(1));
        std::cin.get();

        #ifdef _WIN32 //windows
            system("cls");
        #else //linux
            system("clear");
        #endif
        } else {
            cout << endl;
            cout << ">>> LOGIN GAGAL. SILAHKAN COBA LAGI <<<" << endl;

            //inputan dari admin/user sblm membersihkan layar
            cls();
        }
    }
}

// Fungsi untuk registrasi pengguna
void registrasidataUser(vector<UserData>& registeredUsers) {
    UserData newUser;
    cout << "Silahkan Registrasi sebagai User" << endl;
    cout << "-------------------------------------------------------------" << endl;
    cout << "Username  : ";
    cin >> newUser.username;
    cout << "Password  : ";
    cin >> newUser.password;

    // Menyimpan data pengguna yang terdaftar ke dalam vektor
    registeredUsers.push_back(newUser);
     cout << endl;
    cout << ">>> REGISTRASI BERHASIL <<<" << endl;

    // Tunggu input dari pengguna sebelum membersihkan layar
    this_thread::sleep_for(chrono::seconds(1));
    cin.get();

    // Bersihkan layar
    cls();
}

// Fungsi untuk login pengguna dengan retry menggunakan antrian
void logindataUser(const vector<UserData>& registeredUsers) {
    string usernameInput, passInput;

    queue<string> antrianLogin; // Antrian untuk menyimpan percobaan login

    bool loginBerhasil = false;
    int maksPercobaan = 3; // Jumlah maksimal percobaan login yang diizinkan

    while (!loginBerhasil && maksPercobaan > 0) {
        cout << "Silahkan Login sebagai User" << endl;
        cout << "-------------------------------------------------------------" << endl;
        cout << "Username  : ";
        cin >> usernameInput;
        cout << "Password  : ";
        cin >> passInput;

        // Mengecek apakah username dan password sesuai dengan data yang terdaftar
        for (const auto& user : registeredUsers) {
            if (user.username == usernameInput && user.password == passInput) {
                cout << endl;
                cout << ">>> LOGIN BERHASIL. ANDA MASUK SEBAGAI USER <<<" << endl;
                loginBerhasil = true;
                break;
            }
        }

        if (!loginBerhasil) {
            cout << endl;
            cout << ">>> LOGIN GAGAL. SILAHKAN COBA LAGI <<<" << endl;
            antrianLogin.push("Percobaan: " + to_string(antrianLogin.size() + 1) + " - Username: " + usernameInput);
            maksPercobaan--;

            if (maksPercobaan > 0) {
                cout << "-------------------------------------------------------------" << endl;
                cout << "Sisa percobaan login: " << maksPercobaan << endl;
                cout << "-------------------------------------------------------------" << endl;
            } else {
                cout << endl;
                cout << "Anda telah melebihi batas percobaan login. Akun Anda terkunci." << endl;
                cout << "-------------------------------------------------------------" << endl;
                 exit(0);  // Keluar dari program setelah akun terkunci
            }

            // Tunggu input dari pengguna sebelum membersihkan layar
            this_thread::sleep_for(chrono::seconds(1));
            cin.get();

            // Bersihkan layar
            cls();
        }
    }

    // Tampilkan percobaan login dari antrian
    while (!antrianLogin.empty()) {
        cout << antrianLogin.front() << endl;
        antrianLogin.pop();
    }
    cout << "-------------------------------------------------------------" << endl;

}


// Prosedur Menu Admin
void menuAdmin() {

    int pilihan;

    // LOOP MENU ADMIN
    while (true) {
        cout << endl;
        cout << "=============== MENU ADMIN ==============" << endl;
        cout << "1. Tambah Data Mobil                     " << endl;
        cout << "2. Lihat Data Mobil                      " << endl;
        cout << "3. Ubah Data Mobil                       " << endl;
        cout << "4. Hapus Data Mobil                      " << endl;
        cout << "5. Sorting Data Mobil                    " << endl;
        cout << "6. Lihat Data Sewa                       " << endl;
        cout << "7. Kembali ke Menu Utama                 " << endl;
        cout << "=========================================" << endl;
        cout << "Masukkan pilihan Anda (1-7) >> ";
        cin >> pilihan;

        //inputan dari admin/user sblm membersihkan layar
        cls();

        switch (pilihan) {
            case 1:
                tambahDataMobil(headMobil, tailMobil);
                break;
            case 2:
                lihatDataMobil(headMobil); 
                break;
            case 3:
                ubahDataMobil(headMobil);
                break;
            case 4:
                hapusDataMobil(headMobil);
                break;
            case 5:
                sortingDataMobil(headMobil);
                break;
            case 6:
                int id_penyewaPilihan;
                cout << "Masukkan ID Penyewa yang ingin Anda lihat : ";
                cin >> id_penyewaPilihan;
                lihatDataSewa(headSewa, id_penyewaPilihan);
            case 7:
                return;
                break;
            default:
                cout << endl;
                cout << ">>> PILIHAN TIDAK VALID. SILAHKAN COBA LAGI <<<" << endl;
        }
    }
}

// Prosedur Menu User
void menuUser() {

    bool loginBerhasil = true; 
    int pilihan;

    // LOOP MENU USER
    while (true) {
        cout << endl;
        cout << "=============== MENU USER ===============" << endl;
        cout << "1. Sewa mobil                            " << endl;
        cout << "2. Lihat Data Sewa                       " << endl;
        cout << "3. Kembali ke Menu Utama                 " << endl;
        cout << "=========================================" << endl;
        cout << "Masukkan pilihan Anda (1-3) >> ";
        cin >> pilihan;

        //inputan dari admin/user sblm membersihkan layar
        cls();

        switch (pilihan) {
            case 1:
                sewaMobil(headMobil, headPenyewa, tailPenyewa);
                break;
            case 2:
                int idPenyewaPilihan;
                cout << "Masukkan ID Penyewa yang ingin Anda lihat: ";
                cin >> idPenyewaPilihan;
                lihatDataSewa(headSewa, idPenyewaPilihan);
            case 3:
                return;
                break;
            default:
                cout << endl;
                cout << ">>> PILIHAN TIDAK VALID. SILAHKAN COBA LAGI <<<" << endl;
        }
        }
}

// Fungsi Main
int main() {

    cout << ".-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-" << endl;
    cout << "|                                                                                |" << endl;
    cout << "|              <<<<   Selamat Datang di Program Rental Mobil   >>>>              |" << endl;
    cout << "|                                                                                |" << endl;
    cout << ".-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-" << endl;
    cout << endl;

    // Deklarasi variabel pointer Data Penyewa, Data Mobil, Data Sewa
    dataMobil* headMobil = nullptr;
    dataMobil* tailMobil = nullptr;
    dataSewa* headSewa = nullptr;
    dataSewa* tailSewa = nullptr;
    dataPenyewa* headPenyewa = nullptr;
    dataPenyewa* tailPenyewa = nullptr;

   // Membuat vektor untuk menyimpan data user yang terdaftar
    vector<UserData> registeredUsers;

    int pilihan;
    string usernameUser, passUser;

    // LOOP MENU UTAMA
    while (true) {
        cout << endl;
        cout << "========== MENU UTAMA =========" << endl;
        cout << "1. Admin                       " << endl;
        cout << "2. User                        " << endl;
        cout << "3. Exit                        " << endl;
        cout << "===============================" << endl;
        cout << "Masukkan pilihan Anda (1-3) >> ";
        cin >> pilihan;

        //inputan dari admin/user sblm membersihkan layar
        cls();
  
        switch (pilihan) {
            case 1:
                // Login Admin
                loginAdmin();

                    // Jika login berhasil, masuk ke menu admin
                    menuAdmin();
                    break;
            case 2:
                // Operasi User
                cout << endl;
                cout << "1. Registrasi" << endl;
                cout << "2. Login" << endl;
                cout << "=============================" << endl;
                cout << "Masukkan pilihan Anda (1-2) >> ";
                cin >> pilihan;

                //inputan dari admin/user sblm membersihkan layar
                cls();

                switch (pilihan) {
                    case 1:
                        // Registrasi User
                        registrasidataUser(registeredUsers);
                        cout << endl;
                        break;
                        
                    case 2:
                        // Login User
                        logindataUser(registeredUsers);
                        // Jika login berhasil, Masuk ke Menu User
                            menuUser();
                            break;

                        default:
                            cout << endl;
                            cout << ">>> PILIHAN TIDAK VALID. SILAHKAN COBA LAGI <<<" << endl;
                            break;
                    }
                    break;

                case 3:
                    cout << endl;
                    cout << ">>> ANDA KELUAR DARI PROGRAM <<<" << endl;
                    return 0;

                default:
                    cout << endl;
                    cout << ">>> PILIHAN TIDAK VALID. SILAHKAN COBA LAGI <<<" << endl;
            }
        }

    return 0;
}
