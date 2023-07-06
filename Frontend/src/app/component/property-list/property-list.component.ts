import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Property } from "../../model/property";
import { PropertyService } from "../../service/property.service";

@Component({
  selector: "app-property-list",
  templateUrl: "./property-list.component.html",
  styleUrls: ["./property-list.component.css"],
})
export class PropertyListComponent implements OnInit {
  properties: Property[] = [];
  selectedProperty: Property | undefined;
  newProperty: Property = new Property();
  searchQuery: string = "";
  propertyForm: FormGroup;

  searchForm: FormGroup;
  showAdd!: boolean;
  showUpdate!: boolean;

  searchResults!: any[];
  constructor(
    private propertyService: PropertyService,
    private formBuilder: FormBuilder
  ) {
    this.propertyForm = this.formBuilder.group({
      id: [""],
      name: [
        "",
        [
          Validators.required,
          Validators.minLength(3),
          Validators.maxLength(20),
        ],
      ],
      rooms: [
        "",
        [Validators.required, Validators.min(5), Validators.max(200)],
      ],
      price: [
        "",
        [Validators.required, Validators.min(0), Validators.max(9999)],
      ],
      category: ["", Validators.required],
    });

    this.searchResults = [];
    this.searchForm = this.formBuilder.group({
      name: [""],
      price: [""],
      category: [""],
    });
  }

  ngOnInit(): void {
    this.getProperties();
    this.showAdd = true;
  }

  createProperty(): void {
    this.showAdd = true;
    this.showUpdate = false;
    if (this.propertyForm.invalid) {
      return;
    }

    const product: Property = {
      id: null,
      ...this.propertyForm.value,
    };

    this.propertyService
      .createProperty(product)
      .subscribe((newProperty: Property) => {
        this.propertyForm.reset();
        this.getProperties();
      });
  }

  getProperties(): void {
    this.propertyService
      .getAllProperties()
      .subscribe((properties) => (this.properties = properties));
  }

  //not using in this app
  getProperty(id: number): void {
    this.propertyService
      .getPropertyById(id)
      .subscribe((property) => (this.selectedProperty = property));
  }

  // //not using in this app
  // getPropertyById(id: number): void {
  //   this.propertyService.getPropertyById(id).subscribe(
  //     (product: Product) => {
  //       console.log('Product:', product);
  //     },
  //     (error) => {
  //       console.error('Error:', error);
  //     }
  //   );
  // }

  // getSingle(pro: any) {
  //   this.showAdd = false;
  //   this.showUpdate = false;
  //   this.propertyForm.controls['id'].setValue(pro.id);
  //   this.propertyForm.controls['name'].setValue(pro.name);
  //   this.propertyForm.controls['rooms'].setValue(pro.rooms);
  //   this.propertyForm.controls['price'].setValue(pro.price);
  //   this.propertyForm.controls['category'].setValue(pro.category);

  // }

  edit(pro: any) {
    this.showAdd = false;
    this.showUpdate = true;
    this.propertyForm.controls["id"].setValue(pro.id);
    this.propertyForm.controls["name"].setValue(pro.name);
    this.propertyForm.controls["rooms"].setValue(pro.rooms);
    this.propertyForm.controls["price"].setValue(pro.price);
    this.propertyForm.controls["category"].setValue(pro.category);
  }

  updateProperty(property: Property): void {
    this.propertyService.updateProperty(property).subscribe(() => {
      this.propertyForm.reset();
      this.getProperties();
    });
  }

  deleteProperty(id: number): void {
    this.propertyService
      .deleteProperty(id)
      .subscribe(() => this.getProperties());
  }

  searchProperties() {
    const searchQuery: any = {};

    if (this.searchForm.value.name) {
      searchQuery.name = this.searchForm.value.name;
      this.propertyService
        .searchPropertiesByName(this.searchForm.value.name)
        .subscribe(
          (data: Property[]) => {
            this.searchResults = data;
          },
          (error) => {
            console.log("Error searching properties:", error);
          }
        );
    }

    if (this.searchForm.value.price) {
      searchQuery.price = this.searchForm.value.price;
      this.propertyService
        .searchPropertiesByPrice(this.searchForm.value.price)
        .subscribe(
          (data: Property[]) => {
            this.searchResults = data;
          },
          (error) => {
            console.log("Error searching properties:", error);
          }
        );
    }

    if (this.searchForm.value.category) {
      searchQuery.category = this.searchForm.value.category;
      this.propertyService
        .searchPropertiesByCategory(this.searchForm.value.category)
        .subscribe(
          (data: Property[]) => {
            this.searchResults = data;
          },
          (error) => {
            console.log("Error searching properties:", error);
          }
        );
    }
  }
}
