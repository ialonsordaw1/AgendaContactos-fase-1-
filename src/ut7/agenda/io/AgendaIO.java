package ut7.agenda.io;

import ut7.agenda.modelo.*;
/**
 * Utilidades para cargar la agenda
 */
public class AgendaIO {
	/**
	 * Metodo para importar una linea de datos a la agenda
	 * @param agenda
	 */
	public static void importar(AgendaContactos agenda) {
		String[] linea = obtenerLineasDatos();
		for (int i = 0; i < linea.length; i++) {
			Contacto c = parsearLinea(linea[i]);
			agenda.añadirContacto(c);
		}
	}
	/**
	 * Metodo privado para parsear la linea a importar
	 * @param linea
	 * @return Contacto
	 */
	private static Contacto parsearLinea(String linea) {
		String[] formateo = linea.split(",");
		Contacto c = null;
		if (formateo[0].trim().equals("1")) {
			c = new Profesional(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim());
		}
		else {
			switch (formateo[6].trim().toLowerCase()) {
			case "amigos":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.AMIGOS);
				break;
			case "hijo":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.HIJO);
				break;
			case "hija":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.HIJA);
				break;
			case "pareja":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.PAREJA);
				break;
			case "padre":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.PADRE);
				break;
			case "madre":
				c = new Personal(formateo[1].trim(), formateo[2].trim(), formateo[3].trim(), formateo[4].trim(), formateo[5].trim(), Relacion.MADRE);
				break;
			}
		}
		return c;
	}

	/**
	 * 
	 * @return un array de String con todas las líneas de información de todos
	 *         los contactos. 1 significa contacto profesional, 2 significa
	 *         contacto personal
	 */
	private static String[] obtenerLineasDatos() {
		return new String[] {
				"1, Isabel, Acosta Mendioroz,  678895433 ,  iacostamen@gmail.com ,  walden estrella ",
				"2,  pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 09/03/2007, amigos",
				"1, Angel , Esteban Grande , 674544123 ,  aestebang@gmail.com ,  magma publicidad ",
				"2, elena , bueno ganuza , 6786547699 ,  ebuenogan@gmail.com , 17/03/2000, amigos",
				"2, amaia , romero sein , 642222343 ,  aromerosein@gmail.com , 09/03/2012, pareja",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"1,  Isabel ,  Acosta Marin , 678895433 ,  iacostamar@gmail.com ,  publicidad holdings ",
				"1 ,    roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, pedro , urruti tello , 616789654 ,  urrutitello@gmail.com , 17/03/2000, amigos",
				"1,marta, sanz iris, 622999876, msanzi@gmail.com, jump literatura ",
				"1,javier, porto luque, 691256777 , japorlu@gmail.com, gas natural ",
				"1,pablo, ponce larraoz, 689123456, pabloponce@gmail.com, la caixa",
				"1, javier, marin lancho, 666666666, jruizlanchoe@gmail.com, bbva",
				"1,juan maria, garcía oliva, 699898111, jmgarcioliva@gmail.com, conway motor ",
				"2, Berta ,  andia solano ,  621123345 , bandiasol@gmail.com ,  12/12/1999 ,  HIJA",
				"2, Ignacio ,  Anto roth ,  688912799 , iantoroth@gmail.com ,  11/11/1969 , padre",
				"  1,  roberto , casas maura , 666777888 ,  rocasasma@gmail.com ,  strato banca ",
				" 2, daniel , martin martin , 678901234 ,  damrtinmartin@gmail.com , 15/07/1980, amigos",
				"  2, pablo , martin abradelo , 667788899 ,  martinabra@gmail.com , 31/01/2010, amigos",
				"  2, susana , santaolalla bilbao , 676767676 ,  ssantaolalla@gmail.com , 17/03/1998, amigos",
				"  2, adur ,  martin merino ,  611112113 , adurmartinme@gmail.com ,  14/02/2003 , amigos" };

	}

}
