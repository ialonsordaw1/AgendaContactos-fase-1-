package ut7.agenda.modelo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		agenda = new TreeMap<>();
	}

	public void añadirContacto(Contacto c) {
		Set<Contacto> lista = new TreeSet<>();
		if(agenda.isEmpty() || !agenda.containsKey(c.getPrimeraLetra())) {
			lista.add(c);
			agenda.put(c.getPrimeraLetra(), lista);
		}
		else{
			lista = agenda.get(c.getPrimeraLetra());
			lista.add(c);
			agenda.remove(c.getPrimeraLetra());
			agenda.put(c.getPrimeraLetra(), lista);
		}
	}

	public Set<Contacto> contactosEnLetra(char c) {
		return agenda.get(c);
	}

	public int totalContactos() {
		Set<Character> entry = agenda.keySet();
		Iterator<Character> it = entry.iterator();
		int cont = 0;
		while (it.hasNext()) {
			Character temp = (Character) it.next();
			cont += agenda.get(temp).size();
		}
		return cont;
	}

	@Override
	public String toString() {
		String listado = "";
		Set<Character> entry = agenda.keySet();
		Iterator<Character> it = entry.iterator();
		while (it.hasNext()) {
			Character temp = (Character) it.next();
			listado += temp + "(" + agenda.get(temp).size() + " contacto/s)\n------------------\n";
			Iterator<Contacto> it2 = agenda.get(temp).iterator();
			while (it2.hasNext()) {
				Contacto contacto = (Contacto) it2.next();
				listado += contacto.toString();
			}
		}
		return listado;
	}

	public List<Contacto> buscarContactos(String texto) {
		ArrayList<Contacto> lista = new ArrayList<>();
		Set<Character> entry = agenda.keySet();
		Iterator<Character> it = entry.iterator();
		while (it.hasNext()) {
			Character temp = (Character) it.next();
			Iterator<Contacto> it2 = agenda.get(temp).iterator();
			while (it2.hasNext()) {
				Contacto contacto = (Contacto) it2.next();
				if(contacto.getNombre().contains(texto) || contacto.getApellidos().contains(texto)) {
					lista.add(contacto);
				}
			}
		}
		return lista;

	}

	public List<Personal> personalesEnLetra(char letra) {
		ArrayList<Personal> lista = new ArrayList<>();
		if(agenda.get(letra) == null)
			return null;
		else {
			Iterator<Contacto> it = agenda.get(letra).iterator();
			while (it.hasNext()) {
				Contacto contacto = (Contacto) it.next();
				if(contacto instanceof Personal) {
					lista.add((Personal) contacto);
				}
			}
		}
		return lista;
	}

	public List<Personal> felicitar() {
		ArrayList<Personal> lista = new ArrayList<>();
		Set<Character> entry = agenda.keySet();
		Iterator<Character> it = entry.iterator();
		while (it.hasNext()) {
			Character temp = (Character) it.next();
			Iterator<Personal> it2 = personalesEnLetra(temp).iterator();
			while (it2.hasNext()) {
				Personal personal = (Personal) it2.next();
				if(personal.esCumpleaños()) {
					lista.add(personal);
				}
			}
		}
		return lista;
	}

	public void personalesPorRelacion() {

	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {

		return null;

	}

}
