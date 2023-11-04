# ENOCA CASE PROJESI

## Kullanılan Teknolojiler
- Java
- Spring Boot
- Postman

## Proje Oluşturma
İntellij İdea üzerinden Spring Initializr ile Spring Web, Spring Data JPA ve Mysql Driver Dependcyleri eklendi.

## API End Pointleri
- GET /api/directors: Tüm yönetmenleri getir.
- POST /api/directors: Yeni bir yönetmen oluştur.
- GET /api/directors/{id}: ID'ye göre bir yönetmen getir.
- PUT /api/directors/{id}: ID'ye göre bir yönetmeni güncelle.
- DELETE /api/directors/{id}: ID'ye göre bir yönetmeni sil.

- GET /api/films: Tüm filmleri getir.
- POST /api/films: Yeni bir film oluştur.
- GET /api/films/{id}: ID'ye göre bir film getir.
- PUT /api/films/{id}: ID'ye göre bir filmi güncelle.
- DELETE /api/films/{id}: ID'ye göre bir filmi sil.

Ve hataya uygun geri dönüşlerde bulunur. POSTMAN gibi bir request uygulamasından request'te bulunmadan önce 
- Requestin Headers'ında Key kısmına: Content-Type Value kısmına: application/json girilmeli
- ardından örneğin directors için POST metotda "name": "Lars Von Trier" tarzı bir request ile ekleme yapılabilir.
- DELETE metodu içinse sadece id girilmesi yeterlidir
- Film eklemek için ise POST metotda: 
- {
  "name": "Film Adı",
  "director": {
  "id": 1
  }
  } şeklinde request yapılmalıdır.

## Uygulamanın ayağa kaldırılması
Uygulamayı kullanmak için src/main/java/Enocadeneme1Application class'ı çalıştırılır ve server açılır. Ardından Postman ile request atılır.

## Veritabanı
application.properties dosyasını kullanılarak veritabanı bağlantısı oluşturulur, gerekli kodlar:
```
spring.datasource.url=jdbc:mysql://localhost:3306/film_director
spring.datasource.username=root
spring.datasource.password=Mysql1453.
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
PostgreSQL için gerekli bağımlılıklar pom.xml dosyasına eklenir:
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>
```
Ve veritabanı ayağı kaldırılır.