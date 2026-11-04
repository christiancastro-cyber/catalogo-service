package com.dorcas.catalog.workshop.infrastructure.web;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class WorkshopApiTest {

    @Autowired
    MockMvc mockMvc;

    private String student(String name) {
        return """
                {"student": "%s"}
                """.formatted(name);
    }

    private Long enrollAndReturnId(Long workshopId, String name) throws Exception {
        String response = mockMvc.perform(post("/api/workshops/{id}/bookings", workshopId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student(name)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return JsonPath.parse(response).read("$.id", Long.class);
    }

    @Test
    void listReturnsTheFourWorkshops() throws Exception {
        mockMvc.perform(get("/api/workshops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4));
    }

    @Test
    void filteringByLevelReturnsOnlyThatLevel() throws Exception {
        mockMvc.perform(get("/api/workshops").param("level", "BEGINNER"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].level", everyItem(is("BEGINNER"))));
    }

    @Test
    void gettingAWorkshopThatDoesNotExistReturns404() throws Exception {
        mockMvc.perform(get("/api/workshops/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("WORKSHOP_NOT_FOUND"));
    }

    @Test
    void enrollingAStudentReturns201WithTheBooking() throws Exception {
        mockMvc.perform(post("/api/workshops/1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student("Mara")))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.student").value("Mara"))
                .andExpect(jsonPath("$.workshopId").value(1))
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    void enrollingTheSameStudentTwiceReturns409() throws Exception {
        enrollAndReturnId(1L, "Mara");

        mockMvc.perform(post("/api/workshops/1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student("Mara")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("STUDENT_ALREADY_ENROLLED"));
    }

    @Test
    void enrollingWithoutANameReturns400() throws Exception {
        mockMvc.perform(post("/api/workshops/1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student("")))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_DATA"));
    }

    @Test
    void enrollingInAFullWorkshopReturns409() throws Exception {
        for (String name : new String[]{"Ana", "Lucía"}) {
            enrollAndReturnId(4L, name);
        }

        mockMvc.perform(post("/api/workshops/4/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student("Pilar")))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("WORKSHOP_FULL"));
    }

    @Test
    void cancellingABookingRemovesItFromTheList() throws Exception {
        Long bookingId = enrollAndReturnId(1L, "Mara");

        mockMvc.perform(delete("/api/workshops/1/bookings/{bookingId}", bookingId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/workshops/1/bookings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void cancellingABookingThatDoesNotExistReturns404() throws Exception {
        mockMvc.perform(delete("/api/workshops/1/bookings/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("BOOKING_NOT_FOUND"));
    }
}
