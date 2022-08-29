package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.IPersonajeRepository;
import com.alkemy.disney.disney.service.IPersonajeService;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonajeService implements IPersonajeService {
    @Autowired
    private IPersonajeRepository personajeRepository;
    @Autowired
    private PersonajeMapper personajeMapper;
}
