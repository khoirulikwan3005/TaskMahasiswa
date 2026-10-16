# Jawaban Pertanyaan Demo TaskMahasiswa Offline

## 1. Mengapa menggunakan Room Database?

Room digunakan untuk menyimpan data tugas secara lokal di perangkat. Dengan demikian, data tetap tersedia meskipun aplikasi ditutup atau perangkat tidak terhubung ke internet.

## 2. Di mana UI State dikelola?

UI State dikelola melalui ViewModel. ViewModel menyediakan data yang dibutuhkan tampilan dan membantu mempertahankan state ketika terjadi perubahan konfigurasi tertentu.

## 3. Mengapa UI tidak mengakses database secara langsung?

Agar tanggung jawab setiap bagian aplikasi terpisah dengan jelas. UI bertugas menampilkan informasi, sedangkan ViewModel, Repository, dan DAO menangani alur pengelolaan data. Struktur ini membuat kode lebih terorganisasi dan mudah dipelihara.

## 4. Bagaimana aplikasi merespons perubahan data?

Perubahan data diproses melalui ViewModel, Repository, dan DAO. Room menyediakan data terbaru, kemudian perubahan state yang diamati oleh Compose dapat memicu recomposition sehingga tampilan diperbarui.

## 5. Commit mana yang menunjukkan fitur utama selesai?

Riwayat Git mencatat perubahan proyek secara bertahap. Commit awal menyimpan struktur proyek dan kode aplikasi, sedangkan commit berikutnya menambahkan dokumentasi. Untuk menunjukkan fitur utama, periksa riwayat commit yang benar-benar berisi implementasi fitur dan gunakan hash serta pesan commit yang sesuai dengan perubahan tersebut.