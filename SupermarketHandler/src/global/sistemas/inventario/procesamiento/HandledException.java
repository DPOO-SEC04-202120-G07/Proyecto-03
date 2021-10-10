package global.sistemas.inventario.procesamiento;

public class HandledException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

    public HandledException(String code) {
        this.setCode(code);
    }

    public HandledException(String code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
