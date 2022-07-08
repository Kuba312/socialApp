package com.facebook.facebookclone.model.dto;

import com.facebook.facebookclone.model.dao.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {


    private Long id;

    private String productName;

    private String lastModifiedBy;


    private String createdBy;


    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    private Double price;

    private Long quantity;

    @JsonProperty("addedBy")
    private UserDto userDto;

}
