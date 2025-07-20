    package com.goutam.frontBackDirectConnect.controllers;

    import com.goutam.frontBackDirectConnect.models.Client;
    import com.goutam.frontBackDirectConnect.services.ClientService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RestController;

    import java.util.List;

    @RestController
    public class ClientController
    {
        @Autowired
        ClientService clientService;

        //localhost:8080/save
    //    body
    //    {
    //        "name":"Goutam Dam"
    //    }

        @PostMapping("/save")
        public ResponseEntity<String> saveClient(@RequestBody Client client)
        {
            if(clientService.saveClient(client))
            {
                return new ResponseEntity<>("Client's Entry saved Successfully", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Client's Entry Failed save", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        @GetMapping("/getClients")
        public ResponseEntity<List<Client>> getClients()
        {
            return new ResponseEntity<>(clientService.getClients(),HttpStatus.OK);
        }
    }
