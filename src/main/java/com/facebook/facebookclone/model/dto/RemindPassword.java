package com.facebook.facebookclone.model.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemindPassword {

    private String mail;
    private String password;
}
