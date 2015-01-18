@entro
Feature: Dashboard checks for entrepreneurs

  Scenario: User can login

    Given User is in login page of entrostor
    When he tries to login with valid credentials
    Then user is in dashboard page

