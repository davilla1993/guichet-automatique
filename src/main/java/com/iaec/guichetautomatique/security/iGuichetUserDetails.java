package com.iaec.guichetautomatique.security;

import com.iaec.guichetautomatique.entities.Role;
import com.iaec.guichetautomatique.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class iGuichetUserDetails implements UserDetails {

    private User user;

    public iGuichetUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getFullname() {
        return this.user.getNom() + " " + this.user.getPrenom();
    }

    public void setFirstName(String firstName) {
        this.user.setPrenom(firstName);
    }

    public void setLastName(String lastName) {
        this.user.setNom(lastName);
    }



    public boolean hasRole(String roleName){
        return user.hasRole(roleName);
    }
}
