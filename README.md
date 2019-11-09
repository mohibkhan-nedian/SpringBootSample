Spring Boot Demo.


* Setup a Spring Boot service that exposes the following endpoint:
```sh
POST /events

Request body example:
{
  "identifier": "testevent",
  "timestamp": 1,
  "products": [
    {
      "identifier": 1,
      "title": "My Test Product 1",
      "quantity": 9
    },
    {
      "identifier": 2,
      "title": "My Test Product 2",
      "quantity": 14
    },
    {
      "identifier": 3,
      "title": "My Test Product 3",
      "quantity": 71
    },
    {
      "identifier": 4,
      "title": "My Test Product 4",
      "quantity": 56
    }
  ],
  "paymentDetails": {
    "type": "CARD",
    "provider": "VISA",
    "amount": 99.99,
    "currency": "GBP"
  }
}
```
* For each product make the following restful call - **concurrently** using `Future` and `AsyncResult` - and produce a map of `productId->inStock`
```sh
GET http://mytestservice/products/$productId

Response body example:
{"identifier": 0, "inStock": false}
```
* Return this on the endpoint exposed in the service
* Using integration tests and mocking, test that the resulting map produces the following result:
```
1 -> true
2 -> true
3 -> false
4 -> false
```
* Submit this for review in GitLab