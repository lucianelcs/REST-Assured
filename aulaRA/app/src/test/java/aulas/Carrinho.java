package aulas;

import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

public class Carrinho {

    private String idProduto;

    private Integer quantidade;

    public Carrinho() { }
    public Carrinho(String idProduto, Integer quantidade) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
    }

    public String cadastrarCarrinho(String idProduto, Integer quantidade, String userToken) {
        String carrinhoID = given()
                .header("authorization", userToken)
                .body("{\n" +
                        "  \"produtos\": [\n" +
                        "    {\n" +
                        "      \"idProduto\": \"" + idProduto + "\",\n" +
                        "      \"quantidade\":" + quantidade + "\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .contentType("application/json")
                .when()
                .post("http://localhost:3000/carrinhos")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("message", is("Cadastro realizado com sucesso"))
                .extract().path("_id");
        return carrinhoID;
    }

    public void cancelarCompra(String userToken) {
        given()
                .header("authorization", userToken)
                .when()
                .delete("http://localhost:3000/carrinhos/cancelar-compra")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("message", is("Registro excluído com sucesso. Estoque dos produtos reabastecido"));
    }

    public void verificarCarrinho(String userToken) {
        when()
                .get("http://localhost:3000/carrinhos/")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}