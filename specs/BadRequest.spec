Bad Request Tests
=================

GET request to bad_request returns 400 Bad Request
--------------------------------------------------

* GET request to "bad_request" endpoint
* Assert the http response code is "400"
* Assert the http response text is "Bad Request"

POST request to bad_request returns 400 Bad Request
--------------------------------------------------

* POST request to "bad_request" endpoint with body "test1234"
* Assert the http response code is "400"
* Assert the http response text is "Bad Request"
* Assert the http response body contains the posted value