package br.com.industrial.aulacrud.dao;

import br.com.industrial.aulacrud.bean.Aluno;
import java.util.List;

public interface AlunoDAO {
    
    Aluno get(Integer id);
    List<Aluno> get(String nome);
    void delete(Aluno aluno);
    Aluno save(Aluno aluno);
}
