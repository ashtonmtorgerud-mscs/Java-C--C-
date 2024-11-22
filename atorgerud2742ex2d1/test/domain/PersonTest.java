package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person = null;

    @BeforeEach
    void setUp() {
        this.person = new Person();
    }

    @Test
    void setPersonIdBelowRange() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setPersonId(100));
    }

    @Test
    void setPersonIdMinimum() {
        assertDoesNotThrow(() -> this.person.setPersonId(101));
    }

    @Test
    void setPersonIdMaximum() {
        assertDoesNotThrow(() -> this.person.setPersonId(999));
    }

    @Test
    void setPersonIdAboveRange() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setPersonId(1000));
    }

    @Test
    void setFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setFirstName(null));
    }

    @Test
    void setFirstNameBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setFirstName("Aa"));
    }

    @Test
    void setFirstNameMinimum() {
        assertDoesNotThrow(() -> this.person.setFirstName("Aaa"));
    }

    @Test
    void setFirstNameMaximum() {
        assertDoesNotThrow(() -> this.person.setFirstName("Aaaaaaaaaaaaaaa"));
    }

    @Test
    void setFirstNameAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setFirstName("Aaaaaaaaaaaaaaaa"));
    }

    @Test
    void setLastNameNull() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setLastName(null));
    }

    @Test
    void setLastNameBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setLastName("A"));
    }

    @Test
    void setLastNameMinimum() {
        assertDoesNotThrow(() -> this.person.setLastName("Aa"));
    }

    @Test
    void setLastNameMaximum() {
        assertDoesNotThrow(() -> this.person.setLastName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setLastNameAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setLastName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setUserNameNull() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName(null));
    }

    @Test
    void setUserNameBelowMinimum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName("Aaaa"));
    }

    @Test
    void setUserNameMinimum() {
        assertDoesNotThrow(() -> this.person.setUserName("Aaaaa"));
    }

    @Test
    void setUserNameMaximum() {
        assertDoesNotThrow(() -> this.person.setUserName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setUserNameAboveMaximum() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void setUserNameAdmin() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName("ADMIN"));
    }

    @Test
    void setUserNameAdministrator() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName("ADMINISTRATOR"));
    }

    @Test
    void setUserNameSupervisor() {
        assertThrows(IllegalArgumentException.class, () -> this.person.setUserName("SUPERVISOR"));
    }
}