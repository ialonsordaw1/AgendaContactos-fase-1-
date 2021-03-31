package ut7.agenda.modelo;

public class Profesional extends Contacto{
	private String nombreEmpresa;

	public Profesional(String nombre, String apellidos, String telefono, String email, String nombreEmpresa) {
		super(nombre, apellidos, telefono, email);
		this.nombreEmpresa = guardarConFormato(nombreEmpresa.toLowerCase());
	}
	
	private String guardarConFormato(String nombreEmpresa) {
		String[] palabras = nombreEmpresa.split("\\s+");
		String nombre = "";
		for (int i = 0; i < palabras.length; i++) {
			String nombrea = "";
			nombrea += palabras[i].charAt(0);
			nombrea = nombrea.toUpperCase();
			nombrea += palabras[i].substring(1, palabras[i].length()-1);
			nombre += nombrea + " ";
		}
		return nombre;
	}

	@Override
	public String generarFirmaEmail() {
		String firmas[] = {"Atentamente","Saludos","Saludos cordiales","Mis mejores deseos"};
		return firmas[(int)(Math.random() * 4 + 1)];
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public String toString() {
		return super.toString() + "Empresa: " + nombreEmpresa + "\n\n";
	}

	
}
