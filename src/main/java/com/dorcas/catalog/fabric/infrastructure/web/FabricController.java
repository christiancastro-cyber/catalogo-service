package com.dorcas.catalog.fabric.infrastructure.web;

import com.dorcas.catalog.fabric.application.FabricService;
import com.dorcas.catalog.fabric.domain.Category;
import com.dorcas.catalog.fabric.domain.Fabric;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fabrics")
public class FabricController {

    private final FabricService fabricService;

    public FabricController(FabricService fabricService) {
        this.fabricService = fabricService;
    }

    @GetMapping
    public List<FabricOutputDto> list(@RequestParam(required = false) Category category,
                                      @RequestParam(required = false) Boolean beginnerFriendly) {
        return fabricService.list(category, beginnerFriendly).stream()
                .map(FabricOutputDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public FabricOutputDto get(@PathVariable Long id) {
        return FabricOutputDto.from(fabricService.findById(id));
    }

    @PostMapping
    public ResponseEntity<FabricOutputDto> create(@Valid @RequestBody FabricInputDto input) {
        Fabric fabric = fabricService.create(input.toFabric());
        return ResponseEntity
                .created(URI.create("/api/fabrics/" + fabric.getId()))
                .body(FabricOutputDto.from(fabric));
    }

    @PutMapping("/{id}")
    public FabricOutputDto update(@PathVariable Long id, @Valid @RequestBody FabricInputDto input) {
        return FabricOutputDto.from(fabricService.update(id, input.toFabric()));
    }

    @PatchMapping("/{id}")
    public FabricOutputDto changePrice(@PathVariable Long id, @Valid @RequestBody PriceChangeInputDto input) {
        return FabricOutputDto.from(fabricService.changePrice(id, input.pricePerMeter()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        fabricService.delete(id);
    }

    @PostMapping("/{id}/sales")
    @ResponseStatus(HttpStatus.CREATED)
    public SaleOutputDto sell(@PathVariable Long id, @Valid @RequestBody SaleInputDto input) {
        return SaleOutputDto.from(fabricService.sell(id, input.meters()));
    }

    @GetMapping("/{id}/sales")
    public List<SaleOutputDto> listSales(@PathVariable Long id) {
        return fabricService.listSales(id).stream()
                .map(SaleOutputDto::from)
                .toList();
    }

    @GetMapping("/{id}/sales/total")
    public SalesTotalOutputDto salesTotal(@PathVariable Long id) {
        return new SalesTotalOutputDto(id, fabricService.metersSold(id));
    }

    @GetMapping("/restock")
    public List<FabricOutputDto> restockList(@RequestParam(defaultValue = "10") double minimum) {
        return fabricService.restockList(minimum).stream()
                .map(FabricOutputDto::from)
                .toList();
    }
}
