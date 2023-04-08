package com.example.CookingBook.web;

import com.example.CookingBook.enums.Difficulty;
import com.example.CookingBook.models.DTO.ReceiptAddDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReceiptControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithAnonymousUser
    void getAddRecipe_byAnonymous () throws Exception {

        mockMvc.perform(get("/receipes/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }


    @Test
    @WithUserDetails(
            value = "goshko@abv.bg",
            userDetailsServiceBeanName = "userDetailsService",
            setupBefore = TestExecutionEvent.TEST_EXECUTION
    )
    void postAddRecipe_byLoggedUserWithCompleteData_returnsCreated () throws Exception {

        ReceiptAddDTO receiptAddDTO = new ReceiptAddDTO().setPhoto(new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        });

        mockMvc.perform(post("/product/add").param("productName", "tomato")
                        .param("category", "FRUITS").with(csrf())
                )
                .andExpect(redirectedUrlPattern("/product/view/{[0-9]*}"));

        mockMvc.perform(post("/product/add").param("productName", "potato")
                        .param("category", "FRUITS").with(csrf())
                )
                .andExpect(redirectedUrlPattern("/product/view/{[0-9]*}"));

        mockMvc.perform(post("/receipes/add")
                        .param("name", "TestNAme")
                        .param("cooking_time", "14")
                        .param("prep_time", "30")
                        .param("servings", "12")
                        .param("description", "test description")
                        .param("preparation", "test preparation")
                        .param("difficulty", "EASY")
                        .param("ingredientList[0].productName", "tomato")
                        .param("ingredientList[0].quantity", "123")
                        .param("ingredientList[0].measureUnit", "GRAM")
                        .param("ingredientList[1].productName", "potato")
                        .param("ingredientList[1].quantity", "50")
                        .param("ingredientList[1].measureUnit", "GRAM")
                        .flashAttr("receiptAddDTO", receiptAddDTO)
                        .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attributeCount(0))
                .andExpect(view().name("redirect:/receipes/view_user_recipes"));
    }

}
