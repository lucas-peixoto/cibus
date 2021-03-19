set SPRING_DATASOURCE_USERNAME=root
set SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/cibus?createDatabaseIfNotExist=true
set SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQL55Dialect
mvnw spring-boot:run