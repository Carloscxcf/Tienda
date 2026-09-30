import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { CommonModule } from "@angular/common";
import { ListadoComponent } from "./listado.component";
import { ListadoRoutingModule } from "./listado-routing.modules";
import { PaginatorModule } from "../shared/paginator/paginator.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AddProductoComponent } from "./add-producto/add-producto.component";
import { AddProductoModalComponent } from "./add-producto-modal/add-producto-modal.component";

@NgModule({
    declarations: [
        ListadoComponent,
        AddProductoComponent,
        AddProductoModalComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        PaginatorModule,
        ListadoRoutingModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class ListadoModule {}
