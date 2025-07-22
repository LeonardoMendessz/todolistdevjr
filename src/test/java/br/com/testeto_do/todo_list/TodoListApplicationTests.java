package br.com.testeto_do.todo_list;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
class TodoListApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	WebTestClient webTestClient;

	public void criarComSucess() {
		todoEntity todo = new todoEntity("testar isso", "teste com sucesso?", false, 2);
	}
}