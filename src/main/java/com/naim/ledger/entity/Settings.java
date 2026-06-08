package com.naim.ledger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Settings {

    @Id
    private long id = 1L;
    private String businessName;
    private String ownerName;
    private String mobile;
    private String address;


}
