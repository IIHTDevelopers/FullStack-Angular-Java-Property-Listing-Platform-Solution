package com.example.propertylist.boundary;


import com.example.propertylist.dto.PropertyDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.example.propertylist.utils.MasterData.getPropertyDTO;
import static com.example.propertylist.utils.MasterData.randomStringWithSize;
import static com.example.propertylist.utils.TestUtils.boundaryTestFile;
import static com.example.propertylist.utils.TestUtils.currentTest;
import static com.example.propertylist.utils.TestUtils.testReport;
import static com.example.propertylist.utils.TestUtils.yakshaAssert;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testNameNotNull() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setName(null);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMinThree() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setName(randomStringWithSize(2));
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testNameMaxTwentyOne() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setName(randomStringWithSize(21));
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRoomsNotNull() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setRooms(null);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRoomsMinFive() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setRooms(4);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testRoomsMaxTwoHundred() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setRooms(201);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceNotNull() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setPrice(null);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceMinZero() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setPrice(-1D);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testPriceMax9999() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setPrice(100000D);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }

    @Test
    public void testCategoryNotNull() throws Exception {
        PropertyDTO propertyDTO = getPropertyDTO();
        propertyDTO.setCategory(null);
        Set<ConstraintViolation<PropertyDTO>> violations = validator.validate(propertyDTO);
        yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
    }
}
