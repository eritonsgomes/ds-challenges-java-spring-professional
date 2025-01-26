package com.devsuperior.dsdesafios.dsdesafio1mod1;

import com.devsuperior.dsdesafios.dsdesafio1mod1.model.Order;
import com.devsuperior.dsdesafios.dsdesafio1mod1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class DsDesafio1Mod1Application implements CommandLineRunner {

	private final Logger logger = Logger.getLogger(DsDesafio1Mod1Application.class.getName());

	private final OrderService orderService;

	@Autowired
	public DsDesafio1Mod1Application(OrderService orderService) {
		this.orderService = orderService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DsDesafio1Mod1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var order1 = new Order(1034, 150.0, 20.0);
		var total1 = orderService.total(order1);

		var order2 = new Order(2282, 800.0, 10.0);
		var total2 = orderService.total(order2);

		var order3 = new Order(1309, 95.90, 0.0);
		var total3 = orderService.total(order3);

		var sb = new StringBuffer();

		sb.append(getLine());
		sb.append("Informações dos Pedidos");
		sb.append(getLine());

		sb.append(getLogMessage(order1.getCode(), total1));
		sb.append(getLine());

		sb.append(getLogMessage(order2.getCode(), total2));
		sb.append(getLine());

		sb.append(getLogMessage(order3.getCode(), total3));
		sb.append(getLine());

		logger.info(sb.toString());
	}

	private String getLogMessage(Integer code, Double total) {
		String sb = "Pedido código %d" + "%n" +
				"Valor total: R$ %.2f";

		return String.format(sb, code, total);
	}

	private static String getLine() {
		return "\n------------------------\n";
	}

}
