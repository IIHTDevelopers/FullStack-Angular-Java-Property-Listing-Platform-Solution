package com.example.propertylist.exception;

import com.example.propertylist.controller.PropertyController;
import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.service.PropertyService;
import com.example.propertylist.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.example.propertylist.utils.MasterData.getPropertyDTO;
import static com.example.propertylist.utils.TestUtils.currentTest;
import static com.example.propertylist.utils.TestUtils.exceptionTestFile;
import static com.example.propertylist.utils.TestUtils.testReport;
import static com.example.propertylist.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(PropertyController.class)
@AutoConfigureMockMvc
public class PropertyExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreatePropertyInvalidDataException() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setName(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/properties")
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testUpdatePropertyInvalidDataException() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setName(null);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/properties/" + propertyDTO.getId())
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(),
                (result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testGetPropertyByIdResourceNotFoundException() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Property not found");

        when(this.propertyService.getPropertyById(propertyDTO.getId()))
                .thenThrow(new ResourceNotFoundException("Property not found"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testUpdatePropertyByIdResourceNotFoundException() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Property not found");

        when(this.propertyService.updateProperty(eq(propertyDTO.getId()), any()))
                .thenThrow(new ResourceNotFoundException("Property not found"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/properties/" + propertyDTO.getId())
                .content(MasterData.asJsonString(propertyDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }

    @Test
    public void testDeletePropertyByIdResourceNotFoundException() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        ErrorResponse exResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                "Property not found");

        when(this.propertyService.deleteProperty(propertyDTO.getId()))
                .thenThrow(new ResourceNotFoundException("Property not found"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
                exceptionTestFile);

    }
}
