import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";
import { Property } from "../model/property";

@Injectable({
  providedIn: "root",
})
export class PropertyService {
  apiUrl = "http://127.0.0.1:8081/propertylist/properties";

  constructor(private http: HttpClient) {}

  getAllProperties(): Observable<Property[]> {
    return this.http.get<Property[]>(this.apiUrl);
  }

  getPropertyById(id: number): Observable<Property> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Property>(url);
  }

  createProperty(property: Property): Observable<Property> {
    return this.http.post<Property>(this.apiUrl, property);
  }

  updateProperty(property: Property): Observable<Property> {
    const url = `${this.apiUrl}/${property.id}`;
    return this.http.put<Property>(url, property);
  }

  deleteProperty(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }

  searchPropertiesByName(name: String): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/search?name=` + name);
  }

  searchPropertiesByPrice(price: Number): Observable<Property[]> {
    return this.http.get<Property[]>(`${this.apiUrl}/search?price=` + price);
  }

  searchPropertiesByCategory(category: String): Observable<Property[]> {
    return this.http.get<Property[]>(
      `${this.apiUrl}/search?category=` + category
    );
  }
}
