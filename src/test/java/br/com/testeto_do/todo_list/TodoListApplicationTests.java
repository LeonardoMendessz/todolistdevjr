package br.com.testeto_do.todo_list;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

import static net.bytebuddy.matcher.ElementMatchers.isArray;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoListApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void criarComSucess() {
		todoEntity todo = new todoEntity("testar isso", "teste com sucesso?", false, 2);

		webTestClient.post()// envia o metodo post
				.uri("/todo")// para a uri todo
				.bodyValue(todo)//usando o corpo json todo
				.exchange()//executa
				.expectStatus().isOk()//espera o status 200 ok
				.expectBody() // espera que o corpo tenha
				.jsonPath("$").isArray() // seja um array
				.jsonPath("$.length()").isEqualTo(1) // tenha um objeto
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}
}