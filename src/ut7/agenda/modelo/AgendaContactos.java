package ut7.agenda.modelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AgendaContactos implements Comparator<Personal>{
	private Map<Character, Set<Contacto>> agenda;

	public AgendaContactos() {
		agenda = new TreeMap<>();
	}

	public void añadirContacto(Contacto c) {
		List<Contacto> lista2 = new ArrayList<>();
		Set<Contacto> lista = new LinkedHashSet<>();
		if(agenda.isEmpty() || !agenda.containsKey(c.getPrimeraLetra())) {
			lista2.add(c);
			Collections.sort(lista2, new ComparatorCon());
			Iterator it = lista2.iterator();
			while (it.hasNext()) {
				lista.add((Contacto) it.next());
			}
			agenda.put(c.getPrimeraLetra(), lista);
		}
		else{
			lista = agenda.get(c.getPrimeraLetra());
			lista.add(c);
			Collections.sort(lista2, new ComparatorCon());
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
				if(contacto.getNombre().toLowerCase().contains(texto) || contacto.getApellidos().toLowerCase().contains(texto)) {
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

	public TreeMap<Relacion, List<String>> personalesPorRelacion() {
		List<String> list = new ArrayList();
		List<String> list2 = new ArrayList();
		List<String> list3 = new ArrayList();
		List<String> list4 = new ArrayList();
		List<String> list5 = new ArrayList();
		List<String> list6 = new ArrayList();
		TreeMap<Relacion, List<String>> mapa = new TreeMap();
		Set<Character> entry = agenda.keySet();
		Iterator<Character> it = entry.iterator();
		while (it.hasNext()) {
			Character temp = (Character) it.next();
			Iterator<Personal> it2 = personalesEnLetra(temp).iterator();
			while (it2.hasNext()) {
				Personal temp2 = (Personal) it2.next();
				String apenon = temp2.getApellidos() + " " + temp2.getNombre();
				switch (temp2.getRelacion()) {
				case AMIGOS:
					list.add(apenon);
					break;
				case HIJO:
					list2.add(apenon);
					break;
				case HIJA:
					list3.add(apenon);
					break;
				case MADRE:
					list4.add(apenon);
					break;
				case PADRE:
					list5.add(apenon);
					break;
				case PAREJA:
					list6.add(apenon);
					break;
				}
			}
		}
		if(!list.isEmpty())mapa.put(Relacion.AMIGOS, list);
		if(!list2.isEmpty())mapa.put(Relacion.HIJO, list2);
		if(!list3.isEmpty())mapa.put(Relacion.HIJA, list3);
		if(!list4.isEmpty())mapa.put(Relacion.MADRE, list4);
		if(!list5.isEmpty())mapa.put(Relacion.PADRE, list5);
		if(!list6.isEmpty())mapa.put(Relacion.PAREJA, list6);
		return mapa;
	}

	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {
		List<Personal> lista = personalesEnLetra(letra);
		Collections.sort(lista, new AgendaContactos());
		return lista;
	}
	
	@Override
	public int compare(Personal p1, Personal p2) {
		if (p1.getFechaNacimiento().getYear() > p2.getFechaNacimiento().getYear()) {
			return 1;
		}
		else if (p1.getFechaNacimiento().getYear() < p2.getFechaNacimiento().getYear()) {
			return -1;
		}
		else {
			if(p1.getFechaNacimiento().getMonthValue() > p2.getFechaNacimiento().getMonthValue()) {
				return 1;
			}
			else if(p1.getFechaNacimiento().getMonthValue() < p2.getFechaNacimiento().getMonthValue()) {
				return -1;
			}
			else {
				if(p1.getFechaNacimiento().getDayOfMonth() > p2.getFechaNacimiento().getDayOfMonth()) {
					return 1;
				}
				else if(p1.getFechaNacimiento().getDayOfMonth() < p2.getFechaNacimiento().getDayOfMonth()) {
					return -1;
				}
				else return 0;
			}
		}
	} 
}
