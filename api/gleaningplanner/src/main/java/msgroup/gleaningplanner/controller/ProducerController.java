package msgroup.gleaningplanner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import msgroup.gleaningplanner.controller.TransferObject.ProducerTO;
import msgroup.gleaningplanner.controller.TransferObject.VolunteerTO;
import msgroup.gleaningplanner.model.Producer;
import msgroup.gleaningplanner.model.Volunteer;
import msgroup.gleaningplanner.service.ProducerService;
import msgroup.gleaningplanner.controller.TransferObject.AcceptenceTO;
import msgroup.gleaningplanner.controller.TransferObject.ProducerFilterTO;

@RestController
public class ProducerController {
    private ProducerService producerService;

    public ProducerController(ProducerService producerService){
        this.producerService = producerService;
    }

    @PostMapping("/producer/register")
    public ResponseEntity<ProducerTO> createProducer(@RequestBody ProducerTO incoming){
        Producer newProducer = producerService.createProducer(incoming.firstName, incoming.lastName, incoming.email, 
            incoming.username, incoming.password, incoming.phoneNumber, incoming.address, incoming.postalCode, incoming.city);

        ProducerTO out = new ProducerTO(newProducer.getID(), newProducer.getUsername(), newProducer.getFirstName(), 
        newProducer.getLastName(), newProducer.getEmail(), newProducer.getPhoneNumber(), null, newProducer.getCity(),
        newProducer.getAddress(), newProducer.getPostalCode(), newProducer.getLatitude(), newProducer.getLongitude());

        return new ResponseEntity<ProducerTO>(out, HttpStatus.OK);
    }

    @PutMapping("/producer/update")
    public ResponseEntity<ProducerTO> updateProducer(@RequestBody ProducerTO incoming) {

        Producer newProducer = producerService.updateProducer(  
            incoming.id,
            incoming.firstName,
            incoming.lastName,
            incoming.email,
            incoming.username,
            incoming.password,
            incoming.phoneNumber,
            incoming.address,
            incoming.postalCode,
            incoming.city
        );
        ProducerTO out = new ProducerTO(
            newProducer.getID(),
            newProducer.getUsername(), 
            newProducer.getFirstName(), 
            newProducer.getLastName(), 
            newProducer.getEmail(), 
            newProducer.getPhoneNumber(), 
            null, 
            newProducer.getCity(),
            newProducer.getAddress(), 
            newProducer.getPostalCode(), 
            newProducer.getLatitude(), 
            newProducer.getLongitude()
        );
        
        return new ResponseEntity<ProducerTO>(out, HttpStatus.OK);                    
                                                    
    }

    @PostMapping("/producer/get-by-filter")
    public ResponseEntity<ProducerFilterTO> getProducerByFilter(@RequestBody ProducerTO incoming){
        Set<Producer> filteredProducers = this.producerService.filterProducers(
            incoming.id,
            incoming.firstName,
            incoming.lastName,
            incoming.email,
            incoming.username,
            incoming.password,
            incoming.phoneNumber,
            incoming.address,
            incoming.postalCode,
            incoming.city
        );

        List<ProducerTO> producerTOs = new ArrayList<ProducerTO>();
        for (Producer producer : filteredProducers) {
            ProducerTO to = new ProducerTO(
                producer.getID(),
                producer.getFirstName(),
                producer.getLastName(),
                producer.getEmail(),
                producer.getUsername(),
                producer.getPassword(),
                producer.getPhoneNumber(),
                producer.getAddress(),
                producer.getPostalCode(),
                producer.getCity(),
                producer.getLatitude(),
                producer.getLongitude()
            );
            producerTOs.add(to);
        }

        return new ResponseEntity<ProducerFilterTO>(new ProducerFilterTO(producerTOs), HttpStatus.OK);
    }


    @PostMapping("/producer/accept/volunteer-group")
    public ResponseEntity<AcceptenceTO> acceptVolunteerGroup(@RequestBody AcceptenceTO incoming) {
        List<Volunteer> accpetedVolunteers = producerService.acceptVolunteerGroup(
            incoming.getEventID(), 
            incoming.getGroupNumber()
        );

        List<VolunteerTO> out = new ArrayList<VolunteerTO>();
        for (Volunteer volunteer : accpetedVolunteers) {
            out.add(new VolunteerTO(
                volunteer.getID(), 
                volunteer.getUsername(), 
                volunteer.getFirstName(), 
                volunteer.getLastName(), 
                volunteer.getEmail(), 
                volunteer.getPhoneNumber(), 
                volunteer.getPostalCode(), 
                volunteer.getAddress(), 
                volunteer.getCity(), 
                volunteer.getLatitude(), 
                volunteer.getLongitude(), 
                null
            ));
        }

        return new ResponseEntity<AcceptenceTO>(
            new AcceptenceTO(
                incoming.getEventID(), 
                incoming.getGroupNumber(), 
                out
            ), 
            HttpStatus.OK
        );
    }

}