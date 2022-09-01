
# Blog-API
In this project when people want to open a blog, it is intended to be used only by preparing an interface for this service.

## Using Technologies
Spring Boot, Postgresql, JPA and Hiberenate, Swagger 


  
## Let's run it on computer

- Clone the project

```bash
  git clone https://github.com/yasinaysann/blog-api.git
```
- Create Postgresql Database 
```bash
  create database blog-api
```
+ Open `src/main/resources/application.properties`
- Change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

Run project a use maven
```bash
  mvn spring-boot:run
```

Start running http://localhost:8080

- Accessing Swagger API Documentation ```http://localhost:8080/swagger-ui.html```
  
