import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { finalize } from 'rxjs';

import { ProductoDTO } from '../../components/interfaces/TiendaDto';
import { TiendaService } from '../../components/services/Tienda.service';
import { I18nPipe } from '../../shared/i18n/i18n.pipe';
import { I18nService } from '../../shared/i18n/i18n.service';
import { ToasterService } from '../../shared/utils/toaster.service';

export type ProductoModalMode = 'create' | 'details' | 'edit';

@Component({
  selector: 'app-add-producto-modal',
  templateUrl: './add-producto-modal.component.htm',
  styleUrls: ['./add-producto-modal.component.css'],
  standalone: false
})
export class AddProductoModalComponent implements OnChanges {
  @Input() visible = false;
  @Input() mode: ProductoModalMode = 'create';
  @Input() producto: ProductoDTO | null = null;

  @Output() closed = new EventEmitter<void>();
  @Output() saved = new EventEmitter<void>();

  public guardando = false;
  public currentMode: ProductoModalMode = 'create';
  public productoForm: FormGroup;
  private i18nPipe: I18nPipe;

  constructor(
    private formBuilder: FormBuilder,
    private tiendaService: TiendaService,
    private toaster: ToasterService,
    i18nService: I18nService
  ) {
    this.productoForm = this.formBuilder.group({
      nombre: ['', [Validators.required, Validators.pattern(/\S/), Validators.maxLength(100)]],
      precio: [null, [Validators.required, Validators.min(0)]],
      descripcion: ['', Validators.maxLength(500)],
      stock: [0, [Validators.required, Validators.min(0)]],
      idCategoria: [null, [Validators.required, Validators.min(1)]]
    });
    this.i18nPipe = new I18nPipe(i18nService);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (!this.visible || !(changes['visible'] || changes['mode'] || changes['producto'])) {
      return;
    }

    this.currentMode = this.mode;
    this.configurarModo();

    if (this.currentMode === 'create') {
      this.productoForm.reset({ nombre: '', precio: null, descripcion: '', stock: 0, idCategoria: null });
      return;
    }

    if (this.producto) {
      this.cargarProducto(this.producto);
    }
  }

  public cerrar(): void {
    if (!this.guardando) {
      this.closed.emit();
    }
  }

  public editarDesdeDetalle(): void {
    if (!this.producto) return;

    this.currentMode = 'edit';
    this.configurarModo();
    this.cargarProducto(this.producto);
  }

  public guardar(): void {
    if (this.currentMode === 'details' || this.guardando) return;

    if (this.productoForm.invalid) {
      this.productoForm.markAllAsTouched();
      return;
    }

    const values = this.productoForm.getRawValue();
    const producto: ProductoDTO = {
      id: this.currentMode === 'edit' ? this.producto?.id : undefined,
      nombre: values.nombre.trim(),
      precio: values.precio,
      descripcion: values.descripcion?.trim() ?? '',
      stock: values.stock,
      idCategoria: values.idCategoria
    };

    if (this.currentMode === 'edit' && producto.id == null) return;

    const editando = this.currentMode === 'edit';
    const request = editando
      ? this.tiendaService.actualizarProducto(producto.id!, producto)
      : this.tiendaService.crearProducto(producto);

    this.guardando = true;
    request.pipe(finalize(() => this.guardando = false)).subscribe({
      next: () => {
        this.toaster.showSuccess(
          this.i18nPipe,
          this.i18nPipe.transform(editando ? 'Product.toast.updated.success' : 'Product.toast.created.success'),
          this.i18nPipe.transform(editando ? 'Product.toast.updated.title' : 'Product.toast.created.title')
        );
        this.saved.emit();
        this.closed.emit();
      },
      error: (error: any) => {
        console.error('Error al guardar el producto:', error);
        this.toaster.showError(
          this.i18nPipe,
          this.i18nPipe.transform(editando ? 'Product.toast.save.error' : 'Product.toast.create.error'),
          this.i18nPipe.transform('Error')
        );
      }
    });
  }

  private cargarProducto(producto: ProductoDTO): void {
    this.productoForm.reset({
      nombre: producto.nombre ?? '',
      precio: producto.precio ?? null,
      descripcion: producto.descripcion ?? '',
      stock: producto.stock ?? 0,
      idCategoria: producto.idCategoria ?? null
    });
  }

  private configurarModo(): void {
    if (this.currentMode === 'details') {
      this.productoForm.disable({ emitEvent: false });
    } else {
      this.productoForm.enable({ emitEvent: false });
    }
  }
}
