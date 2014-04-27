CsrfRestExample
===============

Example how to secure Spring Boot REST application against csrf without using session.

TokenRepository is implemented as Map and Spring security is instructed to use this TokenRepository
 instead of default HttpSessionCsrfTokenRepository.
