import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { AddProductoComponent } from './add-producto.component';
import { TiendaService } from '../../components/services/Tienda.service';
import { I18nService } from '../../shared/i18n/i18n.service';
import { ToasterService } from '../../shared/utils/toaster.service';

describe('AddProductoComponent', () => {
  let component: AddProductoComponent;
  let fixture: ComponentFixture<AddProductoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddProductoComponent],
      providers: [
        provideRouter([]),
        { provide: TiendaService, useValue: { crearProducto: () => undefined } },
        { provide: ToasterService, useValue: { showSuccess: () => undefined, showError: () => undefined } },
        { provide: I18nService, useValue: { getTranslation: (key: string) => key } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AddProductoComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});