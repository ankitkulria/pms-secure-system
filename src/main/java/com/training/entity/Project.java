package com.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

//    related with user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    related with task
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks;

}
