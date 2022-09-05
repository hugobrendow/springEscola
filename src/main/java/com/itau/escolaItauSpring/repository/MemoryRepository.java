package com.itau.escolaItauSpring.repository;


import com.itau.escolaItauSpring.exception.ItemNaoExistenteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MemoryRepository<K,E> {
    protected Map<K,E> dados = new HashMap<>();

    protected abstract K novaChave();

    protected abstract void setChave(E item, K chave);

    public E adicionar(E item) {
        K chave = novaChave();
        setChave(item, chave);
        dados.put(chave, item);
        return item;
    }

    public E remover(K chave) throws ItemNaoExistenteException {
        E item = dados.remove(chave);
        if (item == null) {
            throw new ItemNaoExistenteException();
        }
        return item;
    }

    public E localizar(K chave) {
        return dados.get(chave);
    }

    public List<E> listar() {
        List<E> lista = new ArrayList<>(dados.values());
        return lista;
    }

    public E alterar(K chave, E item) throws ItemNaoExistenteException {
        E antigo = dados.get(chave);
        if (antigo == null) {
            throw new ItemNaoExistenteException();
        }
        setChave(item, chave);
        dados.put(chave, item);
        return item;
    }
}
