
Feature: Validating Add Place API


  Scenario: Verify places is being successfully added to AddAPI 
    Given Add Place Payload
    When User calls AddAPI with post http request
    Then The API call get success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
