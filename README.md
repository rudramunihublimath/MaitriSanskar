1) Swagger URL :
   http://localhost:8081/swagger-ui/index.html#/

2) Table :
   select * from mbp.users;
   select * from mbp.token;
   select * from mbp.country_master;
   select * from mbp.states_master;
   select * from mbp.cities_master;

3) Sample JSON for Registration : added in folder  
4) Below URL need JWT token to login 

http://localhost:8081/Secured/MBP/ChangePassword
http://localhost:8081/Secured/MBP/demo-controller

5) Static data inserted in tables :

Insert Into COUNTRY_MASTER Values(1,'+91','India');
Insert Into STATES_MASTER Values(1,1,'Punjab');
Insert Into STATES_MASTER Values(2,1,'Karnataka');
Insert Into STATES_MASTER Values(3,1,'Maharashtra');
Insert Into CITIES_MASTER Values(1,'Ludhiana',1);
Insert Into CITIES_MASTER Values(2,'Amritsar',1);
Insert Into CITIES_MASTER Values(3,'Banglore',2);
Insert Into CITIES_MASTER Values(4,'Mysore',2);
Insert Into CITIES_MASTER Values(5,'Mumbai',3);
Insert Into CITIES_MASTER Values(6,'Pune',3);
commit;

Help :
.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
.requestMatchers(HttpMethod.GET, "/api/users/login", "/api/users/{username}", "/api/users/logout", "/api/customers", "/api/storages").authenticated()
.requestMatchers(HttpMethod.POST, "/api/customers", "/api/storages").authenticated()
.requestMatchers(HttpMethod.PUT, "/api/customers/{id}", "/api/storages/{id}").authenticated()
.requestMatchers(HttpMethod.DELETE, "/api/users/{id}", "/api/storages/{id}", "/api/customers/{id}").authenticated()

---
