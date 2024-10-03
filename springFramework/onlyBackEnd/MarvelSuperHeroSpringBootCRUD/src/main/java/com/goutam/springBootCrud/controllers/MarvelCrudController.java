package com.goutam.springBootCrud.controllers;

import com.goutam.springBootCrud.models.Characters;
import com.goutam.springBootCrud.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarvelCrudController
{
    @Autowired
    CrudService crudService;
    // CRUD
    // Create API Endpoint - POST
    //localhost:8080/create
    //body (raw,JSON)
    //{
    //    "name":"Iron Man",
    //    "humanName":"Tony Stark"
    //}
    @PostMapping("create")
    public void create(@RequestBody Characters superHero)
    {
        crudService.createSuperHero(superHero);
    }

    //Read API Endpoint - GET
    //localhost:8080/read?id=1
    @GetMapping("read")
    public Characters read(@RequestParam int id)
    {
        return crudService.readSuperHero(id);
    }

    //Update (humanName) API Endpoint - PUT
    //localhost:8080/updateHumanName?id=52
    //body (raw,JSON)
    //{
    //    "name":"Spider Man",  // name field is optional here
    //    "humanName":"Miles Morales"
    //}
    @PutMapping("updateHumanName")
    public void updateHumanName(@RequestBody Characters superHero, @RequestParam int id)
    {
        crudService.updateHumanName(superHero,id);
    }

    //Update (name) API Endpoint - PUT
    //localhost:8080/updateName?id=2
    //body (raw,JSON)
    //{
    //    "name":"Spider Man",
    //    "humanName":"Miles Morales"   // humanName field is optional here
    //}
    @PutMapping("updateName")
    public void updateName(@RequestBody Characters superHero, @RequestParam int id)
    {
        crudService.updateName(superHero,id);
    }

    //Delete API Endpoint - DELETE
    //localhost:8080/delete?id=2
    @DeleteMapping("delete")
    public void delete(@RequestParam int id)
    {
        crudService.deleteSuperHero(id);
    }

    @GetMapping("readAll")
    public List<Characters> readAll()
    {
        return crudService.readAllSuperHero();
    }



}
