package Teste_ApiRest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;

public class testeApi {


    @Test
    public void verificaMarcas(){

                given()
                .when()
                    .get("http://desafioonline.webmotors.com.br/api/OnlineChallenge/Make")
                .then()
                    .statusCode(200)
                    .body("Make.size()", is(3))
                        //.rootPath("Make.ID")
                        .body("Name[0]", is("Chevrolet"))
                        .body("Name[1]", is("Honda"))
                        .body("Name[2]", is("Ford"))
               ;

    }

@Test
    public void verificaModelos(){

                given()
                        .when()
                        .get("http://desafioonline.webmotors.com.br/api/OnlineChallenge/Model?MakeID=1")
                        .then()
                        .statusCode(200)
                        .body("Model.size()", is(3))
                        //.rootPath("Make.ID")
                        .body("Name[0]", is("Agile"))
                        .body("Name[1]", is("Astra"))
                        .body("Name[2]", is("Onix"))
                        ;
}

    @Test
    public void verificVersao(){

        given()
                .when()
                .get("http://desafioonline.webmotors.com.br/api/OnlineChallenge/Version?ModelID=1")
                .then()
                .statusCode(200)
               // .body("Version.size()", is(5))
                //.rootPath("Make.ID")
               // .body("Name[0]", is("1.5 DX 16V FLEX 4P AUTOMÁTICO"))
              //  .body("Name[1]", is("1.5 LX 16V FLEX 4P MANUAL"))
              //  .body("Name[2]", is("2.0 EXL 4X4 16V GASOLINA 4P AUTOMÁTICO"))
            //    .body("Name[3]", is("1.8 16V EVO FLEX FREEDOM OPEN EDITION AUTOMÁTICO"))
            //    .body("Name[4]", is("1.0 MPI EL 8V FLEX 4P MANUAL"))

        ;
    }

}
