package br.com.testeto_do.todo_list.Controller;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import br.com.testeto_do.todo_list.Service.todoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class todoController {

    private todoService todoService;

    public todoController(todoService todoService){
        this.todoService = todoService;
    }

    @ApiResponse(responseCode = "201",description = "Tarefa cadastrada")
    @Operation(description = "Cria uma nova tarefa")
    @PostMapping
    public ResponseEntity<List<todoEntity>> criarTarefa(@RequestBody @Valid todoEntity todo){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.criarTarefa(todo));
    }

    @Operation(description = "Busca as tarefas")
    @GetMapping
    public ResponseEntity<List<todoEntity>> listarTarefa(){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.listarTarefas());
    }

    @Operation(description = "Atualiza as tarefas")
    @PutMapping
    public ResponseEntity<List<todoEntity>> atualizarTarefa(@RequestBody @Valid todoEntity todo){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.atualizarTarefa(todo));
    }

    @Operation(description = "Deleta as tarefas")
    @DeleteMapping("/{id}")
    public ResponseEntity<List<todoEntity>> deletarTarefa(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.deletarTarefa(id));
    }
}
