package entregable4.despensa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import entregable4.despensa.DTO.MasVendido;
import entregable4.despensa.entities.ItemPedido;
import entregable4.despensa.services.ItemPedidoService;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RestController
@RequestMapping("/itemspedidos") // la convencion es plural
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService itemPedidoService;

	@GetMapping("")
	public List<ItemPedido> getAllItemsPedidos() {
		return this.itemPedidoService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> getItemPedido(@PathVariable("id") int id) {
		Optional<ItemPedido> itemPedido = itemPedidoService.getItemPedido(id);
		if (itemPedido.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(itemPedido.get(), HttpStatus.OK);
		}
	}

	@PostMapping("")
	public ResponseEntity<ItemPedido> addItemPedido(@RequestBody ItemPedido p) {
		boolean ok = itemPedidoService.addItemPedido(p);
		if (!ok) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		} else {
			return new ResponseEntity<>(p, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ItemPedido> deleteItemPedido(@PathVariable("id") int id) {
		Optional<ItemPedido> itemPedido = itemPedidoService.getItemPedido(id);
		if (!itemPedido.isEmpty()) {
			itemPedidoService.deleteItemPedido(itemPedido.get());
			return new ResponseEntity<>(itemPedido.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
