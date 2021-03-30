package ut7.agenda.modelo;

import java.util.Comparator;

public abstract class Contacto implements Comparator<Contacto> {
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;

	public Contacto(String nombre, String apellidos, String telefono,
			String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return email.hashCode();

	}

	public char getPrimeraLetra() {
		return apellidos.charAt(0);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Contacto)) return false;
		return getApellidos().equals(((Contacto) obj).getApellidos()) && getNombre().equals(((Contacto) obj).getNombre()) && getEmail().equals(((Contacto) obj).getEmail());
	}
	
	@Override
	public int compare(Contacto o1, Contacto o2) {
		if (o1.getApellidos().compareTo(o2.getApellidos()) == 0) {
			if (o1.getNombre().compareTo(o2.getNombre()) < 0) return -1;
			else if (o1.getNombre().compareTo(o2.getNombre()) > 0) return 1;
			else return 0;
		}
		else if (o1.getApellidos().compareTo(o2.getApellidos()) < 0) return -1;
		else return 1;
	}
	
	public abstract String generarFirmaEmail ();
	
	@Override
	public String toString() {
		return getApellidos().toUpperCase() + ", " + getNombre().toUpperCase() + " (" + this.getClass().toString() + ")\n"
				+ "Tfno: " + getTelefono() + " | email: " + getEmail() + "\n";
	}

}
