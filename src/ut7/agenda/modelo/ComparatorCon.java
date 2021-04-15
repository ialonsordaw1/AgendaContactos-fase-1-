package ut7.agenda.modelo;

import java.util.Comparator;

public class ComparatorCon implements Comparator<Contacto>{
	/**
	 * Comparador entre dos contactos
	 * @param Contacto1, Contacto2 
	 * @return orden
	 */
	@Override
	public int compare(Contacto o1, Contacto o2) {
		if (o1.getApellidos().compareTo(o2.getApellidos()) == 0) {
			if (o1.getNombre().compareTo(o2.getNombre()) > 0) return -1;
			else if (o1.getNombre().compareTo(o2.getNombre()) < 0) return 1;
			else return 0;
		}
		else if (o1.getApellidos().compareTo(o2.getApellidos()) > 0) return -1;
		else return 1;
	}
}
