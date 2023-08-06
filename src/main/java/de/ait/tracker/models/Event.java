package de.ait.tracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String title;

    private String description;

    private  Integer maxParticipantsCount;

    private String feedbackFromAdmin;

    @ManyToOne
    @JoinColumn(name = "about_id")
    private User about;
}
