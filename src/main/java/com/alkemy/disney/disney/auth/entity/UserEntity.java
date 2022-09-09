package com.alkemy.disney.disney.auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="user")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Email
    private String username;
    @Size(min=8)
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialNonExpired;
    private boolean enabled;

    public UserEntity(){

        this.accountNonExpired=true;
        this.accountNonLocked=true;
        this.credentialNonExpired=true;
        this.enabled=true;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String str)
    {
        this.username=str;
    }

    public void setPassword(String str)
    {
        this.password=str;
    }
    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
