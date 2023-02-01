package org.projects.quarkus.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.projects.quarkus.constants.ApplicationConstants;
import org.projects.quarkus.dto.group.CreateGroupDTO;
import org.projects.quarkus.dto.login.LoginRequestDTO;
import org.projects.quarkus.dto.user.UserDetails;
import org.projects.quarkus.service.group.GroupService;
import org.projects.quarkus.service.login.UserAccessService;
import org.projects.quarkus.service.user.UserService;

import io.smallrye.mutiny.Uni;

/**
 * @author Athul K S
 * 
 *         INFO : Controller class to handle the requests
 *
 */
@Path("/quarkus/reactive")
public class ReactiveController {

	@Inject
	UserService userService;

	@Inject
	GroupService groupService;

	@Inject
	UserAccessService accessService;

	/**
	 * @return INFO : A simple service greeting message
	 */
	@GET
	@Path("/greet")
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<String> hello() {
		return Uni.createFrom().item(() -> "Welcome to Reactive programming in quarkus!");
	}

	/**
	 * @param id
	 * @return Response with user details
	 */
	@GET
	@Path("/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> getUserDetails(@PathParam("id") Long id) {
		return userService.getUserDetails(id).onItem().transform(detail -> Response.ok(detail).build());
	}

	/**
	 * @param userDetail
	 * @return Response with success or failure details for register user
	 */
	@POST
	@Path("/user/register")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> registerUser(UserDetails userDetail) {
		return userService.registerUser(userDetail).onItem().transform(response -> {
			return response.getStatusCode() == ApplicationConstants.SUCCESS_CODE
					? Response.ok().entity(response).build()
					: Response.serverError().entity(response).build();
		});
	}

	/**
	 * @param group
	 * @return Response with success or failure details for create group
	 */
	@POST
	@Path("/group/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> createGroup(CreateGroupDTO group) {
		return groupService.createGroup(group).onItem().transform(response -> {
			return response.getStatusCode() == ApplicationConstants.SUCCESS_CODE
					? Response.ok().entity(response).build()
					: Response.serverError().entity(response).build();
		});
	}

	/**
	 * @return The list of groups
	 */
	@GET
	@Path("/group/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> groupList() {
		return groupService.listGroups().onItem().transform(list -> Response.ok().entity(list).build());
	}

	/**
	 * @return Login response
	 */
	@POST
	@Path("/user/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<Response> userLogin(LoginRequestDTO request) {
		return accessService.loginUser(request).onItem().transform(response -> Response.ok().entity(response).build());
	}
}