# TaskMahasiswa Offline

Aplikasi Android untuk membantu mahasiswa mengelola tugas kuliah secara offline.

## Identitas Mahasiswa

- **Nama:** Khoirul Ikwan
- **NIM:** 2411020
- **Program Studi:** Informatika
- **Universitas:** Universitas Mulia
- **Kelas:** IFB5J

## Deskripsi Aplikasi

TaskMahasiswa Offline merupakan aplikasi manajemen tugas mahasiswa yang dirancang menggunakan Kotlin dan Jetpack Compose. Aplikasi menggunakan database lokal Room agar data tugas tetap tersimpan meskipun aplikasi ditutup dan dibuka kembali.

## Teknologi yang Digunakan

- Kotlin
- Jetpack Compose
- ViewModel
- Navigation
- Room Database
- Repository Pattern

## Fitur Utama

- Menampilkan daftar tugas mahasiswa.
- Menambahkan tugas baru.
- Melihat detail tugas.
- Mengedit informasi tugas.
- Menghapus tugas.
- Mengubah status tugas.
- Menyimpan data secara lokal agar dapat digunakan tanpa koneksi internet.

## Struktur Arsitektur

Alur pengelolaan data aplikasi:

UI → ViewModel → Repository → DAO → Room Database

Pemisahan ini membantu menjaga agar tampilan aplikasi tidak mengakses database secara langsung.

## Cara Menjalankan Aplikasi

1. Buka proyek menggunakan Android Studio.
2. Tunggu proses Gradle Sync selesai.
3. Jalankan aplikasi menggunakan emulator atau perangkat Android.
4. Gunakan fitur aplikasi untuk mengelola tugas kuliah.

## Repository

https://github.com/khoirulikwan3005/TaskMahasiswa