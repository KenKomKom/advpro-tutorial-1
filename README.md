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

## REFLECTION 3

1. Prinsip yang saya aplikasikan ada,
- Single Responsibility Principle, Saya memisahkan method home page saya dari yang sebelumnya berada pada ProductController ke class HomeController karena memiliki fungsi yang berbeda, selain itu, saya juga menghilangkan extends ProductController pada CarController karena menurut saya kedua class tersebut memiliki fungsi berbeda sehingga CarController seharusnya tidak bisa menggunakan method di ProductController.
- Dependacy Inversion dilakukan pada file CarController dengan cara mengganti tipe data CarServiceImpl menjadi CarService untuk menghindari coupling antara kelas CarController dan juga class CarServiceImpl dan juga agar perubaha yang terjadi di CarServiceImpl tidak merusak CarController.

2. Dengan mengimplementasikan SOLID, kita meningkatkan manageability  code yang kita buat. 
- Kita lebih mudah membuat unit untuk menguji kode misalkan,

  - Kita membuat 1 method panjang yang melakukan banyak hal, saat kita menguji kode tersebut dan terjadi kesalahan, akan sulit menentukan dibagian mana programnya mengalami kesalahan. Sedangkan jika kita pisahkan method tersebut menjadi method-method yang lebih kecil dan menguji method-method tersebut, akan lebih mudah mendiagnosa kesalahan.
  
  - Selain itu jika kita membuat suatu class dengan tingkat dependency yang tinggi ke class lain, akan sulit bagi kita untuk membuat test karena kita harus memikirkan keterhubungan class-class lain tersebut dalam test kita dan apakah perlu untuk kita mock dependency tersebut.
- Meningkatkan keterbacaan kode,

  - Misalkan seperti contoh di atas, kita memiliki sebuah method yang sangat panjang dan melakukan banyak hal. Seseorang yang baru membaca kode tersebut akan kesusahan memahami cara kerja dari method karena method terlalu kompleks, sedangkan jika method dipecah menjadi beberapa method yang lebih kecil, akan lebih jelas bagi pembaca apa tujuan dan cara kerja method tersebut.
  
    Lebih mudah membaca kode pendek daripada yang panjang. 
- Lebih mudah menambah fitur ke dalam kode

    - Seperti yang disebutkan dalam salah satu aturan dalam SOLID, Open Closed Principle menyarankan pembuat kode untuk membuat kode yang bisa  ditambahkan tanpa perlu mengubah kode yang sudah ada. Hasil pengimplementasian ini adalah kode yang kita miliki sekarang jika diperlukan penambahan fitur, hanya perlu dibuat kode untuk fitur baru tersebut karena fitur yang sebelumnya sudah ada sudah dibuat untuk bisa berinteraksi dengan fitur tambahannya.
- Perubahan pada satu bagian kode tidak merusak pengerjaan kode lain. Hal ini didasarkan pada pengimpelmentasian Dependency Inversion dimana kita disarankan untuk menggunakan interface dibanding concrete implemantaion dari interface tersebut. Mengapa? Karena jika kita menggunakan tipe data interface, maka artinya variabel tersebut harus mengimplementasikan fungsi yang kita perlukan secara benar header methodnya. 

  - Sebagai contoh dari pengaplikasian ini adalah, 
  misalkan kita memiliki class yang menggunakan object A sebagai tipe data B. Jika fungsi yang kita gunakan di class tersebut yang ada di A diubah, Ia tetap harus sesuai dengan interface agar tidak error sehingga implementasi kode pada class kita tetap berjalan dengan baik.
  
  Sedangkan jika misal kita menggunakan A dengan concrete implementation dari B, maka A bisa dengan bebas mengganti fungsi yang kita perlukan atau mungkin saja, berganti interface.
3. Kerugian dari tidak mengimplementasikan SOLID adalah sebagai berikut,
- Penambahan pada kode akan sulit dilakukan.
  - Hal ini sama seperti lawan dari yang disebutkan diatas, jika kode tidak mengimplementasikan prinsip open close, maka bisa saja untuk tiap penambahan kode yang ingin kita lakukan, kita perlu melakukan banyak perubahan terhadap kode dulu kita.
- Kode akan sulit untuk dibaca
  - Seperti yang dijelaskan pada nomor 2, method yang melakukan terlalu banyak hal akan jauh lebih susah untuk di debug dan dibaca tujuannya apa.
- Kode akan sulit untuk di uji
  - Saat kita ingin menguji suatu method, jika method yang kita buat memiliki dependancy yang tinggi terhadap codebase lain, atau misal sangat panjang. Akan diperlukan usaha lebih untuk membuat unit testing yang bisa menguji kodenya karena diperlukan misal, mocking dari variabel yang Ia butuhkan.
- Kode akan sulit untuk diubah
  - Seperti yang dijelaksan di nomor 2, jika kode tidak dibuat dengan mengimplementasikan paradigma open close principle, dan dependacy injection, maka akan sangat sulit untuk kita melakukan perubahan karena bisa saja modul yang kita ganti diperlukan di kode lain, dan perubahan kita menyebabkan kerusakan pada kode tersebut. Atau misal method yang kita buat hanya dipersiapkan untuk menerima tipe data A, dan ketika kita perlu menggunakannya untuk tipe data B, program perlu kita bongkar ulang implementasinya.
