package com.training.service;


import com.training.dto.UserDTO;
import com.training.dto.UserResponseDTO;
import com.training.entity.User;
import com.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;

    public UserResponseDTO createUser(UserDTO dto)
    {
        User user=modelMapper.map(dto,User.class);

//        Default role
        user.setRole("ROLE_USER");
        User savedUser=repository.save(user);

        return modelMapper.map(savedUser, UserResponseDTO.class);
    }
}
