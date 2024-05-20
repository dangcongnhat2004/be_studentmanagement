package com.example.be.service;

import com.example.be.Dto.UsersDto;
import com.example.be.entity.Users;
import com.example.be.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public UsersDto getUserDetailById(int userId) {
        Users user = usersRepository.findById(userId).orElse(null);
        if (user != null) {
            UsersDto usersDto = new UsersDto();
            usersDto.setId(user.getId());
            usersDto.setUserName(user.getUserName());
            usersDto.setName(user.getName());
            usersDto.setAddress(user.getAddress());
            usersDto.setSchoolName(user.getSchoolname());
            usersDto.setClassName(user.getClasses() != null ? user.getClasses().getName() : null);
            usersDto.setPasswordHash(user.getPasswordhash());
            usersDto.setRoles(user.getRoles().toString());
            usersDto.setStatusNow(user.getStatusNow().toString());
            return usersDto;
        } else {
            return null;
        }
    }
}
