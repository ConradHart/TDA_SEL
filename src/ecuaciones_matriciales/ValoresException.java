package ecuaciones_matriciales;

class ValoresExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public ValoresExcepcion() {
		super();
	}
	public ValoresExcepcion(String s) {
		super(s);
	}
}