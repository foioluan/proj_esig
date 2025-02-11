package com.br.projEsig.repository;

import java.util.List;

import com.br.projEsig.domain.Task;

public interface TaskRepository {
	public void save(Task task);

    public List<Task> findAll();

    public void delete(Long id);

    public void update(Task task);

    public List<Task> filterFind(String title, String description, String manager, String priority, String status);
}
