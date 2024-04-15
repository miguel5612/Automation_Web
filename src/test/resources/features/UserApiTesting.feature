#Autor: Miguel Califa 14/04/2024

@stories
Feature: Realizar la prueba automatizada de una API

  @apiRest

  Scenario Outline: Como usuario deseo realizar las siguientes peticiones
    Given el usuario "<usuario>" realiza la peticion "<nombrePeticion>"
    When llama al endpoint correspondiente
      | metodo   | URL   | Headers   | Body   | fileURL   |
      | <metodo> | <URL> | <Headers> | <Body> | <fileURL> |
    Then deberia recibir una respuesta acorde a los parametros preestablecidos
      | pathParams   | respuestasEsperadas   |
      | <pathParams> | <respuestasEsperadas> |

    Examples:
      | nombrePeticion   | metodo   | URL   | Headers   | Body   | fileURL   | pathParams   | respuestasEsperadas   | usuario   |
      ##@externaldata@./src/test/resources/datadriven/apiData.xlsx@infoAPI
   |Get a Single Member   |GET   |http://localhost:5002/api/members/1   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |id#name#gender   |1#Monil#Female   |miguel|
   |Get a Single Autor   |GET   |http://localhost:5002/api/authors/1   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |id#name#age#gender   |1#Monil#34#Female   |miguel|
   |Delete a Member   |DELETE   |http://localhost:5002/api/members/40   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |msg   |Member with id 4 is deleted successfully   |miguel|
   |Update a Member Via PUT   |PUT   |http://localhost:5002/api/members/5   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Ravina","gender":"Female"}   |   |msg   |Member with id 5 is updated successfully   |miguel|
   |Update a Member Via PATCH   |PATCH   |http://localhost:5002/api/members/5   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Alee"}   |   |msg   |Member with id 5 is updated successfully   |miguel|
   |Create a new Member   |POST   |http://localhost:5002/api/members   |Authorization: Basic YWRtaW46YWRtaW4='   |{"name":"Ryan","gender":"Male"}   |   |name#gender   |Ryan#Male   |miguel|
   |Upload a File   |POST   |http://localhost:5002/api/upload   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |success#message   |true#File uploaded successfully!   |miguel|
   |Download a File   |GET   |http://localhost:5002/api/download?name=Yey.jpg   |Authorization: Basic YWRtaW46YWRtaW4='   |   |   |   |   |miguel|
