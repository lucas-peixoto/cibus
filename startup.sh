export SPRING_DATASOURCE_USERNAME=root
export SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/cibus?createDatabaseIfNotExist=true
export SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.MySQL55Dialect
./mvnw spring-boot:run