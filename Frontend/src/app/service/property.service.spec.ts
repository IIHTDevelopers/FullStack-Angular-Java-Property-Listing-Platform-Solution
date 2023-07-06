import { HttpParams } from "@angular/common/http";
import { of } from "rxjs";
import { PropertyService } from "./property.service";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import { TestBed } from "@angular/core/testing";
describe("PropertyService", () => {
  let service: PropertyService;
  let httpClientSpy: any;
  let httpMock: HttpTestingController;
  beforeEach(() => {
    httpClientSpy = {
      get: jest.fn(),
      post: jest.fn(),
      put: jest.fn(),
      delete: jest.fn(),
    };
    service = new PropertyService(httpClientSpy);
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PropertyService],
    });

    // service = TestBed.inject(PropertyService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  describe("business", () => {
    it("property service should be created", () => {
      expect(service).toBeTruthy();
    });

    it("should get all properties by calling getAllProperties in service", () => {
      const res = "some message";
      const url = "http://127.0.0.1:8081/propertylist/properties";
      jest.spyOn(httpClientSpy, "get").mockReturnValue(of(res));
      service.getAllProperties();
      expect(httpClientSpy.get).toHaveBeenCalledWith(url);
    });

    it("should get a property by calling getPropertyById in service", () => {
      const res = "some message";
      const url = "http://127.0.0.1:8081/propertylist/properties/1";
      jest.spyOn(httpClientSpy, "get").mockReturnValue(of(res));
      service.getPropertyById(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(url);
    });

    it("should create the property by calling createProperty in service", () => {
      const data = {
        id: 1,
        name: "Property1",
        rooms: 5,
        price: 5000,
        category: "HOUSES",
      };
      const res = "some message";
      const url = "http://127.0.0.1:8081/propertylist/properties";
      jest.spyOn(httpClientSpy, "post").mockReturnValue(of(res));
      service.createProperty(data);
      expect(httpClientSpy.post).toHaveBeenCalledWith(url, data);
    });

    it("should update the property by calling updateProperty in service", () => {
      const data = {
        id: 1,
        name: "Property1",
        rooms: 5,
        price: 5000,
        category: "HOUSES",
      };
      const res = "some message";
      const url = "http://127.0.0.1:8081/propertylist/properties/1";
      jest.spyOn(httpClientSpy, "put").mockReturnValue(of(res));
      service.updateProperty(data);
      expect(httpClientSpy.put).toHaveBeenCalledWith(url, data);
    });

    it("should delete the property calling deleteProperty() in service", () => {
      const res = "some message";
      const API_URL = "http://127.0.0.1:8081/propertylist/properties/1";
      jest.spyOn(httpClientSpy, "delete").mockReturnValue(of(res));
      service.deleteProperty(1);
      expect(httpClientSpy.delete).toHaveBeenCalledWith(API_URL);
    });

    it("should search property with name by calling searchPropertiesByName() in service", () => {
      const res = "some message";
      const API_URL =
        "http://127.0.0.1:8081/propertylist/properties/search?name=Property1";
      jest.spyOn(httpClientSpy, "get").mockReturnValue(of(res));
      service.searchPropertiesByName("Property1");
      expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL);
    });

    it("should search property with price by calling searchPropertiesByPrice() in service", () => {
      const res = "some message";
      const API_URL =
        "http://127.0.0.1:8081/propertylist/properties/search?price=100";
      jest.spyOn(httpClientSpy, "get").mockReturnValue(of(res));
      service.searchPropertiesByPrice(100);
      expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL);
    });

    it("should search property with category by calling searchPropertiesByCategory() in service", () => {
      const res = "some message";
      const API_URL =
        "http://127.0.0.1:8081/propertylist/properties/search?category=HOUSES";
      jest.spyOn(httpClientSpy, "get").mockReturnValue(of(res));
      service.searchPropertiesByCategory("HOUSES");
      expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL);
    });
  });
});
