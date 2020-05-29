Feature: Client

  Scenario: Get all clients
    Given url 'http://localhost:8080/clients'
    When method GET
    Then status 200

  Scenario: Add client
    Given url 'http://localhost:8080/clients'
    And request { id: 1, firstName: 'name', lastName: 'lastName', address: 'address', prescriptions: null }
    When method POST
    Then status 201

  Scenario: Get one client using id
    Given url 'http://localhost:8080/clients/1'
    When method GET
    Then status 200

  Scenario: Delete client using id
    Given url 'http://localhost:8080/clients/1'
    When method DELETE
    Then status 200
