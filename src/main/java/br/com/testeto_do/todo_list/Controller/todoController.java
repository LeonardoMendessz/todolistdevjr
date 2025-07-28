package br.com.testeto_do.todo_list.Controller;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import br.com.testeto_do.todo_list.Service.todoService;
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

    @PostMapping
    public List<todoEntity> criarTarefa(@RequestBody @Valid todoEntity todo){
        return todoService.criarTarefa(todo);
    }

    @GetMapping
    public List<todoEntity> listarTarefa(){
        return todoService.listarTarefas();
    }

    @PutMapping
    public List<todoEntity> atualizarTarefa(@RequestBody @Valid todoEntity todo){
        return todoService.atualizarTarefa(todo);
    }

    @DeleteMapping("/{id}")
    public List<todoEntity> deletarTarefa(@PathVariable Long id){
        return todoService.deletarTarefa(id);
    }
}
