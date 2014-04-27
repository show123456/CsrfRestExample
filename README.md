CsrfRestExample
===============
Example how to secure Spring Boot REST application against csrf without using session.

TokenRepository is implemented as Map and Spring security is instructed to use this TokenRepository
 instead of default HttpSessionCsrfTokenRepository.

GET /info
---------
unauthorized access to info resoucre

GET /login
----------
authorized resource that returns also csrf_token token in headers

PUT /info
---------
authorized access to resource that can be accessed only with correct csrf_token