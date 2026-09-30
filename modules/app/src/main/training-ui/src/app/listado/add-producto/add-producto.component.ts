import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ProductoDTO } from '../../components/interfaces/TiendaDto';
import { TiendaService } from '../../components/services/Tienda.service';
import { I18nPipe } from '../../shared/i18n/i18n.pipe';
import { I18nService } from '../../shared/i18n/i18n.service';
import { ToasterService } from '../../shared/utils/toaster.service';

@Component({
  selector: 'app-add-producto',
  templateUrl: './add-producto.component.html',
  styleUrls: ['./add-producto.component.css'],
  standalone: false
})
export class AddProductoComponent {
  public guardando = false;
  public formProducto: FormGroup;
  private i18nPipe: I18nPipe;

  constructor(
    private formBuilder: FormBuilder,
    private tiendaService: TiendaService,
    private router: Router,
    private toaster: ToasterService,
    private i18nService: I18nService
  ) {
    this.formProducto = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.maxLength(100)]],
      precio: [null, [Validators.required, Validators.min(0)]],
      descripcion: ['', Validators.maxLength(500)],
      stock: [0, [Validators.required, Validators.min(0)]],
      idCategoria: [null, [Validators.required, Validators.min(1)]]
    });
    this.i18nPipe = new I18nPipe(this.i18nService);
  }

  public guardar(): void {
    if (this.formProducto.invalid || this.guardando) {
      this.formProducto.markAllAsTouched();
      return;
    }

    const valores = this.formProducto.getRawValue();
    const producto: ProductoDTO = {
      nombre: valores.nombre.trim(),
      precio: valores.precio,
      descripcion: valores.descripcion?.trim() ?? '',
      stock: valores.stock,
      idCategoria: valores.idCategoria
    };

    this.guardando = true;
    this.tiendaService.crearProducto(producto).subscribe({
      next: () => {
        this.toaster.showSuccess(
          this.i18nPipe,
          this.i18nPipe.transform('Product.toast.created.success'),
          this.i18nPipe.transform('Product.toast.created.title')
        );
        this.router.navigate(['/listado']);
      },
      error: (error: any) => {
        console.error('Error al agregar el producto:', error);
        this.guardando = false;
        this.toaster.showError(
          this.i18nPipe,
          this.i18nPipe.transform('Product.toast.create.error'),
          this.i18nPipe.transform('Error')
        );
      }
    });
  }

  public cancelar(): void {
    this.router.navigate(['/listado']);
  }
}
