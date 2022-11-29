# AMEX API 
This project encapsulates an Order Service used to create, edit, view, and delete Orders for a fictious company. Below are the list of additional models used to encapsulate and process orders.



# Services

## OrderService
The order service includes methods to create, edit, view and delete orders.

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

## AssociateService
The Associate Service include methods to create, edit, view, and delete company associates (who faciliate an order submission).

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

## CustomerService
The Customer Service include methods to create, edit, view, and delete customers.

### Endpoint:

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

## ProductService
The product service includes methods to create, edit, view and delete products.

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