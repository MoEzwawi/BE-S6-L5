package MoEzwawi.BES6L5.services;

import MoEzwawi.BES6L5.entities.User;
import MoEzwawi.BES6L5.exceptions.BadRequestException;
import MoEzwawi.BES6L5.exceptions.UserNotFoundException;
import MoEzwawi.BES6L5.payloads.UserRequestDTO;
import MoEzwawi.BES6L5.repositories.UsersRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Cloudinary cloudinaryUploader;
    private boolean isTheEmailAlreadyInUse(String email){
        return this.usersRepository.existsByEmail(email);
    }
    private boolean isUsernameAlreadyPresent(String username){
        return this.usersRepository.existsByUsername(username);
    }
    public Page<User> getUsers(int pageN,int size, String orderBy){
        Pageable pageable = PageRequest.of(pageN,size, Sort.by(orderBy));
        return usersRepository.findAll(pageable);
    }
    public User save(UserRequestDTO body){
        if(this.isTheEmailAlreadyInUse(body.email()) || this.isUsernameAlreadyPresent(body.username())){
            throw new BadRequestException("User already present");
        }
        User newUser = new User(body.username(), body.name(), body.surname(), body.email());
        System.out.println(" ------------- User saved correctly ---------------");
        System.out.println(newUser);
        return usersRepository.save(newUser);
    }
    public User findById(long id){
        return this.usersRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
    public void deleteById(long id){
        usersRepository.deleteById(id);
        System.out.println("User correctly deleted from db");
    }
    public User updateUser(long id, UserRequestDTO partialBody){
        User found = this.findById(id);
        if(partialBody.name()!=null) found.setName(partialBody.name());
        if(partialBody.surname()!=null) found.setSurname(partialBody.surname());
        if(!this.isTheEmailAlreadyInUse(partialBody.email())) {
            if(partialBody.email()!=null) found.setEmail(partialBody.email());
        }
        if(!this.isUsernameAlreadyPresent(partialBody.username())){
            if(partialBody.username()!=null) found.setUsername(partialBody.email());
        }
        return found;
    }
    public User updateProfilePic(long id, MultipartFile file) throws IOException {
        User found = this.findById(id);
        String url = (String) cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");
        found.setProfilePicUrl(url);
        return found;
    }
}