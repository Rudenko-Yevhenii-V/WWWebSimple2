package ry.rudenko.exception;



  public class EmptyArgsException extends Exception {
    private static final String message = "Args is empty... ";

    public EmptyArgsException(String ex) {
      super(message + ex);
    }
  }
