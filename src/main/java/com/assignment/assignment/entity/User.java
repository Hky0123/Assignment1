package com.assignment.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="userDetail")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userName;
    @Id
    private String userAccountId;


}
