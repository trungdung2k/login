package com.example.finalexam.constant;

import java.nio.charset.Charset;

public class BaseConst {
    // Session attribute user login
    public static final String USER_SESSION = "userSession";
    // Redirect prefix
    public static final String REDIRECT = "redirect:";
    // Error
    public static final String ERROR = "error";
    // not found
    public static final String NOT_FOUND = "not_found";
    // User ID
    public static final String UID = "uid";
    // text/html
    public static final String MAIL_TYPE = "text/html";
    // mail/send
    public static final String MAIL_ENDPOINT = "mail/send";
    // locked
    public static final String LOCKED = "locked";
    // File extensions csv
    public static final String CSV = ".csv";
    // File extensions PDF
    public static final String PDF = ".pdf";
    // File extensions JRXML
    public static final String JRXML = ".jrxml";
    // File extensions JASPER
    public static final String JASPER = ".jasper";
    // File extensions DAT
    public static final String DAT = ".DAT";
    // File extensions ZIP
    public static final String ZIP = ".zip";
    // File extensions JSON
    public static final String JSON = ".json";
    // File extensions TXT
    public static final String TXT = ".txt";
    // Zero
    public static final int ZERO = 0;
    // One
    public static final int ONE = 1;
    // Two
    public static final int TWO = 2;
    // Three
    public static final int THREE = 3;
    // Four
    public static final int FOUR = 4;
    // Five
    public static final int FIVE = 5;
    // Six
    public static final int SIX = 6;
    // Seven
    public static final int SEVEN = 7;
    // Eight
    public static final int EIGHT = 8;
    // Nine
    public static final int NINE = 9;
    // Ten
    public static final int TEN = 10;
    // Max length com.example.finalexam.request ID
    public static final int LENGTH_REQUEST_ID = 6;
    // Max length barcode
    public static final int MAX_LENGTH_BARCODE = 14;
    // Charset Shift-JIS
    public static final Charset CHARSET_SHIFT_JIS = Charset.forName("Shift-JIS");
    // Regex number with decimal
    public static final String REGEX_NUMBER_DECIMAL = "\\d+\\.?\\d*";
    // Default region JP
    public static final String REGION_JP = "JP";
    // Sort DESC
    public static final String SORT_DESC = "DESC";
    // Sort ASC
    public static final String SORT_ASC = "ASC";
}
