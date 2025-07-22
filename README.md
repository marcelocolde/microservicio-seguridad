Este repositorio está compuesto por múltiples microservicios que se comunican entre sí. Cada servicio se encuentra registrado en un servidor Eureka, 
y la comunicación se realiza a través de un API Gateway central.

El sistema además implementa Spring Security con OAuth2 para la gestión de autenticación y autorización, asegurando el acceso a los recursos de manera segura.

Además, el sistema incluye una aplicación de gestión de usuarios, compuesta por:
  ms-users-app: microservicio backend de usuarios (Spring Boot).
  angular/user-app: aplicación frontend desarrollada en Angular.
