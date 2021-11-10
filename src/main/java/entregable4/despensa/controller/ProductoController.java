package entregable4.despensa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entregable4.despensa.DTO.MasVendido;
import entregable4.despensa.entities.Producto;
import entregable4.despensa.services.ProductoService;

@RestController
@RequestMapping("/productos") // la convencion es plural
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("")
	public List<Producto> getAllProductos() {
		return this.productoService.getProductos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> getProducto(@PathVariable("id") int id) {
		Optional<Producto> producto = productoService.getProducto(id);
		if (producto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(producto.get(), HttpStatus.OK);
		}
	}

	@PostMapping("")
	public ResponseEntity<Producto> addProducto(@RequestBody Producto p) {
		boolean ok = productoService.addProducto(p);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		} else {
			return new ResponseEntity<>(p, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Producto> deleteProducto(@PathVariable("id") int id) {
		Optional<Producto> producto = productoService.getProducto(id);
		if (!producto.isEmpty()) {
			productoService.deleteProducto(producto.get());
			return new ResponseEntity<Producto>(producto.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/masvendido")
	public ResponseEntity<MasVendido> getMasVendido(){
		Optional<MasVendido> masvendido= productoService.getProductoMasVendido();
		if(!masvendido.isEmpty()) {
			return new ResponseEntity<MasVendido>(masvendido.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<MasVendido>(HttpStatus.NOT_FOUND);
		}
	}
	

}
