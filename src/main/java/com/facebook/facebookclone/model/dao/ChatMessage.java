package com.facebook.facebookclone.model.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatMessage {

    @GeneratedValue
    @Id
    private Long id;


    private String value;
}
