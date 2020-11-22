package com.liferon.sparkjava.controller;

import com.liferon.sparkjava.dto.ApiResponse;
import com.liferon.sparkjava.dto.StatusResponse;
import com.liferon.sparkjava.dto.User;
import com.liferon.sparkjava.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

import static spark.Spark.*;

@RequiredArgsConstructor
public class UserController extends BaseController {

    private final UserService userService;

    public void routes() {
        post("/users", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            User user = gson.fromJson(req.body(), User.class);
            userService.addUser(user);

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .data(gson.toJsonTree(user))
                    .build()
            );
        });

        get("/users", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            Collection<User> users = userService.getUsers();

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .data(gson.toJsonTree(users))
                    .build()
            );
        });

        get("/users/:id", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            String id = req.params(":id");

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .data(gson.toJsonTree(userService.getUser(id)))
                    .build()
            );
        });

        put("/users/:id", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            String id = req.params(":id");
            User user = gson.fromJson(req.body(), User.class);

            User updatedUser = userService.updateUser(id, user);

            if (updatedUser != null) {
                return jsonResponse(ApiResponse.builder()
                        .status(StatusResponse.SUCCESS)
                        .data(gson.toJsonTree(updatedUser))
                        .build()
                );
            }

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.ERROR)
                    .message("Error updating user")
                    .build()
            );
        });

        delete("/users/:id", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            userService.deleteUser(req.params(":id"));

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .message("User deleted successfully")
                    .build()
            );
        });

        options("/users/:id", (req, resp) -> {
            resp.type(APPLICATION_JSON);
            String id = req.params(":id");

            return jsonResponse(ApiResponse.builder()
                    .status(StatusResponse.SUCCESS)
                    .message(userService.userExist(id)
                            ? "User exists"
                            : "User does not exist")
                    .build()
            );
        });
    }
}
