import { TestBed, ComponentFixture } from "@angular/core/testing";
import { AppComponent } from "./app.component";
import { RouterTestingModule } from "@angular/router/testing";
import { HttpClient, HttpHandler } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { PropertyListComponent } from "./component/property-list/property-list.component";

describe("AppComponent", () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule, ReactiveFormsModule],
      declarations: [AppComponent, PropertyListComponent],
      providers: [HttpClient, HttpHandler],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  describe("business", () => {
    it("should create app component", () => {
      expect(component).toBeTruthy();
    });

    it("should have title as Property Listing Platform", () => {
      const titleElement = fixture.nativeElement.querySelector("h1");
      expect(titleElement.textContent).toContain("Property Listing Platform");
    });
  });
});
