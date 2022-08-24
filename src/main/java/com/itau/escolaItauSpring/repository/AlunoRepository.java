package com.itau.escolaItauSpring.repository;

import com.itau.escolaItauSpring.model.Aluno;

import java.util.UUID;

public class AlunoRepository extends MemoryRepository<UUID, Aluno> {

    @Override
    protected UUID novaChave() {
        return UUID.randomUUID();
    }

    @Override
    protected void setChave(Aluno item, UUID chave) {
        item.setId(chave);
    }
}
