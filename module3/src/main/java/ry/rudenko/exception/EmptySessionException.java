package ry.rudenko.exception;



  public class EmptySessionException extends Exception {
    private static final String message = "Session is empty... ";

    public EmptySessionException(String ex) {
      super(message + ex);
    }
  }
