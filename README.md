# AMEX API 
This project encapsulates an API Service used to create, edit, view, and delete Orders for a fictitious company. 
Below are the list of additional models, services, used to encapsulate and process orders.


# Services:

## OrderService
The order service includes methods to create, edit, view and delete customer orders. **Orders** include **Associate**, **Customer**, and a list of **OrderItem** objects.

### Paths
###### Controller Path
`src/main/java/com/amex/api/controller/OrderController.java`
###### Service Interface/Impl Paths
```
src/main/java/com/amex/api/service/OrderService.java 
src/main/java/com/amex/api/service/OrderServiceImpl.java
```
###### Data Models:
```
src/main/java/com/amex/api/data/Order.java
src/main/java/com/amex/api/data/OrderItem.java
src/main/java/com/amex/api/data/OrderItemPk.java
src/main/java/com/amex/api/data/Associate.java
src/main/java/com/amex/api/data/Customer.java
```

### Tests
```
src/test/java/com/amex/api/service/OrderServiceTests.java
src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java
```

### Endpoints

`GET /api/orders`

###### Description: Returns a collection of known orders.

```json
{
  "data": {
    "id": 11,
    "customer": {
      "id": 1,
      "email": "sophie.bon-customer@gmail.com",
      "firstName": "Sophie",
      "lastName": "Bon-Customer",
      "cellphone": "917-555-5555",
      "dateCreated": "2022-12-04 16:49:10",
      "dateModified": "2022-12-04 16:49:10"
    },
    "associate": {
      "id": 1,
      "email": "edgar.bon-associate@amex.com",
      "storeId": "12345",
      "firstName": "Edgar",
      "lastName": "Bon-Associate",
      "cellphone": "917-222-2222",
      "hireDate": "2022-12-04 16:49:10"
    },
    "orderItems": [{
      "quantity": 8,
      "totalProductDiscountPrice": "4.00",
      "totalProductNetPrice": "8.00",
      "product": {
        "id": 1,
        "name": "Apple",
        "description": "A delicious red apple from the apple orchards at Morales Farms",
        "price": "1.00",
        "dateCreated": "2022-12-04 16:49:10",
        "dateModified": "2022-12-04 16:49:10"
      }
    },
      {
        "quantity": 8,
        "totalProductDiscountPrice": "6.00",
        "totalProductNetPrice": "8.00",
        "product": {
          "id": 2,
          "name": "Orange",
          "description": "A plump, juicy orange from the orange groves at Morales Farms",
          "price": "1.00",
          "dateCreated": "2022-12-04 16:49:10",
          "dateModified": "2022-12-04 16:49:10"
        }
      }
    ],
    "dateCreated": "2022-12-04 16:54:39",
    "dateModified": "2022-12-04 16:54:39",
    "totalOrderNetPrice": "16.00",
    "totalOrderDiscountPrice": "10.00"
  },
  "message": "Successfully created order.",
  "status": 201
}
```
#
`POST /api/order`
###### Description: Creates an order
```
curl -i \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-X POST --data '{"customer_id" :1, "associate_id": 1, "order_items": [{"quantity": 10, "product_id": 1 }]}' \
http://localhost:8080/api/order
```
###### Response
```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 01 Dec 2022 04:33:00 GMT

{"id":11,"customer":{"id":1,"email":"sophie.bon-customer@gmail.com","firstName":"Sophie","lastName":"Bon-Customer","cellphone":"917-555-5555","dateCreated":"2022-12-01 04:25:11","dateModified":"2022-12-01 04:25:11"},"associate":{"id":1,"email":"edgar.bon-associate@amex.com","storeId":"12345","firstName":"Edgar","lastName":"Bon-Associate","cellphone":"917-222-2222","hireDate":"2022-12-01 04:25:11"},"orderItems":[{"quantity":10,"totalProductNetPrice":"10.00","product":{"id":1,"name":"Apple","description":"A delicious red apple from the apple orchards at Morales Farms","price":"1.00","dateCreated":"2022-12-01 04:25:11","dateModified":"2022-12-01 04:25:11"},"totalProductDiscountPrice":"5.00"}],"dateCreated":"2022-12-01 04:33:00","dateModified":"2022-12-01 04:33:00","totalOrderNetPrice":"10.00"}
```
#
`GET /api/order/{id}`
###### Description: Returns a specific order
```
curl -i \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
http://localhost:8080/api/order/1
```
###### Response:
```
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 01 Dec 2022 13:50:27 GMT

{"id":1,"customer":{"id":1,"email":"sophie.bon-customer@gmail.com","firstName":"Sophie","lastName":"Bon-Customer","cellphone":"917-555-5555","dateCreated":"2022-12-01 13:49:58","dateModified":"2022-12-01 13:49:58"},"associate":{"id":1,"email":"edgar.bon-associate@amex.com","storeId":"12345","firstName":"Edgar","lastName":"Bon-Associate","cellphone":"917-222-2222","hireDate":"2022-12-01 13:49:58"},"orderItems":[],"dateCreated":"2022-12-01 13:49:58","dateModified":"2022-12-01 13:50:26","totalOrderNetPrice":"0"}
```
#
`PUT /api/order/{id}`
###### Description: Updates a specific order
```
curl -i \
-H "Accept: application/json" \
-H "Content-Type: application/json" \
-X PUT --data '{"customer_id" :1, "associate_id": 1, "order_items": [{"quantity": 102, "product_id": 1 }]}' \
http://localhost:8080/api/order/1
```
###### Response:
```
http://localhost:8080/api/order/1
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 04 Dec 2022 16:13:16 GMT

{"data":{"id":1,"customer":{"id":1,"email":"sophie.bon-customer@gmail.com","firstName":"Sophie","lastName":"Bon-Customer","cellphone":"917-555-5555","dateCreated":"2022-12-04 16:11:26","dateModified":"2022-12-04 16:11:26"},"associate":{"id":1,"email":"edgar.bon-associate@amex.com","storeId":"12345","firstName":"Edgar","lastName":"Bon-Associate","cellphone":"917-222-2222","hireDate":"2022-12-04 16:11:26"},"orderItems":[{"quantity":102,"totalProductNetPrice":"102.00","product":{"id":1,"name":"Apple","description":"A delicious red apple from the apple orchards at Morales Farms","price":"1.00","dateCreated":"2022-12-04 16:11:26","dateModified":"2022-12-04 16:11:26"},"totalProductDiscountPrice":"51.00"}],"dateCreated":"2022-12-04 16:11:26","dateModified":"2022-12-04 16:13:16","totalOrderNetPrice":"102.00"},"message":"Successfully updated order.","status":200}
```
#
`DELETE /api/order/{id}`
###### Description: Deletes a specific order
```
# add details
```
###
## AssociateService
The Associate Service include methods to create, edit, view, and delete company associates (who faciliates an order submission).

### Paths:
###### Controller Path
`src/main/java/com/amex/api/controller/AssociateController.java`

###### Service Interface/Impl Paths
```
src/main/java/com/amex/api/service/AssociateService.java
src/main/java/com/amex/api/service/AssociateServiceImpl.java
```
###### Data Models Path:
`src/main/java/com/amex/api/data/Associate.java`

### Tests:
```
src/test/java/com/amex/api/service/AssociateServiceTests.java
src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java
```

### Endpoints:
`GET /api/associates`
###### Description: Returns all known Associates
```json
[
  {
    "id": 1,
    "email": "edgar.bon-associate@amex.com",
    "storeId": "12345",
    "firstName": "Edgar",
    "lastName": "Bon-Associate",
    "cellphone": "917-222-2222",
    "hireDate": "2022-11-29 08:44:36"
  }
]
```
###
`POST /api/associate`
###### Description: Create a new Associate
```
 # add details
```
###
`GET /api/associate/{id}`
###### Description: Retrieve a specific Associate
```
 # add details
```
###
`DELETE /api/associate/{id}`
###### Description: Delete a specific Associate
```
 # add details
```
##
## CustomerService
The Customer Service include methods to create, edit, view, and delete customers.

### Paths:
###### Controller Path
`src/main/java/com/amex/api/controller/CustomerController.java`
###### Service Interface/Impl Paths
`src/main/java/com/amex/api/service/CustomerService.java`
`src/main/java/com/amex/api/service/CustomerServiceImpl.java`
###### Data Models Path:
`src/main/java/com/amex/api/data/Customer.java`

### Tests:
`src/test/java/com/amex/api/service/CustomerServiceTests.java`
`src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java`


### Endpoint:

`GET /api/customers`
###### Description: Returns all known Customers
```json
[
  {
    "id": 1,
    "email": "sophie.bon-customer@gmail.com",
    "firstName": "Sophie",
    "lastName": "Bon-Customer",
    "cellphone": "917-555-5555",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  },
  {
    "id": 2,
    "email": "issy.bon-customer@gmail.com",
    "firstName": "Isabella",
    "lastName": "Bon-Customer",
    "cellphone": "917-777-7777",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  },
  {
    "id": 3,
    "email": "sabrina.bon-customer@gmail.com",
    "firstName": "Sabrina",
    "lastName": "Bon-Customer",
    "cellphone": "917-888-8888",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  }
]
```
###
`POST /api/customer`
###### Description: Create a new Customer
```
 # add details
```
###
`GET /api/customer/{id}`
###### Description: Retrieves a specific Customer
```
 # add details
```
###
`DELETE /api/customer/{id}`
###### Description: Deletes a specific Customer
```
 # add details
```

#
## ProductService
The product service includes methods to create, edit, view and delete products.

### Paths:
###### Controller Path
`src/main/java/com/amex/api/controller/ProductController.java`
###### Service Interface/Impl Paths
`src/main/java/com/amex/api/service/ProductService.java`
`src/main/java/com/amex/api/service/ProductServiceImpl.java`
###### Data Models Path:
`src/main/java/com/amex/api/data/Product.java`

### Tests:
`src/test/java/com/amex/api/service/ProductServiceTests.java`
`src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java`


### Endpoint:

`GET /api/products`
```json
[
  {
    "id": 1,
    "name": "Apple",
    "description": "A delicious red apple from the apple orchards at Morales Farms",
    "price": "0.60",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  },
  {
    "id": 2,
    "name": "Orange",
    "description": "A plump, juicy orange from the orange groves at Morales Farms",
    "price": "0.25",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  },
  {
    "id": 3,
    "name": "Melon",
    "description": "A ripe melon from the pristine gardens at Morales Farms",
    "price": "1.25",
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36"
  }
]
```
###
`POST /api/product`
###### Description: Create a new Product
```
 # add details
```
###
`GET /api/product/{id}`
###### Description: Retrieves a specific Product
```
 # add details
```
###
`DELETE /api/product/{id}`
###### Description: Deletes a specific Product
```
 # add details
```

#

# Testing
Unit tests as well as integration tests are located within the `src/test/java/com/amex/api` directory.

```
 # add details
```