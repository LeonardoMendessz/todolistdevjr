package br.com.testeto_do.todo_list.Service;

import br.com.testeto_do.todo_list.Repository.todoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class todoService {


    private todoRepository todoRepository;

    public todoService(todoRepository todoRepository){
        this.todoRepository = todoRepository;
    }



}
