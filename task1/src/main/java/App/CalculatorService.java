package App;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ejbs.Calculator;

@Stateless

@Path("/calc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CalculatorService {
	@EJB
	private Calculator calculator;

	@PersistenceContext(unitName = "hello")
	private EntityManager entityManager;

	@POST
	@Path("/sendnum")
	public Response createCalculation(Calculator calculation) {
		try {
			int result = calculator.performCalculation(calculation.getNum1(), calculation.getNum2(), calculation.getOp());
			
			entityManager.persist(calculation);
			return Response.ok().entity(Collections.singletonMap("Result", result)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/calculations")
	public Response getAllCalculations() {
		try {
			List<Calculator> calculations = entityManager.createQuery("SELECT c FROM Calculation c", Calculator.class)
					.getResultList();
			return Response.ok().entity(calculations).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
