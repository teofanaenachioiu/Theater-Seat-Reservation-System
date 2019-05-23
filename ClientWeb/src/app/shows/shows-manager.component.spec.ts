import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowsManagerComponent } from './shows-manager.component';

describe('ShowsManagerComponent', () => {
  let component: ShowsManagerComponent;
  let fixture: ComponentFixture<ShowsManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowsManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowsManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
