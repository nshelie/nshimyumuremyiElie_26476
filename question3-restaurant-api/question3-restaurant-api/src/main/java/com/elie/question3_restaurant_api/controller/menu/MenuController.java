package com.elie.question3_restaurant_api.controller.menu;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elie.question3_restaurant_api.model.menu.MenuItem;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
 

    private List<MenuItem> menuItems = new ArrayList<>();

    // Initialize sample data (at least 8 items)
    public MenuController() {

        menuItems.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable rolls", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Chicken Wings", "Spicy grilled wings", 7.99, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Grilled Chicken", "Served with fries", 12.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Beef Steak", "Juicy steak with sauce", 18.99, "Main Course", false));
        menuItems.add(new MenuItem(5L, "Cheesecake", "Classic dessert", 6.50, "Dessert", true));
        menuItems.add(new MenuItem(6L, "Chocolate Cake", "Rich chocolate flavor", 6.00, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Coca Cola", "Cold beverage", 2.50, "Beverage", true));
        menuItems.add(new MenuItem(8L, "Orange Juice", "Freshly squeezed", 3.00, "Beverage", false));
    }

    // GET all items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems);
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {

        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                return ResponseEntity.ok(item);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // GET by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getByCategory(@PathVariable String category) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                result.add(item);
            }
        }

        return ResponseEntity.ok(result);
    }

    // GET available items
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableItems(@RequestParam boolean available) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.isAvailable() == available) {
                result.add(item);
            }
        }

        return ResponseEntity.ok(result);
    }

    // SEARCH by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchByName(@RequestParam String name) {

        List<MenuItem> result = new ArrayList<>();

        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(item);
            }
        }

        return ResponseEntity.ok(result);
    }

    // POST new item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {

        menuItems.add(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    // PUT toggle availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {

        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // DELETE item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {

        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                menuItems.remove(item);
                return ResponseEntity.noContent().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
 
}
