package com.canary.beaches.controller;

import com.canary.beaches.dto.BeachDto;
import com.canary.beaches.dto.BeachPreviewDto;
import com.canary.beaches.dto.ErrorResponse;
import com.canary.beaches.dto.PaginatedResponse;
import com.canary.beaches.model.enums.Island;
import com.canary.beaches.service.BeachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/beaches")
@Tag(name = "Beaches", description = "Beaches API")
public class BeachController {

    private final BeachService beachService;

    @Autowired
    public BeachController(BeachService beachService) {
        this.beachService = beachService;
    }

    @Operation(summary = "Get a beach by ID", description = "Retrieve a beach by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the beach",
                    content = @Content(schema = @Schema(implementation = BeachDto.class))),
            @ApiResponse(responseCode = "404", description = "Beach not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<?> getBeach(
            @Parameter(description = "ID of the beach to be retrieved", required = true)
            @PathVariable Long id) {
        Optional<BeachDto> beachDto = beachService.findById(id);

        if (beachDto.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse(404, "Beach not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(beachDto);
    }

    @Operation(summary = "Search beaches", description = "Search beaches by query and return a paginated list")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of beaches",
            content = @Content(schema = @Schema(implementation = Page.class)))
    @GetMapping("/search")
    public ResponseEntity<Page<BeachDto>> searchBeaches(
            @Parameter(description = "Query string to search for beaches", required = false)
            @RequestParam(required = false) String query,
            @Parameter(description = "Page number (starting from 0)", required = false, example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Number of items per page", required = false, example = "10")
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @Parameter(description = "Field to sort by", required = false, example = "name")
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @Parameter(description = "Sorting direction (asc or desc)", required = false, example = "asc")
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {

        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid sorting direction. Use 'asc' or 'desc'.");
        }

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BeachDto> result = beachService.searchBeaches(query, pageable);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get a previw of the search result", description = "Get a preview of the search result")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the preview",
            content = @Content(schema = @Schema(implementation = Page.class)))
    @GetMapping("/search/preview")
    public ResponseEntity<PaginatedResponse<BeachPreviewDto>> searchBeachesPreview(
            @Parameter(description = "Query string to search for beaches", required = false)
            @RequestParam(required = false) String query,
            @Parameter(description = "Number of items to preview", required = false, example = "5")
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @Parameter(description = "Field to sort by", required = false, example = "name")
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @Parameter(description = "Sorting direction (asc or desc)", required = false, example = "asc")
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {

        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("Invalid sorting direction. Use 'asc' or 'desc'.");
        }

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(0, size, sort);

        PaginatedResponse<BeachPreviewDto> result = beachService.searchBeachesPreview(query, pageable);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Get a random beach", description = "Get a random beach")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved a random beach",
            content = @Content(schema = @Schema(implementation = BeachDto.class)))
    @GetMapping("/random")
    public ResponseEntity<?> getRandomBeach(@RequestParam(required = false, defaultValue = "") String island) {
        Optional<BeachDto> randomBeach = beachService.getRandomBeach(island.isEmpty() ? null : Island.valueOf(island));

        if (randomBeach.isEmpty()) {
            ErrorResponse errorResponse = new ErrorResponse(404, "No beaches found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        return ResponseEntity.ok(randomBeach);
    }

    @Operation(summary = "Show nearby beaches", description = "Show nearby beaches")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved nearby beaches",
            content = @Content(schema = @Schema(implementation = Page.class)))
    @GetMapping("/nearby")
    public ResponseEntity<PaginatedResponse<BeachPreviewDto>> getNearbyBeaches(
            @Parameter(description = "Latitude of the location", required = true)
            @RequestParam Double latitude,
            @Parameter(description = "Longitude of the location", required = true)
            @RequestParam Double longitude,
            @Parameter(description = "Radius in meters", required = false, example = "1000")
            @RequestParam(required = false, defaultValue = "1000") Double radius,
            @Parameter(description = "Number of items to preview", required = false, example = "5")
            @RequestParam(required = false, defaultValue = "5") Integer size,
            @Parameter(description = "Page number (starting from 0)", required = false, example = "0")
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @Parameter(description = "Field to sort by", required = false, example = "name")
            @RequestParam(required = false, defaultValue = "distance") String sortBy,
            @Parameter(description = "Sorting direction (asc or desc)", required = false, example = "asc")
            @RequestParam(required = false, defaultValue = "asc") String direction
    ) {
        System.out.println("latitude = " + latitude);
        System.out.println("longitude = " + longitude);

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        PaginatedResponse<BeachPreviewDto> nearbyBeaches = beachService.getNearbyBeaches(latitude, longitude, radius, pageable);
        return ResponseEntity.ok(nearbyBeaches);
    }
}