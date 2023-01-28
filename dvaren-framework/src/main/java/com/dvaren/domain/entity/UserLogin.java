package com.dvaren.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLogin implements Serializable {

    String username;

    String password;
}
