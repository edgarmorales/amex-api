# AMEX API 
This project encapsulates an Order Service used to create, edit, view, and delete Orders for a fictious company. Below are the list of additional models used to encapsulate and process orders.


# Services

## OrderService
The order service includes methods to create, edit, view and delete orders.

### Paths:
###### Controller Path
`src/main/java/com/amex/api/controller/OrderController.java`
###### Service Interface/Impl Paths
`src/main/java/com/amex/api/service/OrderService.java` 
`src/main/java/com/amex/api/service/OrderServiceImpl.java`
###### Data Models Path:
`src/main/java/com/amex/api/data/Order.java`
`src/main/java/com/amex/api/data/OrderItem.java`
`src/main/java/com/amex/api/data/OrderItemPk.java`

### Tests:
`src/test/java/com/amex/api/service/OrderServiceTests.java`
`src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java`

### Endpoint:

`GET /api/orders`

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
      "dateCreated": "2022-11-29 08:44:36",
      "dateModified": "2022-11-29 08:44:36"
    },
    "associate": {
      "id": 1,
      "email": "edgar.bon-associate@amex.com",
      "storeId": "12345",
      "firstName": "Edgar",
      "lastName": "Bon-Associate",
      "cellphone": "917-222-2222",
      "hireDate": "2022-11-29 08:44:36"
    },
    "orderItems": [
      {
        "quantity": 10,
        "product": {
          "id": 1,
          "name": "Apple",
          "description": "A delicious red apple from the apple orchards at Morales Farms",
          "price": "0.60",
          "dateCreated": "2022-11-29 08:44:36",
          "dateModified": "2022-11-29 08:44:36"
        },
        "totalProductNetPrice": "6.00",
        "totalProductDiscountPrice": "3.00"
      },
      {
        "quantity": 10,
        "product": {
          "id": 2,
          "name": "Orange",
          "description": "A plump, juicy orange from the orange groves at Morales Farms",
          "price": "0.25",
          "dateCreated": "2022-11-29 08:44:36",
          "dateModified": "2022-11-29 08:44:36"
        },
        "totalProductNetPrice": "2.50",
        "totalProductDiscountPrice": "1.70"
      }
    ],
    "dateCreated": "2022-11-29 08:44:36",
    "dateModified": "2022-11-29 08:44:36",
    "totalOrderNetPrice": "8.50",
    "totalDiscountedPrice": "4.70"
  }
]
```
### Endpoint: Create a new Order
`POST /api/order`

```json
// TODO: Provide more details
```
### Endpoint: Lookup a specific Order
`GET /api/order/{id}`

```json
// TODO: Provide more details
```
### Endpoint: Delete a specific Order
`DELETE /api/order/{id}`

```json
// TODO: Provide more details
```

## AssociateService
The Associate Service include methods to create, edit, view, and delete company associates (who faciliate an order submission).

### Paths:
###### Controller Path
`src/main/java/com/amex/api/controller/AssociateController.java`
###### Service Interface/Impl Paths
`src/main/java/com/amex/api/service/AssociateService.java`
`src/main/java/com/amex/api/service/AssociateServiceImpl.java`
###### Data Models Path:
`src/main/java/com/amex/api/data/Associate.java`

### Tests:
`src/test/java/com/amex/api/service/AssociateServiceTests.java`
`src/test/java/com/amex/api/AmexApiApplicationIntegrationTests.java`


### Endpoint:
`GET /api/associates`
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
### Endpoint: Create a new Associate
`POST /api/associate`

```json
// TODO: Provide more details
```
### Endpoint: Lookup a specific Associate
`GET /api/associate/{id}`

```json
// TODO: Provide more details
```
### Endpoint: Delete a specific Associate
`DELETE /api/associate/{id}`

```json
// TODO: Provide more details
```

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


### Endpoint: View Customers

`GET /api/customers`
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
### Endpoint: Create a new Customer
`POST /api/customer`

```json
// TODO: Provide more details
```
### Endpoint: Lookup a specific Customer
`GET /api/customer/{id}`

```json
// TODO: Provide more details
```
### Endpoint: Delete a specific Customer
`DELETE /api/customer/{id}`

```json
// TODO: Provide more details
```


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
### Endpoint: Create a new Product
`POST /api/product`

```json
// TODO: Provide more details
```
### Endpoint: Lookup a specific Product
`GET /api/product/{id}`

```json
// TODO: Provide more details
```
### Endpoint: Delete a specific Product
`DELETE /api/product/{id}`

```json
// TODO: Provide more details
```



# Testing
Unit tests as well as integration tests are located within the `src/test/java/com/amex/api` directory.

`// TODO: Provide more details`