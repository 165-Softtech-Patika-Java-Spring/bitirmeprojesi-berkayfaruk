package com.example.softtectfinal.app.use.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USE_USER")
@Data
public class UseUser extends BaseEntity{
    @Id
    @SequenceGenerator(name = "UseUser" , sequenceName = "USE_USER_ID_SEQ")
    @GeneratedValue(generator = "UseUser")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "SURNAME", length = 100, nullable = false)
    private String surname;

    @Column(name = "IDENTITY_NO", nullable = false)
    private Long identityNo;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
