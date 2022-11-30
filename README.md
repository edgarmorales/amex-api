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
[
  {
    "id": 1,
    "customer": {
      "id": 1,
      "email": "sophie.bon-customer@gmail.com",
      "firstName": "Sophie",
      "lastName": "Bon-Customer",
      "cellphone": "917-555-5555",
      "dateCreated": "2022-11-30 02:38:48",
      "dateModified": "2022-11-30 02:38:48"
    },
    "associate": {
      "id": 1,
      "email": "edgar.bon-associate@amex.com",
      "storeId": "12345",
      "firstName": "Edgar",
      "lastName": "Bon-Associate",
      "cellphone": "917-222-2222",
      "hireDate": "2022-11-30 02:38:48"
    },
    "orderItems": [
      {
        "quantity": 1,
        "totalProductDiscountPrice": "1.00",
        "product": {
          "id": 1,
          "name": "Apple",
          "description": "A delicious red apple from the apple orchards at Morales Farms",
          "price": "1.00",
          "dateCreated": "2022-11-30 02:38:48",
          "dateModified": "2022-11-30 02:38:48"
        },
        "totalProductNetPrice": "1.00"
      },
      {
        "quantity": 1,
        "totalProductDiscountPrice": "1.00",
        "product": {
          "id": 2,
          "name": "Orange",
          "description": "A plump, juicy orange from the orange groves at Morales Farms",
          "price": "1.00",
          "dateCreated": "2022-11-30 02:38:48",
          "dateModified": "2022-11-30 02:38:48"
        },
        "totalProductNetPrice": "1.00"
      }
    ],
    "dateCreated": "2022-11-30 02:38:48",
    "dateModified": "2022-11-30 02:38:48",
    "totalOrderNetPrice": "2.00",
    "totalOrderDiscountPrice": "2.00"
  }
]
```
#
`POST /api/order`
###### Description: Creates an order
```
 # add details
```
#
`GET /api/order/{id}`
###### Description: Returns a specific order
```
# add details
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