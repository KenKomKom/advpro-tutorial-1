## EXERCISE 1

1. Menurut saya, prinsip clean code yang telah saya laksanakan adalah sebagai berikut :

- Nama variabel yang jelas

  Nama variabel yang saya berikan tidak berupa singkatan dan jelas tujuan dari pembuatan variabel tersebut apa pada kode yang saya buat
- Nama function yang baik

  Nama function yang saya buat juga menjelaskan tujuan dari function tersebut. Function-fuctionnya pun tidak mengubah variabel global dan hanya berfokus pada 1 kegunaan. Tiap fungsi juga hanya berupa command maupun query, tidak keduanya.
- Karena jelasnya nama variabel, function dan jelasnya alur kode, tidak diperlukannya comment. "Don't comment bad code, rewrite it."
- Alur pembuatan aplikasi dilaksanakan dengan "feature branch workflow" yang lebih aman dibandingkan trunking.
- No null returned

2. Hal yang bisa dikembangkan adalah :

- Pada saat ini, tiap orang bisa mengubah setiap product yang terdapat pada aplikasi. Selain itu, mereka bisa men-delete product hanya dengan brute force link yang dapat menyebabkan terdeletenya seluruh database. Kedepannya diperlukan sistem autentikasi dan autorisasi sehingga yang bisa mendelete/mengubah product hanyalah mereka yang mendaftarkannya.

## EXERCISE2
1. Jumlah kode testing yang kita buat harus bisa memverifikasi kasus saat kode berjalan sesuai alurnya, saat kode diekspektasi untuk error dan ketika terdapat edge case dalam program. Selain itu, jumlah pengujian yang diperlukan untuk suatu kode disarankan mencakup 80% dari kodenya(Atlassian).

   Tapi perlu diingat kalau code coverage sendiri tidak menjelaskan apakah kode yang kita telah buat bisa menindak lanjuti perilaku yang diluar dugaan. Misalkan saja ada kode yang menerima input integer dan kita sudah membuat pengujian yang membuktikan kode tersebut menerimanya. Namun, apa yang terjadi jika angka yang diberikan lebih besar dari angka integer. Maka kode kita tidak akan berjalan sesuai ekspektasi. Code coverage juga bisa diselewengkan dengan membuat tes-tes yang tidak berhubungan dengan kode dengan tujuan untuk menaikan performa code coverage.


2. Menurut saya kebersihan kode nya akan berkurang karena artinya functionnya mengerjakan lebih dari satu hal dimana hal tersebut sebaiknya dihindari dengan cara memperkecil function, seperti memisahkan bagian instansiasi produk ke menjadi functionnya sendiri dan test function hanya berfungsi untuk mengecek kebenaran kode.