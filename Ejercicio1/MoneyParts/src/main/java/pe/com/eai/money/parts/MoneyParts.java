package pe.com.eai.money.parts;

import java.util.ArrayList;

public class MoneyParts {

	private static StringBuffer sb = new StringBuffer();

	public static void main(String[] args) {
		Double monto = Double.parseDouble(args[0]);
		build(monto);

	}

	public static void build(double monto) {
		// Init
		ArrayList<Double> numeros = new ArrayList<>();
		ArrayList<Double> denominaciones = new ArrayList<>();
		double suma = 0;
		ArrayList<String> salida = new ArrayList<String>();

		// Referencias
		denominaciones.add(0.05);
		denominaciones.add(0.1);
		denominaciones.add(0.2);
		denominaciones.add(0.5);
		denominaciones.add(1.0);
		denominaciones.add(2.0);
		denominaciones.add(5.0);
		denominaciones.add(10.0);
		denominaciones.add(20.0);
		denominaciones.add(50.0);
		denominaciones.add(100.0);
		denominaciones.add(200.0);
		// Logica
		Core(monto, numeros, suma, denominaciones);

		String[] parts = sb.toString().split("\\|");

		for (int i = 0; i < parts.length; i++) {

			salida.add(parts[i]);
		}
		// Salida
		System.out.println("entrada:" + '"' + monto + '"' + " salida:" + salida);

	}

	public static void Core(double monto, ArrayList<Double> numeros, double suma,
			ArrayList<Double> denominaciones) {

		if ((suma == monto)) {

			sb.append(numeros);
			sb.append("|");

		} else {

			for (int i = 0; i < denominaciones.size(); i++) {

				suma += denominaciones.get(i);

				if (suma <= monto) {

					numeros.add(denominaciones.get(i));

					Core(monto, numeros, suma, denominaciones);

					numeros.remove(numeros.indexOf(denominaciones.get(i)));

				}

				suma -= denominaciones.get(i);

			}

		}

	}
}
