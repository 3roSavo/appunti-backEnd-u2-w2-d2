package savogineros.webserviceu2w2d2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import savogineros.webserviceu2w2d2.entities.User;
import savogineros.webserviceu2w2d2.exceptions.NotFoundException;

import java.util.*;

@Service
public class UsersService {

   List<User> usersList = new ArrayList<>(); // In assenza di DB collegato mi creo qui una lista con cui interagire

   // GET (userList)
    public List<User> getUsersList() {
        return this.usersList;
    }

    // POST
    public User saveUser(User body) {
        Random rndm = new Random();
        body.setId(rndm.nextInt(1,10000));
        usersList.add(body);
        return body;
    }

    // GET (findById)
    public User findById(int id) {
        User found = null;
        //return usersList.stream().filter(user -> user.getId() == id).toList().get(0);
        // metodo orrendo, perché devo forzare a tornarmi uno user col filter, di solito torna una list
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getId() == id) {
                return usersList.get(i);
            }
        }
        throw new RuntimeException("Elemento con id " + id + " non presente");
        //throw new NotFoundException(id); // eccezione gestita con classe apposita
        // DA RICONTROLLARE PERCHE' HA FATTO UN CASINO NELL'ESERCIZIO
    }

    // DELETE (findById and delete)
    public void findByIdAndDelete(int id) {
        Iterator<User> iterator = this.usersList.iterator();
        while (iterator.hasNext()) {
            User current = iterator.next();                // codice incomprensibile boh
            if (current.getId() == id) {                   // con le modifiche delle liste DEVI utilizzare Iterator
                iterator.remove();                         // con modifiche si intende la dimensione della lista, quindi
            }                                              // se aggiungi o elimini elementi
        }
    }

    // PUT (findById and update)
    public User findByIdAndUpdate(int id, User body) {
        User found = null;
        for (User user : this.usersList) {  // codice del tutto incomprensibile
            if (user.getId() == id) {
                found = user;
                found.setId(id);
                found.setName(body.getName());
                found.setSurname(body.getSurname());
            }
        }
        if (found == null)
            throw new NotFoundException(id);
        return found;
    }



}
