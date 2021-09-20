/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemploentidadejpa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.persistence.Persistence;
import manager.AlunoJpaController;
import model.Aluno;

/**
 *
 * @author nathan
 */
public class SistemaEscola {

    private final AlunoJpaController dao = new AlunoJpaController(
            Persistence.createEntityManagerFactory(
                    "ExemploEntidadeJPAPU"));
    private static final BufferedReader entrada
            = new BufferedReader(new InputStreamReader(System.in));

    private void exibir(Aluno aluno) {
        System.out.println("Aluno: " + aluno.getNome()
                + "\nMatricula: " + aluno.getMatricula()
                + "\tEntrada: " + aluno.getEntrada()
                + "\n==========================");
    }

    public void exibirTodos() {
        dao.findAlunoEntities().forEach(aluno -> exibir(aluno));
    }

    public void inserirAluno() throws Exception {
        Aluno aluno = new Aluno();
        System.out.println("Nome:");
        aluno.setNome(entrada.readLine());
        System.out.println("Matricula:");
        aluno.setMatricula(entrada.readLine());
        System.out.println("Entrada:");
        aluno.setEntrada(Integer.parseInt(entrada.readLine()));
        dao.create(aluno);
    }

    public void excluirAluno() throws Exception {
        System.out.println("Matricula:");
        String matricula = entrada.readLine();
        dao.destroy(matricula);
    }

    public static void main(String args[]) throws Exception {
        SistemaEscola sistema = new SistemaEscola();
        while (true) {
            System.out.println(
                    "1-Listar\t2-Inserir\t3-Excluir\t0-Sair");
            int opcao = Integer.parseInt(entrada.readLine());
            if (opcao == 0) {
                break;
            }
            switch (opcao) {
                case 1:
                    sistema.exibirTodos();
                    break;
                case 2:
                    sistema.inserirAluno();
                    break;
                case 3:
                    sistema.excluirAluno();
                    break;
            }
        }
    }
}
