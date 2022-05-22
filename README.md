# SpringBoot-Commenting-System
This is  comments system by using SpringBoot & Ajax & Hibernate
<h2>Install && Run</h2>


<h3>Run</h3>

Config your mysql/h2 in application.properties
```
spring.datasource.url = your mysql url
spring.datasource.username = your mysql user
spring.datasource.password = your mysql password
```

Run with maven

cd server
mvn spring-boot:run
```

1.Go to client folder and open index.html-->Then select browser option in browser list from the right middle of the code.
2.Once opened the html in browser enter Name and Content then click on POST COMMENT.
3.You can see the logs in console to check Sending and Receiving data via messaging Queue.
4.And you can see the comments in UI.
5.And please check added postman collection to retrieve data.
6.Once you run Async-Service project then you can see Async services are running.
7.You can use H2/SQL Db your side just need to enable dependency and changes in app.properties file.
8.MVN CLEAN TEST for to run Test Cases.
9.java 8 is fine in your JDK.
