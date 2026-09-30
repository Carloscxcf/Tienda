export interface ProductoDTO {
  id?: number;
  nombre: string;
  precio: number;
  descripcion?: string;
  stock: number;
  idCategoria: number;
}