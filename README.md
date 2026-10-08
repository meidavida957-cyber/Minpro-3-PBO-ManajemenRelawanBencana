# Minpro-3-PBO-ManajemenRelawanBencana

## Sistem Manajemen Relawan Bencana

## Daftar Isi
1. [Deskripsi Singkat Program](#1-deskripsi-singkat-program)
2. [Struktur Package](#2-struktur-package)
3. [Penjelasan Setiap Class](#3-penjelasan-setiap-class)
4. [Alur Program](#4-alur-program)
5. [Penerapan Encapsulation dan Inheritance](#5-penerapan-encapsulation-dan-inheritance)
6. [Penerapan Polymorphism dan Abstraction](#6-penerapan-polymorphism-dan-abstraction)
7. [Penerapan Nilai Tambah: Interface](#7-penerapan-nilai-tambah-interface)
8. [Validasi Input](#8-validasi-input)
9. [Contoh Hasil Program](#9-contoh-hasil-program)
10. [Perbaikan dari Masukan Mini Project 2](#10-perbaikan-dari-masukan-mini-project-2)
11. [Cara Menjalankan dan Identitas](#11-cara-menjalankan-dan-identitas)

---

## 1. Deskripsi Singkat Program
Sistem Manajemen Relawan Bencana adalah program berbasis console (Java) yang digunakan untuk mengelola data relawan, data bencana, dan penempatan relawan pada bencana tertentu. Program ini merupakan pengembangan dari Mini Project 2.

**Masalah yang diselesaikan:** pada saat terjadi bencana, petugas perlu mengetahui siapa saja relawan yang tersedia, bencana apa yang sedang ditangani, dan relawan mana yang bertugas di bencana tertentu. Program ini menyimpan dan mengelola informasi tersebut.

**Pengembangan pada Mini Project 3:**
- **Abstraction**: `Relawan` menjadi *abstract class* yang memiliki *abstract method*.
- **Polymorphism**: *overriding* dan *overloading*.
- **MVC**: kode dipisah menjadi package `model`, `view`, dan `controller`.
- **Interface** (nilai tambah): `DapatDitugaskan`.
- Perbaikan dari masukan asisten praktikum pada Mini Project 2.

**Fitur menu:**

| No | Menu | Keterangan |
|----|------|------------|
| 1 | Tampilkan Relawan | Menampilkan semua relawan beserta informasi sesuai jenisnya |
| 2 | Tampilkan Bencana | Menampilkan semua data bencana |
| 3 | Tambah Relawan | Menambah relawan Umum, Medis, atau Logistik |
| 4 | Tambah Bencana | Menambah data bencana baru |
| 5 | Hapus Relawan | Menghapus relawan berdasarkan ID |
| 6 | Hapus Bencana | Menghapus bencana berdasarkan ID |
| 7 | Tambah Penempatan | Menugaskan relawan ke suatu bencana |
| 8 | Tampilkan Penempatan | Menampilkan relawan yang bertugas di tiap bencana |
| 9 | Keluar | Mengakhiri program |

![Menu Utama]
<img width="154" height="140" alt="Screenshot 2026-10-08 182004" src="https://github.com/user-attachments/assets/10e47b21-5adf-4d07-913e-52f7b5eef594" />


---

## 2. Struktur Package
```
com.mycompany.minpro.pbo.manajemenrelawanbencana
├── Main.java
├── interfaces
│   └── DapatDitugaskan.java
├── model
│   ├── Relawan.java            (abstract class)
│   ├── RelawanUmum.java
│   ├── RelawanMedis.java
│   ├── RelawanLogistik.java
│   ├── Bencana.java
│   └── Penempatan.java
├── controller
│   └── ManajemenRelawan.java
└── view
    └── Menu.java
```

**Penerapan struktur MVC:**

| Bagian | Class | Peran |
|--------|-------|-------|
| **Model** | `Relawan`, `RelawanUmum`, `RelawanMedis`, `RelawanLogistik`, `Bencana`, `Penempatan` | Merepresentasikan data dan perilaku objek |
| **View** | `Menu` | Bertanggung jawab atas tampilan menu utama |
| **Controller** | `ManajemenRelawan` | Mengelola logika data: tambah, cari, hapus, tampil, dan penempatan |
| **Interface** | `DapatDitugaskan` | Kontrak yang harus dipenuhi oleh relawan |
| **Entry point** | `Main` | Titik masuk program, membaca input pengguna, dan memanggil controller |

---

## 3. Penjelasan Setiap Class

### Package `interfaces`
**`DapatDitugaskan`**: interface dengan satu method `getTugasUtama()`. Menjadi kontrak bahwa setiap pihak yang dapat ditugaskan harus memiliki tugas utama.

### Package `model`
**`Relawan` (abstract class)**
Class induk semua jenis relawan. Memiliki atribut `id`, `nama`, `alamat`, `noHp`, dan `keahlian` (semuanya `private`) beserta getter dan setter. Method penting:
- `getJenis()`: *abstract method*, wajib diisi subclass.
- `tampilkanInfo()`: menampilkan data relawan secara lengkap, termasuk tugas utama.
- `tampilkanInfo(boolean ringkas)`: versi *overloading*; jika `true` menampilkan satu baris ringkas (ID, nama, jenis).

**`RelawanUmum`**
Subclass `Relawan` untuk relawan tanpa spesialisasi. Tugas utama: evakuasi dan pendataan warga.

**`RelawanMedis`**
Subclass `Relawan` dengan tambahan atribut `spesialisasi`. Tugas utama: pertolongan pertama dan perawatan korban. Meng-override `tampilkanInfo()` untuk menampilkan jenis dan spesialisasi.

**`RelawanLogistik`**
Subclass `Relawan` dengan tambahan atribut `jenisLogistik`. Tugas utama: distribusi bantuan dan pengelolaan gudang. Meng-override `tampilkanInfo()` untuk menampilkan jenis logistik.

**`Bencana`**
Menyimpan `idBencana`, `namaBencana`, `lokasi`, `jenisBencana`, dan `status`.

**`Penempatan`**
Menghubungkan satu `Relawan` dengan satu `Bencana`. Class ini menunjukkan relasi antar objek (*association*).

### Package `controller`
**`ManajemenRelawan`**
Menyimpan tiga `ArrayList`: `daftarRelawan`, `daftarBencana`, dan `daftarPenempatan`. Method utamanya:

| Method | Fungsi |
|--------|--------|
| `tambahRelawan()` / `tambahBencana()` | Menambah data, menolak ID yang sudah ada |
| `tampilkanRelawan()` / `tampilkanBencana()` | Menampilkan seluruh data |
| `cariRelawan()` / `cariBencana()` | Mencari data berdasarkan ID |
| `hapusRelawan()` / `hapusBencana()` | Menghapus data berdasarkan ID |
| `tambahPenempatan()` | Membuat penempatan; mengecek ID ada dan tidak duplikat |
| `tampilkanPenempatan()` | Menampilkan seluruh penempatan |

Konstruktornya mengisi dummy data awal (relawan `R001` dan bencana `B001`).

### Package `view`
**`Menu`**
Berisi `tampilkanMenu()` untuk mencetak menu utama.

### `Main`
Berisi method-method terpisah agar mudah dibaca:

| Method | Fungsi |
|--------|--------|
| `main()` | Perulangan *do-while* yang menampilkan menu hingga pengguna memilih 9 |
| `bacaPilihan()` | Membaca dan mengubah input menu menjadi angka |
| `prosesMenu()` | `switch` yang memanggil fitur sesuai pilihan |
| `tambahRelawan()` | Input dan validasi data relawan |
| `tambahBencana()` | Input dan validasi data bencana |
| `tambahPenempatan()` | Input ID relawan dan ID bencana |

---

## 4. Alur Program
1. Program dijalankan dari `Main.main()`.
2. Objek `ManajemenRelawan` dibuat dan langsung mengisi dummy data.
3. `Menu.tampilkanMenu()` menampilkan menu utama.
4. `bacaPilihan()` membaca input pengguna.
5. `prosesMenu()` memeriksa pilihan:
   - Jika bukan 1-9 (termasuk huruf), tampil pesan *"Input salah, silakan input ulang sesuai ketentuan (1-9)."*
   - Jika valid, fitur terkait dijalankan.
6. Detail setiap menu:
   - **Menu 1 dan 2**: controller menelusuri list dan memanggil `tampilkanInfo()` tiap objek.
   - **Menu 3**: pengguna memilih jenis relawan, lalu mengisi ID, nama, alamat, no. HP, dan keahlian. Untuk Medis ditambah spesialisasi, untuk Logistik ditambah jenis logistik. Setelah lolos validasi, objek yang sesuai dibuat (`RelawanUmum`, `RelawanMedis`, atau `RelawanLogistik`) dan dikirim ke `tambahRelawan()`.
   - **Menu 4**: pengguna mengisi ID, nama, lokasi, jenis, dan status. Jika ada yang kosong, data tidak disimpan.
   - **Menu 5 dan 6**: pengguna memasukkan ID. Jika ditemukan, data dihapus; jika tidak, tampil pesan tidak ditemukan.
   - **Menu 7**: pengguna memasukkan ID relawan dan ID bencana. Controller mengecek: relawan ada, bencana ada, dan pasangan tersebut belum pernah terdaftar. Jika lolos, objek `Penempatan` dibuat.
   - **Menu 8**: seluruh penempatan ditampilkan.
7. Setelah satu menu selesai, program kembali ke langkah 3.
8. Jika pengguna memilih **9**, tampil "Program selesai." dan program berakhir.

```
Mulai
  |
  v
Main -> buat ManajemenRelawan (isi dummy data)
  |
  v
+-> Tampilkan menu
|     |
|     v
|   Baca pilihan --(bukan 1-9)--> Pesan "Input salah" --+
|     |                                                  |
|     v (1-9)                                            |
|   Proses menu (1 s.d. 8)                               |
|     |                                                  |
+-----+--------------------------------------------------+
|
+--(pilih 9)--> "Program selesai" --> Selesai
```

---

## 5. Penerapan Encapsulation dan Inheritance

### Encapsulation
*Encapsulation* adalah pembungkusan data agar tidak dapat diakses langsung dari luar class. Pada program ini, seluruh atribut dibuat `private` dan hanya bisa diakses lewat getter dan setter.

```java
public abstract class Relawan implements DapatDitugaskan {
    private String id;
    private String nama;
    private String alamat;
    private String noHp;
    private String keahlian;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    ...
}
```

Penerapan yang sama ada pada:
- `Bencana` (`idBencana`, `namaBencana`, `lokasi`, `jenisBencana`, `status`)
- `Penempatan` (`relawan`, `bencana`)
- `RelawanMedis` (`spesialisasi`) dan `RelawanLogistik` (`jenisLogistik`)
- `ManajemenRelawan` (ketiga `ArrayList` bersifat `private`)

Contoh akses dari luar class: `controller` membaca ID lewat `relawan.getId()`, bukan `relawan.id`.

### Inheritance
*Inheritance* adalah pewarisan atribut dan method dari superclass ke subclass menggunakan kata kunci `extends`.

```java
public class RelawanMedis extends Relawan {
    private String spesialisasi;

    public RelawanMedis(String id, String nama, String alamat,
                        String noHp, String keahlian, String spesialisasi) {
        super(id, nama, alamat, noHp, keahlian);   // memanggil konstruktor Relawan
        this.spesialisasi = spesialisasi;
    }
}
```

```
            Relawan (abstract)
          /        |           \
RelawanUmum   RelawanMedis   RelawanLogistik
```

Manfaat: atribut umum (`id`, `nama`, `alamat`, `noHp`, `keahlian`) cukup ditulis satu kali di `Relawan`. Subclass hanya menambah atribut khususnya.

---

## 6. Penerapan Polymorphism dan Abstraction

### Abstraction
*Abstraction* menyembunyikan detail implementasi dan hanya menampilkan apa yang harus dimiliki. Diterapkan melalui *abstract class* dan *abstract method* pada `Relawan`:

```java
public abstract class Relawan implements DapatDitugaskan {
    public abstract String getJenis();   // tidak ada isi, wajib diisi subclass
    ...
}
```

Akibatnya:
1. `new Relawan(...)` tidak lagi bisa dilakukan, sehingga relawan umum dibuat lewat class `RelawanUmum`.
2. Setiap subclass **wajib** mengimplementasikan `getJenis()`. Jika tidak, program tidak dapat dikompilasi.
3. `getTugasUtama()` dari interface juga wajib diisi subclass karena `Relawan` tidak mengimplementasikannya.

### Polymorphism: Overriding
*Overriding* adalah penulisan ulang method milik superclass atau interface di subclass dengan nama dan parameter yang sama (ditandai `@Override`).

| Method | Dioverride di | Hasil |
|--------|---------------|-------|
| `getJenis()` | `RelawanUmum`, `RelawanMedis`, `RelawanLogistik` | "Relawan Umum" / "Relawan Medis" / "Relawan Logistik" |
| `getTugasUtama()` | ketiga subclass | Tugas berbeda untuk tiap jenis |
| `tampilkanInfo()` | `RelawanMedis`, `RelawanLogistik` | Menambah informasi spesialisasi / jenis logistik |

```java
// RelawanMedis
@Override
public String getJenis() { return "Relawan Medis"; }

@Override
public String getTugasUtama() { return "Pertolongan pertama dan perawatan korban"; }

@Override
public void tampilkanInfo() {
    super.tampilkanInfo();
    System.out.println("Jenis Relawan : Medis");
    System.out.println("Spesialisasi  : " + spesialisasi);
}
```

**Dynamic binding:** `ManajemenRelawan` menyimpan semua jenis relawan dalam satu `ArrayList<Relawan>`. Saat `relawan.tampilkanInfo()` dipanggil, Java otomatis menjalankan versi milik objek sebenarnya (Umum, Medis, atau Logistik). Inilah *polymorphism*: satu pemanggilan, hasil berbeda sesuai jenis objek.

```java
for (Relawan relawan : daftarRelawan) {
    relawan.tampilkanInfo();   // versi yang dijalankan tergantung jenis objek
}
```

![Tampilan Relawan](screenshots/02-tampil-relawan.png)

### Polymorphism: Overloading
*Overloading* adalah beberapa method bernama sama dalam satu class dengan daftar parameter berbeda. Pada `Relawan`:

```java
public void tampilkanInfo() { ... }                 // tanpa parameter: tampilan lengkap

public void tampilkanInfo(boolean ringkas) {        // dengan parameter: pilih format
    if (ringkas) {
        System.out.println(id + " | " + nama + " | " + getJenis());
    } else {
        tampilkanInfo();
    }
}
```

Java membedakan keduanya dari jumlah dan tipe parameter, bukan dari nama.

---

## 7. Penerapan Nilai Tambah: Interface

Nilai tambah yang diterapkan adalah **interface**, yaitu `DapatDitugaskan` di package `interfaces`.

```java
public interface DapatDitugaskan {
    String getTugasUtama();
}
```

**Letak penerapan:**

| Lokasi | Keterangan |
|--------|------------|
| `interfaces/DapatDitugaskan.java` | Deklarasi interface |
| `model/Relawan.java` | `public abstract class Relawan implements DapatDitugaskan` |
| `model/RelawanUmum.java`, `RelawanMedis.java`, `RelawanLogistik.java` | Mengimplementasikan `getTugasUtama()` dengan isi berbeda |
| `Relawan.tampilkanInfo()` | Memanggil `getTugasUtama()` untuk menampilkan baris "Tugas Utama" |

**Perbedaan abstract class dan interface pada program ini:**
- *Abstract class* `Relawan` menyimpan atribut dan perilaku bersama (data diri relawan).
- *Interface* `DapatDitugaskan` hanya berisi kontrak (apa yang harus ada), tanpa data.

---

## 8. Validasi Input

| Input | Aturan | Pesan jika salah |
|-------|--------|------------------|
| Pilihan menu | Angka 1-9 | "Input salah, silakan input ulang sesuai ketentuan (1-9)." |
| Jenis relawan | Angka 1-3 | "Jenis relawan tidak tersedia." |
| ID relawan | Format `R` + 3 angka | "Format ID tidak valid. Gunakan format R001." |
| ID bencana | Format `B` + 3 angka | "Format ID tidak valid. Gunakan format B001." |
| ID duplikat | Tidak boleh sama dengan data lama | "ID relawan/bencana sudah digunakan." |
| Nama, alamat, keahlian | Tidak boleh kosong | "Nama, alamat, dan keahlian tidak boleh kosong." |
| No. HP | Angka 10-13 digit | "No. HP harus berupa angka 10-13 digit." |
| Spesialisasi / jenis logistik | Tidak boleh kosong | Pesan tidak boleh kosong |
| Nama, lokasi, jenis, status bencana | Tidak boleh kosong | "Nama, lokasi, jenis, dan status tidak boleh kosong." |
| Penempatan | ID relawan dan bencana harus ada | "Relawan/Bencana tidak ditemukan." |
| Penempatan ganda | Pasangan relawan-bencana tidak boleh sama | "Relawan tersebut sudah ditugaskan di bencana yang sama." |

---

## 9. Contoh Hasil Program

**Input menu salah**

![Input Menu Salah](screenshots/03-input-menu-salah.png)

**Validasi tambah bencana**

![Validasi Bencana](screenshots/04-validasi-bencana.png)

**Penempatan ganda ditolak**

![Penempatan Ganda](screenshots/05-penempatan-ganda.png)

**Daftar penempatan**

![Tampil Penempatan](screenshots/06-tampil-penempatan.png)

---

## 10. Perbaikan dari Masukan Mini Project 2

| Masukan asisten | Perbaikan |
|-----------------|-----------|
| Input menu di luar 1-9 berulang tanpa pemberitahuan | Menambahkan pesan kesalahan pada `default` di `prosesMenu()` |
| Lokasi, jenis, dan status bencana tidak divalidasi sehingga bisa kosong | Seluruh isian dicek di `Main.tambahBencana()` |
| `tambahPenempatan` tidak mengecek penempatan yang sama | Menambahkan pengecekan duplikasi di `ManajemenRelawan.tambahPenempatan()` |
| `do-while` di `main` berantakan | Logika dipecah ke method terpisah (`bacaPilihan`, `prosesMenu`, `tambahRelawan`, `tambahBencana`, `tambahPenempatan`) |
| Nama repository kurang tepat | Menggunakan format `Minpro-3-PBO-ManajemenRelawanBencana` |
| Alur program dari awal sampai akhir | Dijelaskan pada bagian 4 |

---

## 11. Cara Menjalankan dan Identitas

**Cara menjalankan:**
1. Buka project menggunakan NetBeans.
2. Jalankan file `Main.java`.
3. Pilih menu sesuai nomor dan ikuti petunjuk di layar.
4. Pilih menu **9** untuk keluar.

**Teknologi:** Java, Maven, NetBeans, Git, dan GitHub.

**Identitas:**
- Nama: Meilanie
- NIM: 2509116109
- Kelas: C
- Mata Kuliah: Pemrograman Berorientasi Objek
- Mini Project: 3
