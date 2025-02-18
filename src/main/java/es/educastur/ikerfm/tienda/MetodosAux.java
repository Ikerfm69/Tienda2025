package es.educastur.ikerfm.tienda;


public class MetodosAux {
    public static boolean validarDNI(String dni) {
          // Verificar que el DNI tiene un formato válido
          if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
              return false;
          }
          // Extraer el número y la letra del DNI
          String numeroStr = dni.substring(0, 8);
          char letra = dni.charAt(8);
  
          // Calcular la letra correspondiente al número del DNI
          char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));
                    // Comparar la letra calculada con la letra proporcionada y devolver
                    // el resultado de la comparación TRUE/FALSE
                    return letra == letraCalculada; 
    }
    
    public static char calcularLetraDNI(int numero) {
          String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
          return letras.charAt(numero % 23);
    } 

      public static boolean esInt(String s){
        int n;
        try{
            n=Integer.parseInt(s);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    public static boolean esDouble(String s){
        double n;
        try{
            n=Double.parseDouble(s);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }
}