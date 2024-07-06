package com.tutorial.authservice.entity;

import com.tutorial.authservice.enums.RoleName;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    public Role(){}

    public Role(RoleName roleName) {
        this.roleName =roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }
}
