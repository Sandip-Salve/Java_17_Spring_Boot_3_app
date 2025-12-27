import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmloyeeRegiComponent } from './emloyee-regi.component';

describe('EmloyeeRegiComponent', () => {
  let component: EmloyeeRegiComponent;
  let fixture: ComponentFixture<EmloyeeRegiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmloyeeRegiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EmloyeeRegiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
