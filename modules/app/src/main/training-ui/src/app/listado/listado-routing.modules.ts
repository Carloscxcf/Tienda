import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ListadoComponent } from "./listado.component";
import { AddProductoComponent } from "./add-producto/add-producto.component";

const routes: Routes = [
    {
        //localhost:4200/listado
        path: '',
        pathMatch: 'full',
        component: ListadoComponent,
        data: {
            pageTitle: 'Listado'
        }
    },
    {
        path: 'agregar',
        component: AddProductoComponent,
        data: {
            pageTitle: 'Agregar producto'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ListadoRoutingModule { }   
