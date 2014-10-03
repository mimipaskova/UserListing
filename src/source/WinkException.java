package source;

public class WinkException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Throwable e;
	
	public WinkException(Throwable e) {
		this.e = e;
	}
	
	@Override
	public String getMessage() {
		return e.getMessage();
	}
	
	@Override
	public void printStackTrace() {
		e.printStackTrace();
	}

}
