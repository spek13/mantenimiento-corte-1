/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.product;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author PC
 */

@Controller
public class ProductController {
    
    private final ProductRepository product;
    
    
    public ProductController(ProductRepository product){
        this.product =product;
    }
      
     @GetMapping("/productos") // aqui muestro la lista de los producto para poder eliminar y actualiar
    public String ProductList(Product product, BindingResult result, Map<String, Object> model){
        Collection<Product> results = this.product.findAll();
        model.put("selections",results);
        return "products/ProductList";
    }
    
    @GetMapping("/productos/reporte") // aqui muestro la lista de los productos como reporte
     public String ProductLista(Product producto, BindingResult result, Map<String, Object> model){
          Collection<Product> results = this.product.findAll();
          model.put("selections",results);
          return "products/Reporte";
    }
    
    
    @GetMapping("/productos/create") // aqui se crea el forma para poder añadir un nuevo producto
    public  String initCreationForm(Map<String, Object> model){
    Product  product = new Product();
    model.put("product",product);
    return "products/CreateUpdateProductsForm";
    }
    
    @PostMapping("/productos/store")//  aqui guardo el producto añadido en el form una vez que se añadio se va a la lista productos
    public String ProcessCreationForm(@Valid Product product,BindingResult result ){
    this.product.save(product);
    return "redirect:/productos";
    }
    
    @PostMapping("/productos/{productId}/delete")// aqui borro un producto
    public String deleteProduct(@Valid Product product, BindingResult result, @PathVariable("productId") int productId) {
        this.product.deleteProduct(productId);
        return "redirect:/productos";
    } 
    
    //editar Usuarios mostrar formualario de editar
    @GetMapping("/productos/{productId}/EditarProductos")
    public String initUpdateProductForm(@PathVariable("productId") int productId, Model model) {
        Product product = this.product.findbyId(productId);
        model.addAttribute(product);
        return "products/EditarProductos"; 
    }
    
    @PostMapping("/productos/{productId}/EditarProductos")
    public String processUpdateUserForm(@Valid Product product, BindingResult result, @PathVariable("productId") int productId) {
     if (result.hasErrors()) {
           return "redirect:/productos";
        } else {
            product.setId(productId);
            this.product.save(product);
            return "redirect:/productos";
        }

    }
    
    
    
    
    
    
}
