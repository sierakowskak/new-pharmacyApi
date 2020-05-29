Feature: Orders

  Scenario: Get all orders
    Given url 'http://localhost:8080/orders'
    When method GET
    Then status 200

  Scenario: Add order
    Given url 'http://localhost:8080/orders'
    And request { id: 1, client: { id: 1 }, medicineOrders: null }
    When method POST
    Then status 201

  Scenario: Get order by id
    Given url 'http://localhost:8080/orders/1'
    When method GET
    Then status 200

  Scenario: Delete order by id
    Given url 'http://localhost:8080/orders/1'
    When method DELETE
    Then status 200

