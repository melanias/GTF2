package br.com.sti.gtf.util;

import java.io.UnsupportedEncodingException;

import javax.enterprise.inject.Vetoed;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Phelipe Melanias
 */
@Vetoed
public final class Utilities {
    private Utilities() {}

    public static String md5(String senha) {
        return DigestUtils.md5Hex(senha);
    }

    public static String sha256(String senha) {
        return DigestUtils.sha256Hex(senha);
    }

    public static String sha512(String senha) {
        return DigestUtils.sha512Hex(senha);
    }

    public static String getRootURL(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();

        return url.substring(0, url.indexOf(uri));
    }

    public static String getRootURLWithContextPath(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String ctx = request.getContextPath();

        return url.substring(0, url.indexOf(uri)) + ctx;
    }

    public static <T> boolean isNull(T obj) {
        return obj == null;
    }

    public static <T> boolean isNotNull(T obj) {
        return obj != null;
    }

    public static String implode(Object[] pieces, String glue) {
        String output = "";

        if (pieces != null && pieces.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(pieces[0]);

            for (int i = 1; i < pieces.length; i++) {
                sb.append(glue);
                sb.append(pieces[i]);
            }

            output = sb.toString();
        }

        return output;
    }

    public static boolean mail(String mail) {
        if (mail.trim().isEmpty()) {
            return false;
        }

        //TODO: melhorar o pattern de validação
        return mail.matches(".+@.+\\.[a-zA-Z]+");
    }

    public static String decodeText(String text) {
        try {
            byte[] bytes = text.getBytes("ISO-8859-1");
            text = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return text;
        }

        return text;
    }

    //TODO: melhorar o código de validação do CNPJ
    public static boolean cnpj(String cnpj) {
        cnpj = cnpj.trim().replaceAll("\\D", "");

        if (cnpj.length() != 14) {
            return false;
        }

        int soma = 0, dig;
        String cnpj_calc = cnpj.substring(0, 12);

        char[] chr_cnpj = cnpj.toCharArray();

        //Primeira parte
        for (int i = 0; i < 4; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
            }
        }

        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        //Segunda parte
        soma = 0;
        for (int i = 0; i < 5; i++) {
            if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
            }
        }

        dig = 11 - (soma % 11);

        cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

        return cnpj.equals(cnpj_calc);
    }

    public static boolean cpf(String cpf) {
        cpf = cpf.trim().replaceAll("\\D", "");

        if (cpf.length() != 11 || isAllNumbersEquals(cpf)) {
            return false;
        }

        //Dígito verificador
        int verificador = Integer.parseInt(cpf.substring(9));

        //Primeira parte do cálculo
        int soma = 0, posicao = 10;

        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * posicao--;
        }

        int num1 = ((soma % 11) < 2) ? 0 : 11 - (soma % 11);

        //Segunda parte do cálculo
        soma = 0;
        posicao = 11;

        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(String.valueOf(cpf.charAt(i))) * posicao--;
        }

        int num2 = ((soma % 11) < 2) ? 0 : 11 - (soma % 11);

        //Retornar se o CPF é válido
        return ((num1 * 10) + num2) == verificador;
    }

    private static boolean isAllNumbersEquals(String cpf) {
        int count = cpf.split(cpf.substring(0, 1)).length;
        return count == 0;
    }
}