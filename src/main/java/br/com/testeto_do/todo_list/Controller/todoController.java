package br.com.testeto_do.todo_list.Controller;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import br.com.testeto_do.todo_list.Service.todoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class todoController {

    private todoService todoService;

    public todoController(todoService todoService){
        this.todoService = todoService;
    }

    @ApiResponse(responseCode = "200",description = "Tarefa cadastrada")
    @Operation(description = "Cria uma nova tarefa")
    @PostMapping
    public List<todoEntity> criarTarefa(@RequestBody @Valid todoEntity todo){
        return todoService.criarTarefa(todo);
    }

    @Operation(description = "Busca as tarefas")
    @GetMapping
    public List<todoEntity> listarTarefa(){
        return todoService.listarTarefas();
    }

    @Operation(description = "Atualiza as tarefas")
    @PutMapping
    public List<todoEntity> atualizarTarefa(@RequestBody @Valid todoEntity todo){
        return todoService.atualizarTarefa(todo);
    }

    @Operation(description = "Deleta as tarefas")
    @DeleteMapping("/{id}")
    public List<todoEntity> deletarTarefa(@PathVariable Long id){
        return todoService.deletarTarefa(id);
    }
}
