# Aplikasi Manajemen Resep

Aplikasi ini dirancang untuk memudahkan pengguna dalam mengelola koleksi resep mereka. Pengguna dapat menambah, memperbarui, dan menghapus resep dengan antarmuka pengguna grafis yang sederhana dan intuitif.

## Fitur Utama
- **Menambahkan Resep**: Pengguna dapat menambahkan resep baru dengan memasukkan nama, bahan-bahan, dan langkah-langkah memasak.
- **Memperbarui Resep**: Pengguna dapat memperbarui resep yang sudah ada dengan mengedit detailnya.
- **Menghapus Resep**: Resep yang tidak diperlukan bisa dihapus dari daftar resep.
- **Lihat Resep**: Pengguna dapat melihat daftar semua resep yang telah disimpan.
- **Validasi Input**: Formulir untuk menambahkan atau memperbarui resep akan memvalidasi bahwa semua kolom terisi dengan benar sebelum menyimpan data.

## Teknologi yang Digunakan
- **Java**: Digunakan sebagai bahasa pemrograman utama untuk aplikasi ini.
- **Swing**: Digunakan untuk membuat antarmuka grafis pengguna (GUI).
- **JUnit & Mockito**: Digunakan untuk pengujian unit dan mock objek dalam pengujian.
- **File I/O**: Menggunakan file sistem untuk menyimpan dan membaca data resep.

## Instalasi dan Penggunaan

### Persyaratan
Sebelum menjalankan aplikasi, pastikan Anda telah menginstal:
- **Java Development Kit (JDK)** versi 8 atau lebih tinggi.
- **IDE** (Integrated Development Environment) seperti IntelliJ IDEA, Eclipse, atau NetBeans untuk mempermudah pengembangan.
- **Maven** atau **Gradle** (opsional), jika Anda ingin menggunakan build tool.

### Langkah-langkah Instalasi

#### 1. **Clone atau Download Repository**
Clone repository ini ke dalam mesin lokal Anda atau unduh sebagai file ZIP:
```bash
git clone https://github.com/username/repo-name.git
```
Atau unduh ZIP dan ekstrak ke direktori pilihan Anda.

#### 2. **Mengimpor ke IDE**
Jika menggunakan IDE seperti IntelliJ IDEA, Eclipse, atau NetBeans:
- Buka IDE Anda.
- Pilih **Import Project** (IntelliJ: File -> Open, Eclipse: File -> Import, NetBeans: File -> Open Project).
- Pilih folder tempat Anda mengunduh atau meng-clone repository.
- IDE akan mengonfigurasi proyek dan mengunduh dependensi yang diperlukan.

#### 3. **Menyusun dan Menjalankan Aplikasi**
- Jika menggunakan **IDE**, pilih kelas utama (`RecipeApp.java`) dan jalankan aplikasi seperti biasa.
- Jika menggunakan **Maven** atau **Gradle**, Anda bisa menjalankan aplikasi dengan perintah berikut:

  **Maven**:
  ```bash
  mvn clean install
  mvn exec:java -Dexec.mainClass="org.example.RecipeApp"
  ```

  **Gradle**:
  ```bash
  gradle build
  gradle run
  ```

#### 4. **Menjalankan Aplikasi**
Setelah aplikasi dijalankan, Anda akan melihat antarmuka grafis pengguna (GUI) yang memungkinkan Anda untuk:
- **Menambahkan resep**: Isi nama resep, bahan-bahan, dan langkah-langkahnya, lalu simpan.
- **Memperbarui resep**: Pilih resep yang ingin diperbarui, ubah detailnya, dan simpan.
- **Lihat resep**: Daftar resep yang sudah disimpan dapat dilihat di panel utama.
- **Hapus resep**: Resep yang tidak diinginkan bisa dihapus dengan memilih resep tersebut dan mengklik tombol "Hapus".

### Pengujian
Aplikasi ini sudah dilengkapi dengan pengujian unit menggunakan **JUnit** dan **Mockito** untuk memastikan bahwa aplikasi berjalan dengan benar. Untuk menjalankan pengujian, Anda bisa menggunakan perintah berikut tergantung pada build tool yang digunakan.

- **Jika menggunakan Maven**:
  ```bash
  mvn test
  ```

- **Jika menggunakan Gradle**:
  ```bash
  gradle test
  ```

- **Jika menggunakan IDE**, pilih opsi **Run Tests** atau klik kanan pada file pengujian dan pilih **Run**.

### Struktur Proyek
```bash
src/
├── main/
│   ├── gui/
│   │   ├── AddRecipePanel.java         # Panel untuk menambahkan resep baru
│   │   ├── MainMenuPanel.java         # Panel menu utama aplikasi
│   │   ├── RecipeDetailPanel.java     # Panel untuk menampilkan detail resep
│   │   ├── UpdateRecipePanel.java     # Panel untuk memperbarui resep
│   │   └── ViewRecipesPanel.java      # Panel untuk melihat daftar resep
│   ├── model/
│   │   └── Recipe.java                # Kelas model untuk data resep
│   ├── RecipeApp.java                 # Kelas utama untuk menjalankan aplikasi
└── util/
    └── FileHandler.java               # Kelas untuk menangani operasi file (seperti membaca dan menyimpan data resep)
```

### Contoh Penggunaan
Setelah aplikasi dijalankan, Anda dapat:
1. Menambahkan resep dengan mengisi nama resep, bahan-bahan, dan langkah-langkah memasak.
2. Mengedit resep yang sudah ada dengan memilihnya dari daftar dan mengubah detailnya.
3. Melihat daftar semua resep yang disimpan.
4. Menghapus resep yang tidak diperlukan.

**Validasi Input**: Aplikasi akan menampilkan pesan kesalahan jika Anda mencoba untuk menyimpan resep tanpa mengisi semua kolom yang diperlukan.

### Masalah yang Diketahui
- Pastikan **JDK** sudah terinstal dengan benar di sistem Anda.
- Jika aplikasi tidak muncul atau tidak dapat dijalankan, pastikan untuk memeriksa log kesalahan di konsol untuk detail lebih lanjut.
