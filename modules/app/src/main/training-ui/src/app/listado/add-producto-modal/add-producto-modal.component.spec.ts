import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { of } from 'rxjs';

import { AddProductoModalComponent } from './add-producto-modal.component';
import { TiendaService } from '../../components/services/Tienda.service';
import { I18nModule } from '../../shared/i18n/i18n.module';
import { I18nService } from '../../shared/i18n/i18n.service';
import { ToasterService } from '../../shared/utils/toaster.service';

describe('AddProductoModalComponent', () => {
  let component: AddProductoModalComponent;
  let fixture: ComponentFixture<AddProductoModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddProductoModalComponent],
      imports: [CommonModule, ReactiveFormsModule, I18nModule],
      providers: [
        {
          provide: TiendaService,
          useValue: {
            crearProducto: () => of({}),
            actualizarProducto: () => of({})
          }
        },
        { provide: ToasterService, useValue: { showSuccess: () => undefined, showError: () => undefined } },
        { provide: I18nService, useValue: { getTranslation: (key: string) => key } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AddProductoModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
