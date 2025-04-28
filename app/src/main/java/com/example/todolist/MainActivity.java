package com.example.todolist;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription;
    private Button buttonAddTask;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private AppDatabase database;
    private List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar componentes da interface
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        recyclerView = findViewById(R.id.recyclerView);

        // Configurar o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Inicializar o banco de dados e o adapter
        database = AppDatabase.getInstance(this);
        taskAdapter = new TaskAdapter(taskList, task -> {
            // Ação para remover tarefa
            database.taskDao().delete(task);
            loadTasks(); // Atualizar a lista
        });
        recyclerView.setAdapter(taskAdapter);

        // Botão "Adicionar Tarefa"
        buttonAddTask.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(description)) {
                // Criar nova tarefa
                Task task = new Task();
                task.setTitle(title);
                task.setDescription(description);

                // Salvar no banco de dados
                database.taskDao().insert(task);

                // Limpar os campos de entrada
                editTextTitle.setText("");
                editTextDescription.setText("");

                // Atualizar a lista de tarefas
                loadTasks();
            }
        });

        // Carregar tarefas ao iniciar
        loadTasks();
    }

    // Método para carregar as tarefas do banco de dados
    private void loadTasks() {
        taskList.clear();
        taskList.addAll(database.taskDao().getAllTasks());
        taskAdapter.notifyDataSetChanged();
    }
}