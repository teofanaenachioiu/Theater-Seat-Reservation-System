import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RezervariComponent } from './rezervari.component';

describe('RezervariComponent', () => {
  let component: RezervariComponent;
  let fixture: ComponentFixture<RezervariComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RezervariComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RezervariComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
