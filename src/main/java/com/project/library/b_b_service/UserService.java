package com.project.library.b_b_service;

import com.project.library.a_entity.Address;
import com.project.library.a_entity.Book;
import com.project.library.a_entity.User;
import com.project.library.ab_helperBackingBeans.methods.DateChecking;
import com.project.library.b_a_DAO.AddressDEO;
import com.project.library.b_a_DAO.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            userList = userDAO.findUserBySurnameIgnoreCaseOrderBySurname(surname);
        } else if (surname.isEmpty()) {
            userList = userDAO.findUserByNameIgnoreCaseOrderBySurname(name);
        } else {
            userList = userDAO.findUserByNameAndSurnameIgnoreCaseOrderBySurname(name, surname);
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
            book.get().setDate(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
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

        Optional<Address> addressOptional = addressDEO.findAddressByStreetAndStreetNumberAndApartmentNumberAndCountyAndPostalCodeAndCityIgnoreCase(street, streetNr, apartmentNumber, county, postalCode, city);

        if (addressOptional.isPresent()) {
            user.getUserDetails().setAddress(addressOptional.get());
        }
        userDAO.save(user);
    }

    public Optional<User> findById(int userId) {
        return userDAO.findById(userId);
    }

    @Transactional
    public void returnBook(int returnedId, int userId) {
        Optional<User> optionalUser = userDAO.findById(userId);
        Optional<Book> optionalBook = bookService.findBookById(returnedId);
        if (optionalUser.isPresent() && optionalBook.isPresent()) {
            User user1 = optionalUser.get();
            Book book = optionalBook.get();
            user1.getBookList().remove(book);
            book.setUser(null);
            book.setDate(null);
            userDAO.save(user1);
            bookService.saveBook(book);
        }
    }

    public Optional<User> getUserFromIdAndBorrowBook(int borrowedId, int userId) {
        Optional<User> user = findById(userId);
        boolean heHas = checkIfHeHasThatBook(user, borrowedId);
        if (!heHas) {
            setBook(borrowedId, user);
        }
        return user;
    }

    public void deleteUser(int userId) {
        Optional<User> userOptional = userDAO.findById(userId);
        if (userOptional.isPresent()) {
            User user1 = userOptional.get();
            user1.setBookList(null);
            userDAO.delete(user1);
        }
    }

    @Transactional
    public void prolongBook(int returnedId, int userId) {
        Optional<Book> bookOptional = bookService.findBookById(returnedId);
        Optional<User> userOptional = userDAO.findById(userId);

        if (bookOptional.isPresent() && userOptional.isPresent()) {
            Book book = bookOptional.get();
            User user = userOptional.get();
            String newDate = DateChecking.prolongBook(book.getDate());
            book.setDate(newDate);
        }
    }

    public boolean userDontExistInDb(User user) {
        Optional<User> userOptional = userDAO.findUserByPESEL(user.getPESEL());
        if (userOptional.isPresent()) {
            return false;
        }
        return true;
    }

    public boolean userDontHaveBooks(int userId) {
        Optional<User> optionalUser = userDAO.findById(userId);
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            if (user1.getBookList().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public void forceDeleteUser(int userId) {
        Optional<User> optionalUser = userDAO.findById(userId);
        if (optionalUser.isPresent()) {
            User user1 = optionalUser.get();
            List<Book> bookList = user1.getBookList();
            for (Book book : bookList) {
                book.setUser(null);
            }
            userDAO.delete(user1);
        }
    }
}
