package edu.taller.service.impl;

import edu.taller.model.Persona;
import edu.taller.repo.IGenericRepo;
import edu.taller.repo.IPersonaRepo;
import edu.taller.service.IPersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl extends CRUDImpl<Persona, Integer> implements IPersonaService {

    private final IPersonaRepo repo;

    @Override
    protected IGenericRepo<Persona, Integer> getRepo() {
        return repo;
    }
}
