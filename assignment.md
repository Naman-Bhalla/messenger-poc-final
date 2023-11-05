For both private and group messages:
1. Check for idempotency
2. store them on the DB as well

Tutorial: https://spring.io/guides/gs/accessing-data-mysql/
- MySQL Database
1. Add dependencies in pom.xml (https://github.com/Naman-Bhalla/productservice/blob/master/pom.xml)
2. Add properties in application.properties
```markdown
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/sep23productservice
spring.datasource.username=sep23productservice
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
#logging.level.org.hibernate.SQL=DEBUG
#logging.level.org.hibernate.type=TRACE
```
