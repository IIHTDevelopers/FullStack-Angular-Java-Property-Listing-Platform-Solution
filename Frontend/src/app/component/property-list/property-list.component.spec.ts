import { ComponentFixture, TestBed } from "@angular/core/testing";
import { FormBuilder, FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PropertyListComponent } from "./property-list.component";
import { PropertyService } from "../../service/property.service";
import { HttpClientModule } from "@angular/common/http";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { Property } from "src/app/model/property";
import { of } from "rxjs";

describe("PropertyComponent", () => {
  let component: PropertyListComponent;
  let fixture: ComponentFixture<PropertyListComponent>;
  let serviceMock: any;
  let productService: PropertyService;
  const property: Property = {
    id: 1,
    name: "Property1",
    rooms: 5,
    price: 5000,
    category: "HOUSES",
  };

  let mockService = {
    getAllProperties: () => {
      return of([property]);
    },
    getPropertyById: () => {
      return of([property]);
    },
    deleteProperty: (id: number | string) => {
      return of(property);
    },
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PropertyListComponent],
      imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
      ],
      providers: [
        FormBuilder,
        PropertyService,
        HttpTestingController,
        { provide: PropertyService, useValue: mockService },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListComponent);
    component = fixture.componentInstance;
    productService = TestBed.inject(PropertyService);
    fixture.detectChanges();
  });

  describe("business", () => {
    it("should create the property component", () => {
      expect(component).toBeTruthy();
    });

    it("should declare propertyForm & searchForm", () => {
      expect(component.propertyForm).toBeDefined();
      expect(component.searchForm).toBeDefined();
    });

    it("should initialize the propertyForm", () => {
      const propertyForm = {
        id: "",
        name: "",
        price: "",
        category: "",
        rooms: "",
      };
      expect(component.propertyForm.value).toEqual(propertyForm);
    });

    it("should initialize the searchForm", () => {
      const searchForm = {
        name: "",
        price: "",
        category: "",
      };
      expect(component.searchForm.value).toEqual(searchForm);
    });

    it("should validate the name field in the propertyForm", () => {
      const c = component.propertyForm.controls["name"];
      c.setValue("Property1");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
      c.setValue("Pr");
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the rooms field in the propertyForm", () => {
      const c = component.propertyForm.controls["rooms"];
      c.setValue(50);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
      c.setValue(2);
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the price field in the propertyForm", () => {
      const c = component.propertyForm.controls["price"];
      c.setValue(500);
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
      c.setValue(-200);
      expect(c.invalid).toBeTruthy();
      c.setValue(999999);
      expect(c.invalid).toBeTruthy();
    });

    it("should validate the category field in the propertyForm", () => {
      const c = component.propertyForm.controls["category"];
      c.setValue("HOUSES");
      expect(c.valid).toBeTruthy();
      c.setValue("");
      expect(c.invalid).toBeTruthy();
    });
  });

  describe("boundary", () => {
    it("should invalidate the propertyForm when name length is greater than 20", () => {
      const form = component.propertyForm;
      form.controls["name"].setValue(
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa  bbbb"
      );
      expect(form.invalid).toBeTruthy();
      expect(form.controls["name"].errors?.["maxlength"]).toBeTruthy();
    });

    it("should invalidate the propertyForm when name length is less than 3", () => {
      const form = component.propertyForm;
      form.controls["name"].setValue("Pr");
      expect(form.invalid).toBeTruthy();
      expect(form.controls["name"].errors?.["minlength"]).toBeTruthy();
    });

    it("should invalidate the propertyForm when price is greater than 9999", () => {
      const form = component.propertyForm;
      form.controls["price"].setValue(18000);
      expect(form.invalid).toBeTruthy();
      expect(form.controls["price"].errors?.["max"]).toBeTruthy();
    });

    it("should invalidate the propertyForm when rooms value is less than 5", () => {
      const form = component.propertyForm;
      form.controls["rooms"].setValue(3);
      expect(form.invalid).toBeTruthy();
      expect(form.controls["rooms"].errors?.["min"]).toBeTruthy();
    });

    it("should invalidate the propertyForm when rooms value is greater than 1000", () => {
      const form = component.propertyForm;
      form.controls["rooms"].setValue(400);
      expect(form.invalid).toBeTruthy();
      expect(form.controls["rooms"].errors?.["max"]).toBeTruthy();
    });

    it("should validate the propertyForm on valid values to all fields", () => {
      component.propertyForm.controls["name"].setValue("Property1");
      component.propertyForm.controls["rooms"].setValue("6");
      component.propertyForm.controls["price"].setValue("500");
      component.propertyForm.controls["category"].setValue("HOUSES");
      expect(component.propertyForm.valid).toBeTruthy();
    });

    it("should disable the submit button when the propertyForm is invalid", () => {
      const form = component.propertyForm;
      form.controls["name"].setValue("");
      form.controls["rooms"].setValue(6);
      form.controls["price"].setValue(100);
      form.controls["category"].setValue("");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(true);
    });

    it("should enable the submit button when the propertyForm is valid", () => {
      const form = component.propertyForm;
      form.controls["name"].setValue("Property1");
      form.controls["rooms"].setValue(6);
      form.controls["price"].setValue(999);
      form.controls["category"].setValue("HOUSES");
      fixture.detectChanges();
      const submitButton = fixture.nativeElement.querySelector(
        'button[type="submit"]'
      );
      expect(submitButton.disabled).toBe(false);
    });
  });

  describe("exception", () => {
    it("should invalidate the propertyForm when empty", () => {
      component.propertyForm.controls["name"].setValue("");
      component.propertyForm.controls["rooms"].setValue("");
      component.propertyForm.controls["price"].setValue("");
      component.propertyForm.controls["category"].setValue("");
      expect(component.propertyForm.valid).toBeFalsy();
    });

    it("name field should show required error when no value passed", () => {
      const c = component.propertyForm.controls["name"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("price field should show required error when no value passed", () => {
      const c = component.propertyForm.controls["price"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("category field should show required error when no value passed", () => {
      const c = component.propertyForm.controls["category"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });

    it("rooms field should show required error when no value passed", () => {
      const c = component.propertyForm.controls["rooms"];
      expect(c.valid).toBeFalsy();
      c.setValue("");
      expect(c.hasError("required")).toBeTruthy();
    });
  });

  describe("business", () => {
    it("should get all properties", () => {
      component.properties = [];
      jest
        .spyOn(mockService, "getAllProperties")
        .mockReturnValue(of([property]));
      component.getProperties();
      expect(mockService.getAllProperties).toBeCalledTimes(1);
      expect(component.properties.length).toBeGreaterThan(0);
      expect(Array.isArray(component.properties)).toBe(true);
    });

    it("should get property by id", () => {
      jest.spyOn(mockService, "getPropertyById");
      component.getProperty(1);
      expect(mockService.getPropertyById).toBeCalledTimes(1);
      expect(mockService.getPropertyById).toBeCalledWith(1);
    });

    it("should delete property by id", () => {
      jest.spyOn(mockService, "deleteProperty").mockReturnValue(of(property));
      component.deleteProperty(1);
      expect(mockService.deleteProperty).toBeCalledTimes(1);
      expect(mockService.deleteProperty).toBeCalledWith(1);
    });
  });
});
