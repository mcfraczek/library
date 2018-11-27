package com.project.library.b_b_service;

import com.project.library.a_entity.Address;
import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.b_a_DAO.AddressDEO;
import com.project.library.b_a_DAO.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private UserDAO userDAO;
    private BookService bookService;
    private AddressDEO addressDEO;

    public List<User> getUsers(@RequestParam("name") String name, @RequestParam("surname") String surname) {
        List<User> userList;
        if (name.isEmpty()) {
            userList = userDAO.findUserBySurname(surname);
        } else if (surname.isEmpty()) {
            userList = userDAO.findUserByName(name);
        } else {
            userList = userDAO.findUserByNameAndSurname(name, surname);
        }
        return userList;
    }

    public boolean checkIfHeHasThatBook(Optional<User> user, int borrowedId) {
        return user.map(x -> x.getBookList()
                .stream()
                .filter(b -> b.getId() == borrowedId)
                .count() > 0).orElse(false);
    }

    @Transactional
    public void setBook(int borrowedId, Optional<User> user) {
        Optional<Book> book = bookService.findBookById(borrowedId);
        if (book.isPresent() && user.isPresent()) {
            user.get().addBook(book.get());
            book.get().setDate(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            book.get().setUser(user.get());
        }
    }

    public void saveUser(User user) {
        String street = user.getUserDetails().getAddress().getStreet();
        String streetNr = user.getUserDetails().getAddress().getStreetNumber();
        String apartmentNumber = user.getUserDetails().getAddress().getApartmentNumber();
        String county = user.getUserDetails().getAddress().getCounty();
        String postalCode = user.getUserDetails().getAddress().getPostalCode();
        String city = user.getUserDetails().getAddress().getCity();

        Optional<Address> addressOptional = addressDEO.findAddressByStreetAndStreetNumberAndApartmentNumberAndCountyAndPostalCodeAndCity(street, streetNr, apartmentNumber, county, postalCode, city);

        if (addressOptional.isPresent()) {
            user.getUserDetails().setAddress(addressOptional.get());
        }
        userDAO.save(user);
    }

    public Optional<User> findById(int userId) {
        return userDAO.findById(userId);
    }
}
