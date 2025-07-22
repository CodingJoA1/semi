package kh.semiProject.users.model.exception;

public class UsersException extends RuntimeException{
	public UsersException() {}
	public UsersException(String msg) {
		super(msg);
	}
}