package com.yeisystems.training.config;

import org.springframework.context.annotation.Configuration;
import com.yeisystems.training.usecases.app.mappers.CategoriaMapper;
import com.yeisystems.training.usecases.app.mappers.impl.CategoriaMapperImpl;
import com.yeisystems.training.usecases.app.ports.in.CategoriaUsecase;
import com.yeisystems.training.usecases.app.ports.out.CategoriaBDGateway;
import com.yeisystems.training.usecases.app.services.CategoriaInteractor;
import com.yeisystems.training.usecases.app.mappers.ProductoMapper;
import com.yeisystems.training.usecases.app.mappers.impl.ProductoMapperImpl;
import com.yeisystems.training.usecases.app.ports.in.ProductoUsecase;
import com.yeisystems.training.usecases.app.ports.out.ProductoBDGateway;
import com.yeisystems.training.usecases.app.services.ProductoInteractor;
import com.yeisystems.training.usecases.app.mappers.UsuarioMapper;
import com.yeisystems.training.usecases.app.mappers.impl.UsuarioMapperImpl;
import com.yeisystems.training.usecases.app.ports.in.UsuarioUsecase;
import com.yeisystems.training.usecases.app.ports.out.UsuarioBDGateway;
import com.yeisystems.training.usecases.app.services.UsuarioInteractor;
import com.yeisystems.training.usecases.app.mappers.PedidoMapper;
import com.yeisystems.training.usecases.app.mappers.impl.PedidoMapperImpl;
import com.yeisystems.training.usecases.app.ports.in.PedidoUsecase;
import com.yeisystems.training.usecases.app.ports.out.PedidoBDGateway;
import com.yeisystems.training.usecases.app.services.PedidoInteractor;
import com.yeisystems.training.usecases.app.mappers.DetallePedidoMapper;
import com.yeisystems.training.usecases.app.mappers.impl.DetallePedidoMapperImpl;
import com.yeisystems.training.usecases.app.ports.in.DetallePedidoUsecase;
import com.yeisystems.training.usecases.app.ports.out.DetallePedidoBDGateway;
import com.yeisystems.training.usecases.app.services.DetallePedidoInteractor;
import org.springframework.context.annotation.Bean;

/**
 * Configuración global de la aplicación.
 *
 * Aquí se registran los beans del núcleo (Interactors y Mappers) con @Bean,
 * de modo que las clases de usecases no dependan de anotaciones de Spring.
 *
 * Ejemplo:
 *
 *   @Bean
 *   public MiInteractor miInteractor(MiBDGateway gateway, MiMapper mapper) {
 *       return new MiInteractor(gateway, mapper);
 *   }
 *
 *   @Bean
 *   public MiMapper miMapper() {
 *       return new MiMapperImpl();
 *   }
 */
@Configuration
public class GlobalConfiguration {

    @Bean
    public CategoriaMapper categoriaMapper() {
        return new CategoriaMapperImpl();
    }

    @Bean
    public CategoriaUsecase categoriaUsecase(CategoriaBDGateway categoriaBDGateway, ProductoBDGateway productoBDGateway, CategoriaMapper categoriaMapper) {
        return new CategoriaInteractor(categoriaBDGateway, productoBDGateway, categoriaMapper);
        // Categoria ahora tambien necesita ProductoBDGateway, para revisar si tiene
        // productos asociados antes de dejarse borrar (ver deleteById del Interactor)
    }

    @Bean
    public ProductoMapper productoMapper() {
        return new ProductoMapperImpl();
    }

    @Bean
    public ProductoUsecase productoUsecase(ProductoBDGateway productoBDGateway, CategoriaBDGateway categoriaBDGateway,
                                           DetallePedidoBDGateway detallePedidoBDGateway, ProductoMapper productoMapper) {
        return new ProductoInteractor(productoBDGateway, categoriaBDGateway, detallePedidoBDGateway, productoMapper);
        // Producto ahora tambien necesita DetallePedidoBDGateway, para revisar si esta
        // en algun pedido antes de dejarse borrar (ver deleteById del Interactor)
    }

    @Bean
    public UsuarioMapper usuarioMapper() {
        return new UsuarioMapperImpl();
    }

    @Bean
    public UsuarioUsecase usuarioUsecase(UsuarioBDGateway usuarioBDGateway, PedidoBDGateway pedidoBDGateway, UsuarioMapper usuarioMapper) {
        return new UsuarioInteractor(usuarioBDGateway, pedidoBDGateway, usuarioMapper);
        // Usuario ahora tambien necesita PedidoBDGateway, para revisar si tiene
        // pedidos asociados antes de dejarse borrar (ver deleteById del Interactor)
    }

    @Bean
    public PedidoMapper pedidoMapper() {
        return new PedidoMapperImpl();
    }

    @Bean
    public PedidoUsecase pedidoUsecase(PedidoBDGateway pedidoBDGateway, UsuarioBDGateway usuarioBDGateway, PedidoMapper pedidoMapper) {
        return new PedidoInteractor(pedidoBDGateway, usuarioBDGateway, pedidoMapper);
        // Pedido necesita UsuarioBDGateway para validar que el usuario exista
        // y para armar la relacion en el Interactor
    }

    @Bean
    public DetallePedidoMapper detallePedidoMapper() {
        return new DetallePedidoMapperImpl();
    }

    @Bean
    public DetallePedidoUsecase detallePedidoUsecase(DetallePedidoBDGateway detallePedidoBDGateway, PedidoBDGateway pedidoBDGateway,
                                                     ProductoBDGateway productoBDGateway, DetallePedidoMapper detallePedidoMapper) {
        return new DetallePedidoInteractor(detallePedidoBDGateway, pedidoBDGateway, productoBDGateway, detallePedidoMapper);
        // DetallePedido necesita Pedido y Producto para validar ambos, congelar el
        // precio, y descontar/regresar el Stock del producto (ver el Interactor)
    }
}