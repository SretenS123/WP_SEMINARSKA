package com.example.seminarksa_wp.config;

import com.example.seminarksa_wp.model.Event;
import com.example.seminarksa_wp.model.User;
import com.example.seminarksa_wp.model.Venue;
import com.example.seminarksa_wp.model.enumerations.EventType;
import com.example.seminarksa_wp.model.enumerations.Role;
import com.example.seminarksa_wp.service.EventService;
import com.example.seminarksa_wp.service.TicketService;
import com.example.seminarksa_wp.service.UserService;
import com.example.seminarksa_wp.service.VenueService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class DataInitializer {

    private final UserService userService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final VenueService venueService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, TicketService ticketService, EventService eventService, VenueService venueService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.eventService = eventService;
        this.venueService = venueService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData(){

        User user1 = this.userService.register("Sreten","Strezovski",
                "075491218",
                "strezovskisreten@gmail.com",
                "sreten",
                "sreten",
                "Macedonia", Role.ROLE_ADMIN);
        User user2 = this.userService.register("Ivana","Taleska",
                "075491218",
                "ivanataleska@gmail.com",
                "ivana",
                "ivana",
                "Macedonia", Role.ROLE_MANAGER);
        User user3 = this.userService.register("Ariton","Verus",
                "075491218",
                "aritonverus@gmail.com",
                "ariton",
                "ariton",
                "Macedonia", Role.ROLE_USER);

        Venue venue1 = this.venueService.create("EPICENTAR",150,"https://mkc.mk/wp-content/uploads/2021/02/%D0%BC%D0%BA%D1%86-%D0%BF%D0%B0%D1%80%D0%BA.jpg",EventType.NIGHTCLUB);
        Venue venue2 = this.venueService.create("HAVANA",200,"https://mkc.mk/wp-content/uploads/2021/02/%D0%BC%D0%BA%D1%86-%D0%BF%D0%B0%D1%80%D0%BA_1.jpg",EventType.NIGHTCLUB);
        Venue venue3 = this.venueService.create("CUBALIBRE",100,"https://mkc.mk/wp-content/uploads/2020/02/%D0%BA%D0%B8%D0%BD%D0%BE-%D0%A4%D1%80%D0%BE%D1%81%D0%B8%D0%BD%D0%B0-8-1.jpg",EventType.BAR);
        Venue venue4 = this.venueService.create("INCLUSIVE",500,"https://mkc.mk/wp-content/uploads/2020/02/%D0%9F%D0%BB%D0%B0%D1%82%D0%BE_2.jpg",EventType.NIGHTCLUB);
        Event event1 = this.eventService.create("Event1","najak event",
                "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/14/cd/73/47/cristian-varela.jpg?w=1200&h=1200&s=1",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,9,28),
                venue1.getId(),200,300);
        Event event2 = this.eventService.create("Event2","najak event",
                "https://visitohrid.mk/wp-content/uploads/2019/01/1dsc_0064_images_thumb_medium730_0.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,6,18),
                venue2.getId(),300,600);
        Event event3 = this.eventService.create("Event3","najak event",
                "https://media-cdn.tripadvisor.com/media/photo-s/13/7f/29/84/18-years-cuba-libre.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,5,30),
                venue3.getId(),400,100);
        Event event4 = this.eventService.create("Event4","najak event",
                "https://inclusive.mk/wp-content/uploads/2021/12/inclusive-pocetna.jpg",
                EventType.NIGHTCLUB,
                LocalDate.of(2022,4,15),
                venue4.getId(),800,200);


    }
}
