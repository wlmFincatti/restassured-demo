Feature: Animes

  Background:
    Given i authenticate  with user "admin" and password "admin123"

  @auth
  Scenario Outline: Get an delete anime by id
    Given i have id "<id>"
    When i make request "<httpVerb>" to endpoint "<endpoint>"
    Then i expected status code <statusCodeExpected>
    Examples:
      | id    | httpVerb | endpoint | statusCodeExpected |
      | id254 | GET      | /{id}    | 200                |
      | id123 | DELETE   | /{id}    | 204                |

  @auth
  Scenario: Get all animes
    When i make request "GET" to endpoint "/"
    Then i expected status code 200

  @noauth
  Scenario: Should not get animes with wrong auth
    Given i authenticate  with user "admin" and password "admin1234"
    When i make request "GET" to endpoint "/"
    Then i expected status code 401