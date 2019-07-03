import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SpectacolCurentComponent } from './spectacol-curent.component';

describe('SpectacolCurentComponent', () => {
  let component: SpectacolCurentComponent;
  let fixture: ComponentFixture<SpectacolCurentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SpectacolCurentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SpectacolCurentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
