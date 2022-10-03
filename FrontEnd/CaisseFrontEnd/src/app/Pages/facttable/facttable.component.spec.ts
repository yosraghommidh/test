import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacttableComponent } from './facttable.component';

describe('FacttableComponent', () => {
  let component: FacttableComponent;
  let fixture: ComponentFixture<FacttableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacttableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacttableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
