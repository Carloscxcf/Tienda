import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalPaginatorComponent } from './modal-paginator.component';

describe('ModalPaginatorComponent', () => {
  let component: ModalPaginatorComponent;
  let fixture: ComponentFixture<ModalPaginatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalPaginatorComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalPaginatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
