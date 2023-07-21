package com.example.desafiomobile;

import org.junit.Test;

import static org.junit.Assert.*;

import Model.Coordinates;
import Model.DateOfBirth;
import Model.Location;
import Model.Login;
import Model.Name;
import Model.Picture;
import Model.Street;
import Model.Timezone;
import Model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testCoordinates() {
        // Crie uma instância de Coordinates com os dados de teste
        Coordinates coordinates = new Coordinates("12.345", "67.890");

        // Execute os métodos a serem testados
        assertEquals("12.345", coordinates.getLatitude());
        assertEquals("67.890", coordinates.getLongitude());
    }

    @Test
    public void testDateOfBirth() {
        // Crie uma instância de DateOfBirth com os dados de teste
        DateOfBirth dateOfBirth = new DateOfBirth("1990-01-01");

        // Execute o método a ser testado
        assertEquals("1990-01-01", dateOfBirth.getDate());
    }

    @Test
    public void testLocation() {
        // Crie uma instância de Location com os dados de teste
        Street street = new Street(123, "Main St");
        Coordinates coordinates = new Coordinates("12.345", "67.890");
        Timezone timezone = new Timezone("+00:00", "GMT");
        Location location = new Location(street, "City", "State", "12345", coordinates, timezone);

        // Execute o método a ser testado
        assertEquals("123 Main St, City, State 12345", location.getFullAddress());
    }

    @Test
    public void testLogin() {
        // Crie uma instância de Login com os dados de teste
        Login login = new Login("123456789", "password123");

        // Execute os métodos a serem testados
        assertEquals("123456789", login.getUuid());
        assertEquals("password123", login.getPassword());
    }

    @Test
    public void testName() {
        // Crie uma instância de Name com os dados de teste
        Name name = new Name("Mr", "John", "Doe");

        // Execute o método a ser testado
        assertEquals("Mr John Doe", name.getFullName());
    }

    @Test
    public void testPicture() {
        // Crie uma instância de Picture com os dados de teste
        Picture picture = new Picture("large_url", "medium_url", "thumbnail_url");

        // Execute o método a ser testado
        assertEquals("large_url", picture.getLarge());
    }

    @Test
    public void testUser() {
        // Crie uma instância de User com os dados de teste
        Name name = new Name("Mr", "John", "Doe");
        Street street = new Street(123, "Main St");
        Coordinates coordinates = new Coordinates("12.345", "67.890");
        Timezone timezone = new Timezone("+00:00", "GMT");
        Location location = new Location(street, "City", "State", "12345", coordinates, timezone);
        DateOfBirth dateOfBirth = new DateOfBirth("1990-01-01");
        Login login = new Login("123456789", "password123");
        Picture picture = new Picture("large_url", "medium_url", "thumbnail_url");
        User user = new User(name, location, dateOfBirth, login, picture, "email@example.com", "1234567890");

        // Execute os métodos a serem testados
        assertEquals(name, user.getName());
        assertEquals(location, user.getLocation());
        assertEquals(dateOfBirth, user.getDateOfBirth());
        assertEquals(login, user.getLogin());
        assertEquals(picture, user.getPicture());
        assertEquals("email@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
    }
}