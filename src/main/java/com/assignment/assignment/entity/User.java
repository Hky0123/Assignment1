package com.assignment.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="userDetail")
public class User {
    private String userName;
    @Id
    private String userAccountId;
}
