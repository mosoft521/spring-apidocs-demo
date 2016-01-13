package com.example.controllers;

import com.example.models.CreatePersonForm;
import com.example.models.Person;
import com.example.models.UpdatePersonForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;

@Controller
@RequestMapping("/api/people")
public class PersonController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ApiOperation(value = "createPerson", nickname = "createPerson", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 400, response = String.class, message = "Invalid data"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@Validated @RequestBody CreatePersonForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO: Implement a proper error message
            return ResponseEntity.badRequest().body("Invalid data provided for operation");
        }

        return ResponseEntity.ok(new Person(form.getName(), form.getAddress()));
    }

    @ApiOperation(value = "updatePerson", nickname = "updatePerson", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 400, response = String.class, message = "Invalid data"),
            @ApiResponse(code = 404, response = String.class, message = "Person not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@Validated @RequestBody UpdatePersonForm form,
                                          @PathParam("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            //TODO: Implement a proper error message
            return ResponseEntity.badRequest().body("Invalid data provided for operation");
        }

        return ResponseEntity.ok(new Person(form.getName(), form.getAddress()));
    }

    @ApiOperation(value = "updatePerson", nickname = "updatePerson", code = 204)
    @ApiResponses({
            @ApiResponse(code = 404, response = String.class, message = "Person not found"),
            @ApiResponse(code = 500, response = String.class, message = "Internal server error")
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(int id) {
        return ResponseEntity.noContent().build();
    }
}
