#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Place an order in Ecommerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on HomePage
  Given I moved to AccountCreationPage

  
  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Registered with firstName <firstName> and lastName <lastName> and email<email> and password <password> 
    When  I add product <productName> to Cart and submit the order
    Then "Thank you for your purchase!" message is displayed on ConfirmationPage.

    Examples: 
      | email                      | password    | firstName |lastName |productName         | address             |country       |state  |city   |postCode |telephone    |
      | testjohhndep@gmail.com     | Password123 | Johny     | Depp    |Eos V-Neck Hoodie   | 162 Blue Spring Ave.|United States |Florida|Deltona| 32725   |407-555-0113 |
    
