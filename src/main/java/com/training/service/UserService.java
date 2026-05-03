package com.training.service;


import com.training.dto.UserDTO;
import com.training.dto.UserResponseDTO;
import com.training.entity.User;
import com.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserDTO dto)
    {
        User user=modelMapper.map(dto,User.class);

//        Default role
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User savedUser=repository.save(user);

        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));
    }
}
