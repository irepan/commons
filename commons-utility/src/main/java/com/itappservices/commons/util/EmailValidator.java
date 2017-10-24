/**
 * 
 */
package com.itappservices.commons.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase utilizada para validar los correos electr&oacute;nicos
 * @author Irepan
 *
 */
public class EmailValidator {
	  
	  public static final String EMAIL_BASE_PATTERN = "[_A-Za-z0-9-]+(?:\\.[_A-Za-z0-9-]+)*@((?:[_A-Za-z0-9]\\-)*[_A-Za-z0-9]){1,}(?:\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,9})";
	  /**
	   * Patr&oacute;n de validaci&oacute;n de email
	   */
	  public static final String EMAIL_PATTERN = "^" + EMAIL_BASE_PATTERN + "$";
	  /**
	   * Patr&oacute;n de validaci&oacute;n de m&uacute;ltiples emails separados por coma, al menos debe existir un email v&aacute;lido
	   */
	  public static final String MULTI_EMAIL_PATTERN = "^(?:" + EMAIL_BASE_PATTERN + "\\s*,\\s*)*" + EMAIL_BASE_PATTERN + "$";//"^" + EMAIL_BASE + "([\\s]*(\\,[\\s]*(" + EMAIL_BASE + ")))*$";
	  /**
	   * Patr&oacute;n de validaci&oacute;n de m&uacute;ltiples emails separados por coma (m&iacute;nimo 3 emails)
	   */
	  public static final String MULTI_3_EMAIL_PATTERN = getMultiRegexStEnd(3);//"^" + EMAIL_BASE + "([\\s]*(\\,[\\s]*(" + EMAIL_BASE + "))){2,}$";

	  
	  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
	  private static final Pattern multiEmailPattern = Pattern.compile(MULTI_EMAIL_PATTERN);;

	  /**
	   * Valida una direcci&oacute;n de correo con una expresi&oacute;n regular
	   * @param email la direcci&oacute;n de correo a validar
	   * @return true si es un correo v&aacute;lido, false si es un correo inv&aacute;lido
	   */
	  public static boolean validate(final String email){
		  final Matcher matcher = pattern.matcher(email);
		  return matcher.matches();
	  }
	  /**
	   * Valida una cadena que contiene por lo menos una direcci&oacute;n de correo v&aacute;lida
	   * las direcciones est&aacute;n separadas por comas
	   * @param emails cadena que contiene los correos separados por coma
	   * @return true valid hex, false invalid hex
	   */
	  public static boolean validateMulti(final String emails){
		  final Matcher matcher = multiEmailPattern.matcher(emails);
		  return matcher.matches();
	  }
	  public static boolean validateMulti(final String hex, int minimum){
		  final Pattern mPattern = Pattern.compile(getMultiRegexStEnd(minimum));
		  final Matcher matcher = mPattern.matcher(hex);
		  return matcher.matches();
	  }
	  /**
	   * Regresa la expresi&oacute;n regular para validar conjunto de emails separados por coma con m&iacute;nimo de repeticiones
	   * @param minimum el n&uacute;mero m&iacute;nimo de emails en la cadena, el m&iacute;nimo tiene que ser mayor que 0
	   * @return la expresi&oacute;n regular
	   */
	  public static String getMultiRegex(int minimum){
		  if (minimum<=0){
			  throw new IllegalArgumentException("El valor esperado es entero mayor que 0");
		  } if (minimum == 1) {
			  return "(?:" + EMAIL_BASE_PATTERN + "\\s*,\\s*)*" + EMAIL_BASE_PATTERN;
		  } else {
			  return "(?:" + EMAIL_BASE_PATTERN + String.format("\\s*,\\s*){%d,}", minimum - 1) + EMAIL_BASE_PATTERN;
		  }
	  }
	  /**
	   * Regresa la expresi&oacute;n regular para validar conjunto de emails separados por coma con m&iacute;nimo de repeticiones
	   * @param minimum el n&uacute;mero m&iacute;nimo de emails en la cadena, el m&iacute;nimo tiene que ser mayor que 0
	   * @return la expresi&oacute;n regular con s&iacute;mbolo de inicio y fin
	   */
	  public static String getMultiRegexStEnd(int minimum){
			  return "^" + getMultiRegex(minimum) + "$";
	  }
}
