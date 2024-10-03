package com.goutam.springBootCrud.services;

import com.goutam.springBootCrud.models.Characters;
import com.goutam.springBootCrud.repositories.SuperHeroCharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // or @Component
public class CrudService
{
    @Autowired
    SuperHeroCharacterRepository superHeroCharacterRepository;

    public void createSuperHero(Characters superHero)
    {
        superHeroCharacterRepository.save(superHero);
    }

    public Characters readSuperHero(int id)
    {
        return superHeroCharacterRepository.findById(id).get();
    }

    public void updateHumanName(Characters superHero, int id)
    {
        Characters superHeroToBeUpdated = readSuperHero(id);
        // or  Characters superHeroToBeUpdated = superHeroCharacterRepository.findById(id).get();
        superHeroToBeUpdated.setHumanName(superHero.getHumanName());
        createSuperHero(superHeroToBeUpdated);
        // or superHeroCharacterRepository.save(superHeroToBeUpdated);

    }

    public void updateName(Characters superHero, int id)
    {
        Characters superHeroToBeUpdated = readSuperHero(id);
        superHeroToBeUpdated.setName(superHero.getName());
        createSuperHero(superHeroToBeUpdated);
    }

    public void deleteSuperHero(int id)
    {
        Characters superHeroToBeDeleted = readSuperHero(id);
        superHeroCharacterRepository.delete(superHeroToBeDeleted);
    }

    //ReadAll API Endpoint - GET
    //localhost:8080/readAll
    public List<Characters> readAllSuperHero()
    {
        return superHeroCharacterRepository.findAll();
    }
}
