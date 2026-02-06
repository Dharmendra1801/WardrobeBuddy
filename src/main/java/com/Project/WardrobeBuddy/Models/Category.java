package com.Project.WardrobeBuddy.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String categoryName;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
