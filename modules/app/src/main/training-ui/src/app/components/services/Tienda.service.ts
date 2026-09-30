import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ResourceDefinition } from '../config/resource-definitions.config';
import { ProductoDTO } from '../interfaces/TiendaDto';

@Injectable({
  providedIn: 'root'
})
export class TiendaService {

  private urlDefinitions: ResourceDefinition = new ResourceDefinition();

  constructor(private http: HttpClient) {
  }

  public getProductos(
    search: string,
    page: number,
    size: number
  ): Observable<any> {

    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    const paramsWithSearch = search.trim()
      ? params.set('search', search.trim())
      : params;

    return this.http.get<any>(
      this.urlDefinitions.productoUrl,
      { params: paramsWithSearch }
    );
  }

  public deleteProducto(id: number): Observable<any> {
    return this.http.delete<any>(`${this.urlDefinitions.productoUrl}/${id}`);
  }

  public crearProducto(producto: ProductoDTO): Observable<ProductoDTO> {
    return this.http.post<ProductoDTO>(this.urlDefinitions.productoUrl, producto);
  }

  public actualizarProducto(id: number, producto: ProductoDTO): Observable<ProductoDTO> {
    return this.http.put<ProductoDTO>(`${this.urlDefinitions.productoUrl}/${id}`, producto);
  }

}
