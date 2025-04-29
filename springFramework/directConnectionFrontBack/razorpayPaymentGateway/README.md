Project razorPayPaymentGateway
1. Generate Project Setup from https://start.spring.io/
2. Java Version – JDK-17
3. Build Tool - Maven
4. Spring Boot Version - 3.4.5
5. Packaging - JAR
   6. Dependency added -
       1. spring-boot-starter-web
       2. razorpay-java  reference - https://mvnrepository.com/artifact/com.razorpay/razorpay-java/1.4.8
       3. lombok
       4. spring-boot-starter-data-jpa 
          5. mysql-connector-j
             7. Package and classes-
                 1. controller
                     1. PaymentController.java (class)
                     2. UserController.java (class)
                 2. service
                     1. PaymentService.java (class)
                     2. UserService.java (class)
                 3. model
                     1. User.java (class)
                     2. Payments.java (class)
                 4. repository
                     1. PaymentsRepository.java (interface)
                     2. UserRepository.java (interface)

                 Application.properties - Properties to configure RazorPay, MySql database using Hibernate
                 Reference - https://github.com/netgloo/spring-boot-samples/blob/master/spring-boot-mysql-springdatajpa-hibernate/src/main/resources/application.properties

                 Reference 1 - https://www.youtube.com/watch?v=AVWT23zKWaE
   				 Reference 2 - https://www.youtube.com/watch?si=b2mq1Vv0Nh6OS8q-&v=Qph08VLmPhc&feature=youtu.be
   				 Reference 3 - https://razorpay.com/docs/payments/server-integration/java/integration-steps/
