package edu.taller.service.impl;

import edu.taller.repo.IGenericRepo;
import edu.taller.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

    protected abstract IGenericRepo<T, ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T updade(ID id, T t) {
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void delete(ID id) {
        getRepo().deleteById(id);
    }


}
