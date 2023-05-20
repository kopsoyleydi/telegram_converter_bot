package com.example.telerambot.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Entity
@Table(name = "app-table")
@AllArgsConstructor
@NoArgsConstructor
public class AppRepos {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ad")
    private String ad;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
