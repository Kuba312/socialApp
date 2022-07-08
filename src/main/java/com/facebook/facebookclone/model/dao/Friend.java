package com.facebook.facebookclone.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    private Long id;

    private boolean accepted;



    @ManyToOne()
    private User user1;

    
    @ManyToOne
    private User user2;


}
