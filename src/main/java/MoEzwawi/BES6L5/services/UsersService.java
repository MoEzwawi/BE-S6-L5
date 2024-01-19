package MoEzwawi.BES6L5.services;

import MoEzwawi.BES6L5.entities.User;
import MoEzwawi.BES6L5.exceptions.BadRequestException;
import MoEzwawi.BES6L5.exceptions.UserNotFoundException;
import MoEzwawi.BES6L5.payloads.UserRequestDTO;
import MoEzwawi.BES6L5.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    public Page<User> getUsers(int pageN,int size, String orderBy){
        Pageable pageable = PageRequest.of(pageN,size, Sort.by(orderBy));
        return usersRepository.findAll(pageable);
    }
    public User save(UserRequestDTO body){
        if(usersRepository.existsByEmail(body.email()) || usersRepository.existsByUsername(body.username())){
            throw new BadRequestException("User already present");
        }
        User newUser = new User(body.username(), body.name(), body.surname(), body.email());
        System.out.println(" ------------- User saved correctly ---------------");
        System.out.println(newUser);
        return usersRepository.save(newUser);
    }
    public User findById(long id) throws UserNotFoundException {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) return userOptional.get();
        else throw new UserNotFoundException(id);
    }
    public void deleteById(long id){
        usersRepository.deleteById(id);
        System.out.println("User correctly deleted from db");
    }
    public User patchUser(long id, UserRequestDTO partialBody){
        User found = this.findById(id);
        if(partialBody.name()!=null) found.setName(partialBody.name());
        if(partialBody.surname()!=null) found.setSurname(partialBody.surname());
        if(partialBody.em()!=null) found.setEmail(partialBody.getEmail());
        if(partialBody.getAvatarUrl()!=null) found.setAvatarUrl(partialBody.getAvatarUrl());
        return found;
    }
    public long count(){
        return usersRepository.count();
    }
}