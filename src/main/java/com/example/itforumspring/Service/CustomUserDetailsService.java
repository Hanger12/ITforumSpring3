package com.example.itforumspring.Service;
import com.example.itforumspring.repositories.UserRepository;
import com.example.itforumspring.repositories.RoleRepository;
import com.example.itforumspring.bdclass.Role;
import com.example.itforumspring.bdclass.Users;

import java.util.*;

import com.example.itforumspring.repositories.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepositoryCustom userRepositorycustom;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public List<Users> findAllUsers(){return userRepository.findAll();}
    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Users findUserByID(long id) {return userRepository.findById(id);}
    public void saveUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setId(userRepositorycustom.getMaxId()+1);
        Role userRole = roleRepository.findByRole("USER");
        user.setRoles(new HashSet<>(List.of(userRole)));
        userRepository.save(user);
    }
    public void deleteUser(Users user)
    {
        userRepository.delete(user);
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if(user != null) {
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserForAuthentication(user, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }
    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return new ArrayList<>(roles);
    }
    private UserDetails buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
