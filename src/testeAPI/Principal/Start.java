package testeAPI.Principal;

import java.io.IOException;

import ServiceApplication.Request;

public class Start {

	public static void main(String[] args) throws InterruptedException, IOException {
		String str = new Request().execute("https://parallelum.com.br/fipe/api/v1/carros/marcas/44/modelos/5901/anos/2015-1");
		System.out.println(str);

	}

}
