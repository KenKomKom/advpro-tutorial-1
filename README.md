## REFLECTION1

1. Menurut saya, prinsip clean code yang telah saya laksanakan adalah sebagai berikut :

- Meaningful names

  Variabel yang dibuat memiliki nama jelas. Nama variabel yang saya berikan tidak berupa singkatan dan jelas tujuan dari pembuatan variabel tersebut apa pada kode yang saya buat
- Meaningful function name

  Nama function yang saya buat juga menjelaskan tujuan dari function tersebut.
- Function do one thing
  
  Function hanya berfokus pada 1 kegunaan.
- Function have no side effects

  Function yang dibuat, dibuat dengan cara yang menghindari penggunaan global variabel.
- Function does not return null
  
    Function tidak meng return null
- Comment don't make up for bad code

  Karena jelasnya nama variabel, function dan jelasnya alur kode, tidak diperlukannya comment. "Don't comment bad code, rewrite it."
- Alur pembuatan aplikasi dilaksanakan dengan "feature branch workflow" yang lebih aman dibandingkan trunking.

2. Hal yang bisa dikembangkan adalah :

- Pada saat ini, tiap orang bisa mengubah setiap product yang terdapat pada aplikasi. Selain itu, mereka bisa men-delete product hanya dengan brute force link yang dapat menyebabkan terdeletenya seluruh database. Kedepannya diperlukan sistem autentikasi dan autorisasi sehingga yang bisa mendelete/mengubah product hanyalah mereka yang mendaftarkannya.

## REFLECTION2
1.
- Setelah menuliskan kode testing untuk program saya, saya menjadi lebih yakin terhadap kebenaran kode saya.
- Menurutku tidak ada jumlah atau batasan wajib terhadap jumlah testing dalam suatu class selama masih sesuai dalam definisi class tersebut seperti misalkan class khusus functional test tidak seharusnya berisikan unit test. 
- Jumlah kode testing yang kita buat harus bisa memverifikasi kasus saat kode berjalan sesuai alurnya, saat kode diekspektasi untuk error dan ketika terdapat edge case dalam program. Selain itu, cara untuk mengetahui apakah kode tes kita sudah memverifikasi program kita dengan baik adalah dengan melihat code coverage kita. Jumlah pengujian yang diperlukan untuk suatu kode disarankan mencakup 80% dari kodenya(Atlassian). Semakin tinggi coverage nya semakin baik biasanya.
- Perlu diingat kalau code coverage sendiri tidak menjelaskan apakah kode yang kita telah buat bisa menindak lanjuti perilaku yang diluar dugaan. Misalkan saja ada kode yang menerima input integer dan kita sudah membuat pengujian yang membuktikan kode tersebut menerimanya. Namun, apa yang terjadi jika angka yang diberikan lebih besar dari angka integer. Maka kode kita tidak akan berjalan sesuai ekspektasi. Code coverage juga bisa diselewengkan dengan membuat tes-tes yang tidak berhubungan dengan kode dengan tujuan untuk menaikan performa code coverage, seperti mengecek apakah 1==1. Oleh karena itu, code coverage 100% bukan berarti code kita bebas dari error.


3. Menurut saya kebersihan kode nya akan berkurang karena artinya terjadi pengulangan kode pada program kita. Dimana salah satu paradigma pemrograman adalah don't repeat yourself. Selain itu, kedua function tersebut artinya melakukan lebih dari 1 hal dan kemungkinan functionnya dapat disederhanakan dengan memisahkan bagian instansiasi produk ke menjadi functionnya sendiri dan test function hanya berfungsi untuk mengecek kebenaran kode, memenuhi paradigma sehingga 1 fungsi 1 tujuan.

## REFLECTION2

1. Masalah kualitas kode yang saya bersihkan pada tutorial ini adalah sebagai berikut :
- Berdasarkan PMD:
  - Menghilangkan sebagian import yang tidak diperlukan
  - Mengimport hanya method yang diperlukan
  - Membuat blok if else berada didalam kurung kurawal untuk konsistensi
  - Menghilangkan access modifier public pada interface karena pada java sudah otomatis
  - Menghilangkan deklarasi variabel yang tidak digunakan.
  - Menghilangkan kurung yang tidak berguna agar kode bersih
  - Penyesuaian nama fungsi berdasarkan standar Java
  - Penyesuaian nama package agar tidak mengandung kapital
2. Menurut saya, dengan diimplementasikannya CI/CD workflow pada aplikasi ini sudah lumayan sesuai dengan definisi dari CI/CD workflows. Setiap kali ada push ke repository github aplikasi akan selalu di test terlebih dahulu oleh unit-test dan kemudian di cek oleh PMD dan ScoreCard akan kebersihan kodenya. Dengan demikian aplikasi ini akan selalu teruji implementasinya setiap ada push ke branch. Untuk Continuous deployment. Saat contributor github merasa sudah cukup baik dan merge ke main. Maka aplikasi akan terdeploy secara otomatis oleh Koyeb dan bisa diakses via internet.