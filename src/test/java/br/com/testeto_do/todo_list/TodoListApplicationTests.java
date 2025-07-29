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

	@Test
	public void criarComErro(){
		todoEntity todo = new todoEntity("","",false,0);
		webTestClient.post()
				.uri("/todo")
				.bodyValue(new todoEntity("","",false,0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Test
public void testeDelete(){
	todoEntity todoDel = new todoEntity("testar isso", "deletado com sucesso", false, 2);
	todoEntity todoCriada =
			webTestClient.post()
					.uri("/todo")
					.bodyValue(todoDel)
					.exchange()
					.expectStatus().isOk()
					.expectBodyList(todoEntity.class)
					.returnResult()
					.getResponseBody()
					.get(0);

	// agora o delete

	webTestClient.delete()
			.uri("/todo/" + todoCriada.getId())
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(0);

}

@Test
public void testarPut(){
		//criar a tarefa original
	todoEntity todoOg = new todoEntity("atualizar", "para atualizar", false, 2);
	todoEntity todoCriada =
			webTestClient.post()
					.uri("/todo")
					.bodyValue(todoOg)
					.exchange()
					.expectStatus().isOk()
					.expectBodyList(todoEntity.class)
					.returnResult()
					.getResponseBody()
					.get(0);

	//criar novo objeto com dados atualizados
	todoEntity todoAtualizada = new todoEntity();
	todoAtualizada.setId(todoCriada.getId());
	todoAtualizada.setNome("atualizei");
	todoAtualizada.setDescricao("atualizei ja a tarefa");
	todoAtualizada.setRealizado(true);
	todoAtualizada.setPrioridade(1);

	//enviar o put com a tarefa atualizada
	webTestClient.put()
			.uri("/todo")
			.bodyValue(todoAtualizada)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$[0].nome").isEqualTo("atualizei")
			.jsonPath("$[0].descricao").isEqualTo("atualizei ja a tarefa")
			.jsonPath("$[0].realizado").isEqualTo(true)
			.jsonPath("$[0].prioridade").isEqualTo(1);

}

}