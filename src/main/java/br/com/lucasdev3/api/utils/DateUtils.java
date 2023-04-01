package br.com.lucasdev3.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

  public static String dateNow() {
    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SSS").format(new Date());
  }

}
