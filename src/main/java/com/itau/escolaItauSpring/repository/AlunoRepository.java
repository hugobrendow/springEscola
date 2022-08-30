package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
    Long countAlunoByAtivado(Boolean ativado);
    @Transactional
    void deleteByCpf(Long cpf);

    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    // JPQL
    @Query("select aluno from Aluno aluno where aluno.cpf = :cpf and aluno.nome like :nome")
    Aluno buscarPorCpfENome(Long cpf, String nome);
}
