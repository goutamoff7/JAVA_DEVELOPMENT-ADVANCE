package com.goutam.frontBackDirectConnect.services;


import com.goutam.frontBackDirectConnect.models.Client;
import com.goutam.frontBackDirectConnect.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService
{
    @Autowired
    ClientRepository clientRepository;

    public boolean saveClient(Client client)
    {
        try
        {
            Client savedClient = clientRepository.save(client);
            if(savedClient !=null)
            {
                return true;
            }
            else
                throw new Exception();

        }
        catch(Exception e)
        {
            return false;
        }
    }

    public List<Client> getClients()
    {
        return clientRepository.findAll();
    }
}
