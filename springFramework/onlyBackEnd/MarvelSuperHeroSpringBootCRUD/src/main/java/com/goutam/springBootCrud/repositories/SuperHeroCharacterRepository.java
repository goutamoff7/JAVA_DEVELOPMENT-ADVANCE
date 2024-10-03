package com.goutam.springBootCrud.repositories;

import com.goutam.springBootCrud.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // or @Component
public interface SuperHeroCharacterRepository extends JpaRepository<Characters,Integer>
{

}
