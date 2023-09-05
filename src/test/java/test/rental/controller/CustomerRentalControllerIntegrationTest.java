package test.rental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import test.rental.domain.Movie;
import test.rental.domain.MovieType;
import test.rental.domain.Rental;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWebTestClient
class CustomerRentalControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addRentals_should_create_customer_and_rentals() throws Exception {
        var customerName = "JOHN DOE";
        var rentals = Arrays.asList(
                new Rental(new Movie("Die Hard", MovieType.REGULAR), 2),
                new Rental(new Movie("Kids party", MovieType.CHILDREN), 3)
        );

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(String.format("/customers/%S/rentals", customerName))
                                .content(objectMapper.writeValueAsString(rentals))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(customerName))
                .andExpect(jsonPath("$.frequentRentalPoints").value(0));
    }

    @Test
    public void addRentals_on_movie_with_no_name_should_fail() throws Exception {
        var customerName = "JOHN DOE";
        var rentals = Collections.singletonList(new Rental(new Movie(" ", MovieType.REGULAR), 2));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(String.format("/customers/%S/rentals", customerName))
                                .content(objectMapper.writeValueAsString(rentals))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRentals_on_movie_with_rental_period_0_should_fail() throws Exception {
        var customerName = "JOHN DOE";
        var rentals = Collections.singletonList(new Rental(new Movie("Die Hard", MovieType.REGULAR), 0));

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(String.format("/customers/%S/rentals", customerName))
                                .content(objectMapper.writeValueAsString(rentals))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRentals_on_unknown_movie_type_should_fail() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(String.format("/customers/%s/rentals", "JOHN"))
                                .content("[{\"movie\":{\"title\":\"Die Hard\", \"movieType\":\"UNKNOWN\"}, \"daysRented\":3}]")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getStatement_on_customer_rentals_should_return_statement() throws Exception {
        var customerName = "JOHN DOE";
        var rentals = Arrays.asList(
                new Rental(new Movie("Die Hard", MovieType.REGULAR), 2),
                new Rental(new Movie("Kids party", MovieType.CHILDREN), 3),
                new Rental(new Movie("Harry Potter 10", MovieType.NEW_RELEASE), 5),
                new Rental(new Movie("Classic Gold", MovieType.CLASSIC), 2)
        );

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(String.format("/customers/%S/rentals", customerName))
                                .content(objectMapper.writeValueAsString(rentals))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(String.format("/customers/%S/rentals/statements", customerName))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(customerName))
                .andExpect(jsonPath("$.rentAmount.amount").value(20.50))
                .andExpect(jsonPath("$.rentAmount.currencyCode").value("USD"))
                .andExpect(jsonPath("$.frequentRentalPoints").value(3));
    }

    @Test
    public void getStatement_without_customer_should_fail() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get(String.format("/customers/%S/rentals/statements", "Unknown Customer"))
                )
                .andExpect(status().isNotFound());
    }
}