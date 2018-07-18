Feature: Get both debit and credit cards for the given customer

  @happy
  Scenario: 01 Customer with no debit or credit cards
	Given the customer "getcards_01"
    And this customer has 0 debit cards
    And this customer has 0 credit cards
	When I get the cards of this customer from our API
	Then I receive a response with status 200
    And the response contains 0 cards
    And the response contains the error message ""
    And the gDebitCards API received a GET request on the resource "debit-cards" for this customer
    And the gCreditCards API received a GET request on the resource "credit-cards" for this customer

  @happy
  Scenario: 02 Customer with 2 debit and 2 credit cards
    Given the customer "getcards_02"
    And this customer has 2 debit cards
    And this customer has 2 credit cards
    When I get the cards of this customer from our API
    Then I receive a response with status 200
    And the response contains 4 cards
    And the response contains the error message ""
    And the gDebitCards API received a GET request on the resource "debit-cards" for this customer
    And the gCreditCards API received a GET request on the resource "credit-cards" for this customer

  @unhappy
  Scenario: 03 Customer with 2 debit cards, but gCreditCardsApi returns an error
    Given the customer "getcards_03"
    And this customer has 2 debit cards
    And the gCreditCards API responds with status 500 for a GET request on the resource "credit-cards" for this customer
    When I get the cards of this customer from our API
    Then I receive a response with status 200
    And the response contains 2 cards
    And the response contains the error message "We were not able to retrieve your credit cards at this time"
    And the gDebitCards API received a GET request on the resource "debit-cards" for this customer
    And the gCreditCards API received a GET request on the resource "credit-cards" for this customer

  @unhappy
  Scenario: 04 Customer with 2 credjt cards, but gDebitCardsApi returns an error
    Given the customer "getcards_04"
    And this customer has 2 credit cards
    And the gDebitCards API responds with status 500 for a GET request on the resource "debit-cards" for this customer
    When I get the cards of this customer from our API
    Then I receive a response with status 200
    And the response contains 0 cards
    And the response contains the error message "We were not able to retrieve your cards at this time"
    And the gDebitCards API received a GET request on the resource "debit-cards" for this customer

  @unhappy
  Scenario: 05 Both gDebitCardsApi and gCreditCardsApi return an error
    Given the customer "getcards_05"
    And the gDebitCards API responds with status 500 for a GET request on the resource "debit-cards" for this customer
    And the gCreditCards API responds with status 500 for a GET request on the resource "credit-cards" for this customer
    When I get the cards of this customer from our API
    Then I receive a response with status 200
    And the response contains 0 cards
    And the response contains the error message "We were not able to retrieve your cards at this time"
    And the gDebitCards API received a GET request on the resource "debit-cards" for this customer

  @performance
  Scenario: 06 100 requests in a second
#    Given the customer "getcards_06"