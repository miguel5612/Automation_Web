#Autor: Miguel Califa 13/12/2022

@stories
Feature: As a user, I want login in the Star Sharp Page so i should create a new meetings
  Background:
    Given that the user is on the client page
    And the user Logs in
      | user | password  |
      | admin | serenity |

  @creandoUnaUnidadNegocio

  Scenario Outline: Create a new  Bussines unit
    And he go to the Bussines Units
    When he create a new unit <unitName>
    Then the <unitName> should display in the menu
    Examples:
      |unitName|
      |COL - Miguel Califa|
      |ESP - Miguel Califa|
      |CAN - Miguel Califa|
      |CN - Miguel Califa|
