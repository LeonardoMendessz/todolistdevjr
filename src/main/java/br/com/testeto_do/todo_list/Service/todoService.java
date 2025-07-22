package br.com.testeto_do.todo_list.Service;

import br.com.testeto_do.todo_list.Entity.todoEntity;
import br.com.testeto_do.todo_list.Repository.todoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class todoService {


    private todoRepository todoRepository;

    public todoService(todoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<todoEntity> criarTarefa(todoEntity todo){
        todoRepository.save(todo); // vamos retornar a lista posteriormente
        return listarTarefas();
    }


    public List<todoEntity> listarTarefas(){
        Sort sort = Sort.by("prioridade").descending();
        return todoRepository.findAll(sort);
    }

    public List<todoEntity> atualizarTarefa(todoEntity todo){
        todoRepository.save(todo);
        return listarTarefas();
    }

    public List<todoEntity> deletarTarefa(Long id){
        todoRepository.deleteById(id);
        return listarTarefas();
    }


}
