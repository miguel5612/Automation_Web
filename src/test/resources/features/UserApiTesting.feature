#Autor: Miguel Califa 14/04/2024

@stories
Feature: Realizar la prueba automatizada de una API

  @apiRest

  Scenario Outline: Como usuario deseo realizar las siguientes peticiones
    Given el usuario "<Usuario>" realiza la peticion "<NombrePeticion>"
    When llama al endpoint correspondiente el actor "<Usuario>"
      | Metodo   | EndPoint   | Headers   | Body   | FileURL   |
      | <Metodo> | <EndPoint> | <Headers> | <Body> | <FileURL> |
    Then deberia "<Usuario>" recibir una respuesta acorde a los parametros preestablecidos
      | PathParams   | RespuestasEsperadas   |
      | <PathParams> | <RespuestasEsperadas> |

    Examples:
      | NombrePeticion   | Metodo   | EndPoint   | Headers   | Body   | FileURL   | PathParams   | RespuestasEsperadas   | Usuario   |
      ##@externaldata@./src/test/resources/datadriven/apiData.xlsx@infoAPI
   |Get a Single Member   |GET   |/api/members/1   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |id#name#gender   |1#Monil#Female   |miguel1|
   |Get a Single Autor   |GET   |/api/authors/1   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |id#name#age#gender   |1#Monil#34#Female   |miguel2|
   |Create a new Member   |POST   |/api/members   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Ryan","gender":"Male"}   |   |name#gender   |Ryan#Male   |miguel3|
   |Upload a File   |POST   |/api/upload   |Authorization: Basic YWRtaW46YWRtaW4='   |   |C:/Users/Usuario/Documents/Automation_Web/src/test/resources/jpg/demo.jpg   |success#message   |true#File uploaded successfully!   |miguel7|
   |Download a File   |GET   |/api/download?name=Yey.jpg   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |   |   |miguel8|
   |Delete a Member   |DELETE   |/api/members/5   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |msg   |Member with id 5 is deleted successfully   |miguel4|
   |Update a Member Via PATCH   |PATCH   |/api/members/21   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Alee"}   |   |msg   |Member with id 21 is updated successfully   |miguel6|
   |Update a Member Via PUT   |PUT   |/api/members/20   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Ravina","gender":"Female"}   |   |msg   |Member with id 20 is updated successfully   |miguel5|
