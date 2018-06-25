package br.com.industrial.aulacrud.dao.impl;

import br.com.industrial.aulacrud.bean.Aluno;
import br.com.industrial.aulacrud.dao.AlunoDAO;
import br.com.industrial.aulacrud.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {

    @Override
    public Aluno get(Integer id) {
        Aluno aluno = null;
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id, nome, idade FROM Alunos WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                aluno = new Aluno(rs.getInt("id"),
                        rs.getString("nome"), rs.getInt("idade"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao executar get " + id);
        }

        return aluno;
    }

    @Override
    public List<Aluno> get(String nome) {
        List<Aluno> alunos = new ArrayList<>();
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT id, nome, idade FROM Alunos WHERE nome LIKE ?");
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno(rs.getInt("Id"),
                        rs.getString("nome"), rs.getInt("idade"));
                alunos.add(a);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao executar get " + nome);
        }

        return alunos;
    }

    @Override
    public void delete(Aluno aluno) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("DELETE FROM Alunos WHERE id = ?");
            ps.setInt(1, aluno.getId());
            ps.execute();

        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
                throw new RuntimeException("Erro ao executar delete " + aluno);
            } catch (SQLException ex1) {
                throw new RuntimeException("Erro ao executar delete " + aluno);
            }
        } finally {
            try {
                if (con != null) {
                    con.commit();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao executar delete " + aluno);
            }
        }
    }

    @Override
    public Aluno save(Aluno aluno) {
        if (aluno.getId() != null) {
            atualizar(aluno);
        } else {
            salvar(aluno);
        }
        return aluno;
    }

    private void atualizar(Aluno aluno) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("UPDATE Alunos SET nome = ?, idade = ? WHERE id = ?");
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getIdade());
            ps.setInt(3, aluno.getId());
            if (ps.executeUpdate() == 0) {
                throw new RuntimeException("Erro ao executar update " + aluno);
            }
        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
                throw new RuntimeException("Erro ao executar update " + aluno);
            } catch (SQLException ex1) {
                throw new RuntimeException("Erro ao executar update " + aluno);
            }
        } finally {
            try {
                if (con != null) {
                    con.commit();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao executar update " + aluno);
            }
        }
    }

    private void salvar(Aluno aluno) {
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement("INSERT INTO Alunos(nome, idade) VALUES(?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, aluno.getNome());
            ps.setInt(2, aluno.getIdade());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                aluno.setId(rs.getInt(1));
            }

        } catch (SQLException ex) {
            try {
                if (con != null) {
                    con.rollback();
                }
                throw new RuntimeException("Erro ao executar salvar " + aluno);
            } catch (SQLException ex1) {
                throw new RuntimeException("Erro ao executar salvar " + aluno);
            }
        } finally {
            try {
                if (con != null) {
                    con.commit();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao executar salvar " + aluno);
            }
        }
    }

}
