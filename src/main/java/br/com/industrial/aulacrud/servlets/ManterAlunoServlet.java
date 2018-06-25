package br.com.industrial.aulacrud.servlets;

import br.com.industrial.aulacrud.bean.Aluno;
import br.com.industrial.aulacrud.dao.AlunoDAO;
import br.com.industrial.aulacrud.dao.impl.AlunoDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManterAlunoServlet", urlPatterns = {"/ManterAlunoServlet"})
public class ManterAlunoServlet extends HttpServlet {

    private Integer id;
    private AlunoDAO alunoDAO;
    private Aluno aluno;

    private Aluno recuperarAluno(HttpServletRequest request) {
        id = Integer.parseInt(request.getParameter("id"));
        alunoDAO = new AlunoDAOImpl();
        aluno = alunoDAO.get(id);
        return aluno;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "novo":
                request.setAttribute("aluno", new Aluno());
                request.getRequestDispatcher("/editarAluno.jsp").forward(request, response);
                break;
            case "editar":
                request.setAttribute("aluno", recuperarAluno(request));
                request.getRequestDispatcher("/editarAluno.jsp").forward(request, response);
                break;
            case "excluir":
                alunoDAO.delete(recuperarAluno(request));
                request.setAttribute("msg", "Aluno " + aluno.getNome() + " excluido com sucesso");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        aluno = new Aluno();
        if(!request.getParameter("id").equals("")){
            aluno.setId(Integer.parseInt(request.getParameter("id")));
        }
        aluno.setNome(request.getParameter("nome"));
        aluno.setIdade(Integer.parseInt(request.getParameter("idade")));
        alunoDAO = new AlunoDAOImpl();
        alunoDAO.save(aluno);
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
