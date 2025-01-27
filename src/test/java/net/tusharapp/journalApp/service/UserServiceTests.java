package net.tusharapp.journalApp.service;

import net.tusharapp.journalApp.entity.User;
import net.tusharapp.journalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    //below annotations are used to setup or do operations before or after test cases
//    @BeforeAll , @BeforeEach  , @BeforeAll  , @AfterEach , @AfterAll
//    void setup(){
//
//    }


    //WITH @ArgumentSource
    @Disabled  //if we have to disable this test for now
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)   //if we have to pass argument from the class
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "shyam",
//            "vipul"
// //    @ValueSource(Strings = {
// //            "ram",
// //            "shyam",
// //            "vipul"
// //    })
//    public void testSaveNewUser (String name) {
//        assertNotNull(userRepository.findByUserName(name));
//    }

//    @Test
//    public void testSaveNewUser() {
//        User user = userRepository.findByUserName("ram");
//        assertTrue(!user.getJournalEntries().isEmpty());
//    }


    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

}
