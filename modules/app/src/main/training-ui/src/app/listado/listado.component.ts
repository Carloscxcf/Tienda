import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { TiendaService } from '../components/services/Tienda.service';
import { ProductoDTO } from '../components/interfaces/TiendaDto';
import { I18nPipe } from '../shared/i18n/i18n.pipe';
import { I18nService } from '../shared/i18n/i18n.service';
import { ToasterService } from '../shared/utils/toaster.service';
import { ProductoModalMode } from './add-producto-modal/add-producto-modal.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.css'],
  standalone: false
})
export class ListadoComponent implements OnInit {

  public productos: ProductoDTO[] = [];

  public cargando: boolean = false;

  public size: number =1;
  public paginator: any = null;
  public formBusqueda!: FormGroup;
  public modalProductoVisible = false;
  public modalProductoMode: ProductoModalMode = 'create';
  public productoSeleccionado: ProductoDTO | null = null;
  private i18nPipe!: I18nPipe;

  constructor(
    private tiendaService: TiendaService,
    private fb: FormBuilder,
    private toaster: ToasterService,
    private i18nService: I18nService
  ) {
    this.i18nPipe = new I18nPipe(this.i18nService);
  }

  ngOnInit(): void {
    this.formBusqueda = this.fb.group({
      search: ['', [Validators.maxLength(20)]]
    });
    this.getProductos(0);
  }

  public eliminarProducto(producto: ProductoDTO): void {
    if (producto.id == null) return;

    Swal.fire({
      icon: 'question',
      title: '¿Deseas borrar el producto?',
      showCancelButton: true,
      confirmButtonText: 'Confirmar',
      cancelButtonText: 'Cancelar',
      buttonsStyling: false,
      customClass: {
        confirmButton: 'btn btn-primary me-2',
        cancelButton: 'btn btn-danger'
      }
    }).then((result) => {
      if (!result.isConfirmed) return;

      this.tiendaService.deleteProducto(producto.id!).subscribe({
        next: () => {
          this.toaster.showSuccess(this.i18nPipe, 'Producto eliminado correctamente.');
          this.getProductos(this.paginator?.number ?? 0);
        },
        error: (error) => {
          console.error('Error al eliminar el producto:', error);
          this.toaster.showError(this.i18nPipe, 'No se pudo eliminar el producto.');
        }
      });
    });
  }

  public abrirModalProducto(mode: ProductoModalMode, producto: ProductoDTO | null = null): void {
    this.modalProductoMode = mode;
    this.productoSeleccionado = producto;
    this.modalProductoVisible = true;
  }

  public cerrarModalProducto(): void {
    this.modalProductoVisible = false;
    this.productoSeleccionado = null;
  }

  public alGuardarProducto(): void {
    this.getProductos(this.paginator?.number ?? 0);
  }

  public getProductos(page: number): void {

    this.cargando = true;

    this.tiendaService
      .getProductos(this.formBusqueda.value.search ?? '', page, this.size)
      .subscribe({
        next: (response: any) => {

          console.log('Productos recibidos:', response?.content);
          this.paginator = response;
          this.productos = Array.isArray(response?.content)
            ? response.content
            : [];
          this.toaster.showSuccess(
            this.i18nPipe,
            this.i18nPipe.transform('Search.notification.success')
          );

          this.cargando = false;
        },

        error: (error) => {

          console.error('Error al obtener los productos:', error);

          this.productos = [];
          this.paginator = null;
          this.toaster.showError(
            this.i18nPipe,
            this.i18nPipe.transform('Search.notification.error')
          );
          this.cargando = false;
        }
      });
  }

}
