import aulas.Carrinho;
import aulas.Login;
import aulas.Produto;
import aulas.Usuario;
import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

public class Exercicio03 {

    private Usuario usuario;
    private Produto produto;
    private Carrinho carrinho;
    private Login login;
    private Faker faker;

    @Before
    public void preCondition(){
        faker = new Faker();
        String userName = faker.name().firstName();
        String email = userName + "@qa.com.br";
        String password = faker.internet().password();
        String productName = faker.pokemon().name();
        produto = new Produto(productName, 1000, "pokemon", 100);
        usuario =  new Usuario(userName, email, password, "true");
        login =  new Login(email, password);
        carrinho = new Carrinho();
    }

    @Test
    public void verifyStock() {
        String userID =  usuario.cadastrarUsuario(usuario);
        String userToken =  login.efetuarLogin(login);
        String productID = produto.cadastrarProduto(produto, userToken);
        carrinho.cadastrarCarrinho(productID,  5, userToken);
        produto.listarProdutoPorID(productID,  95);
        carrinho.cancelarCompra(userToken);
        produto.listarProdutoPorID(productID, 100);

    }
}