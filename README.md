1) Swagger URL :
   http://localhost:8081/swagger-ui/index.html#/
2) Table :
   SELECT * FROM mbp.users;   
3) Sample JSON for Registration : added in folder  
4) Below URL need JWT token to login 

http://localhost:8081/Secured/MBP/ChangePassword
http://localhost:8081/Secured/MBP/demo-controller

Help :
.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
.requestMatchers(HttpMethod.GET, "/api/users/login", "/api/users/{username}", "/api/users/logout", "/api/customers", "/api/storages").authenticated()
.requestMatchers(HttpMethod.POST, "/api/customers", "/api/storages").authenticated()
.requestMatchers(HttpMethod.PUT, "/api/customers/{id}", "/api/storages/{id}").authenticated()
.requestMatchers(HttpMethod.DELETE, "/api/users/{id}", "/api/storages/{id}", "/api/customers/{id}").authenticated()