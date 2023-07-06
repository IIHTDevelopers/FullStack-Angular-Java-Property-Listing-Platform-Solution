package com.example.propertylist.functional;

import com.example.propertylist.controller.PropertyController;
import com.example.propertylist.dto.PropertyDTO;
import com.example.propertylist.entity.PropertyCategory;
import com.example.propertylist.service.PropertyService;
import com.example.propertylist.utils.MasterData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.example.propertylist.utils.MasterData.getPropertyDTO;
import static com.example.propertylist.utils.MasterData.getPropertyDTOList;
import static com.example.propertylist.utils.TestUtils.businessTestFile;
import static com.example.propertylist.utils.TestUtils.currentTest;
import static com.example.propertylist.utils.TestUtils.testReport;
import static com.example.propertylist.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(PropertyController.class)
@AutoConfigureMockMvc
public class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PropertyService propertyService;

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testCreateProperty() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();

        when(propertyService.createProperty(any())).thenReturn(propertyDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/properties")
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTO))
                        ? "true"
                        : "false"),
                businessTestFile);
    }

    @Test
    public void testCreatePropertyIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        PropertyDTO propertyDTO = getPropertyDTO();

        when(propertyService.createProperty(any())).then(new Answer<PropertyDTO>() {

            @Override
            public PropertyDTO answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTO;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/properties")
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testGetPropertyById() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        when(this.propertyService.getPropertyById(propertyDTO.getId())).thenReturn(propertyDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTO)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetPropertyByIdIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        PropertyDTO propertyDTO = getPropertyDTO();
        when(this.propertyService.getPropertyById(propertyDTO.getId())).then(new Answer<PropertyDTO>() {

            @Override
            public PropertyDTO answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTO;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testGetAllProperties() throws Exception {
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();

        when(this.propertyService.getAllProperties()).thenReturn(propertyDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTOS)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testGetAllPropertiesIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();

        when(this.propertyService.getAllProperties()).then(new Answer<List<PropertyDTO>>() {

            @Override
            public List<PropertyDTO> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTOS;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testUpdateProperty() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();

        when(this.propertyService.updateProperty(eq(propertyDTO.getId()), any())).thenReturn(propertyDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/properties/" + propertyDTO.getId())
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTO))
                        ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testUpdatePropertyIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        PropertyDTO propertyDTO = getPropertyDTO();

        when(this.propertyService.updateProperty(eq(propertyDTO.getId()), any())).then(new Answer<PropertyDTO>() {

            @Override
            public PropertyDTO answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTO;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/properties/" + propertyDTO.getId())
                .content(MasterData.asJsonString(propertyDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testDeleteProperty() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        when(this.propertyService.deleteProperty(propertyDTO.getId())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals("") ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testDeletePropertyIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        PropertyDTO propertyDTO = getPropertyDTO();
        when(this.propertyService.deleteProperty(propertyDTO.getId())).then(new Answer<Boolean>() {

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return true;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/properties/" + propertyDTO.getId())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testSearchPropertiesByName() throws Exception {
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        String name = "random";
        when(this.propertyService.searchPropertiesByName(name)).thenReturn(propertyDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("name", name)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTOS)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testSearchPropertiesByNameIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        String name = "random";
        when(this.propertyService.searchPropertiesByName(name)).then(new Answer<List<PropertyDTO>>() {

            @Override
            public List<PropertyDTO> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTOS;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("name", name)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testSearchPropertiesByPrice() throws Exception {
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        Double price = 5555D;
        when(this.propertyService.searchPropertiesByPrice(price)).thenReturn(propertyDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("price", price.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTOS)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testSearchPropertiesByPriceIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        Double price = 5555D;
        when(this.propertyService.searchPropertiesByPrice(price)).then(new Answer<List<PropertyDTO>>() {

            @Override
            public List<PropertyDTO> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTOS;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("price", price.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testSearchPropertiesByCategory() throws Exception {
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        PropertyCategory category = PropertyCategory.VILLAS;
        when(this.propertyService.searchPropertiesByCategory(category)).thenReturn(propertyDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("category", category.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTOS)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testSearchPropertiesByCategoryIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        PropertyCategory category = PropertyCategory.VILLAS;
        when(this.propertyService.searchPropertiesByCategory(category)).then(new Answer<List<PropertyDTO>>() {

            @Override
            public List<PropertyDTO> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTOS;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .queryParam("category", category.toString())
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }

    @Test
    public void testSearchPropertiesByWithoutFilter() throws Exception {
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        when(this.propertyService.getAllProperties()).thenReturn(propertyDTOS);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        yakshaAssert(currentTest(),
                (result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(propertyDTOS)) ? "true"
                        : "false"),
                businessTestFile);

    }

    @Test
    public void testSearchPropertiesByWithoutFilterIsServiceMethodCalled() throws Exception {
        final int count[] = new int[1];
        List<PropertyDTO> propertyDTOS = getPropertyDTOList();
        when(this.propertyService.getAllProperties()).then(new Answer<List<PropertyDTO>>() {

            @Override
            public List<PropertyDTO> answer(InvocationOnMock invocation) throws Throwable {
                // TODO Auto-generated method stub
                count[0]++;
                return propertyDTOS;
            }
        });
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/properties/search")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

    }
}
